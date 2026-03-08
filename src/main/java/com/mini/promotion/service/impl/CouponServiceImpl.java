package com.mini.promotion.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.common.api.CategoryClient;
import com.mini.common.api.dot.CategoryBasicDTO;
import com.mini.common.domain.dto.PageDTO;
import com.mini.common.exception.BadRequestException;
import com.mini.promotion.constant.PromotionConstants;
import com.mini.promotion.domain.Coupon;
import com.mini.promotion.domain.CouponScope;
import com.mini.promotion.domain.dto.CouponFormDTO;
import com.mini.promotion.domain.dto.CouponIssueFormDTO;
import com.mini.promotion.domain.vo.CouponDetailVO;
import com.mini.promotion.domain.vo.CouponPageVO;
import com.mini.promotion.domain.vo.CouponScopeVO;
import com.mini.promotion.enums.CouponStatus;
import com.mini.promotion.enums.ObtainType;
import com.mini.promotion.query.CouponQuery;
import com.mini.promotion.service.CouponScopeService;
import com.mini.promotion.service.CouponService;
import com.mini.promotion.mapper.CouponMapper;
import com.mini.utils.BeanUtils;
import com.mini.utils.CollUtils;
import com.mini.utils.DateUtils;
import com.mini.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 19256
 * @description 针对表【coupon(优惠券的规则信息)】的数据库操作Service实现
 * @createDate 2026-02-28 15:36:26
 */
@Service
@AllArgsConstructor
@Slf4j
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon>
        implements CouponService {
    private final CouponScopeService scopeService;
    private final CategoryClient categoryClient;
    private final StringRedisTemplate redisTemplate;

    @Override
    public void saveCoupon(CouponFormDTO dto) {
        //1.保存优惠券
        Coupon coupon = BeanUtils.copyBean(dto, Coupon.class);
        save(coupon);
        //2.检查是否为分类限定优惠券
        if (!dto.getSpecific()) {
            return;
        }
        //3.检查是否有优惠券范围
        List<Long> scopes = dto.getScopes();
        if (CollUtils.isEmpty(scopes)) {
            throw new BadRequestException("限定范围不能为空");
        }
        Long couponId = coupon.getId();
        //4.将此优惠券的可用范围对象插入优惠券范围表
        List<CouponScope> list = new ArrayList<>();
        for (Long scope : scopes) {
            CouponScope couponScope = new CouponScope();
            couponScope.setCouponId(couponId);
            couponScope.setBizId(scope);
            list.add(couponScope);
        }
        //5.批量保存优惠券范围表
        scopeService.saveBatch(list);
    }

    @Override
    public void updateById(CouponFormDTO dto, Long id) {
        // 1.校验参数
        Long dtoId = dto.getId();
        // 如果dto的id和路径id都存在但id不一致，或者都不存在，则抛出异常
        if ((dtoId != null && id != null && !dtoId.equals(id)) || (dtoId == null && id == null)) {
            throw new BadRequestException("参数错误");
        }
        // 2.更新优惠券基本信息
        Coupon coupon = BeanUtils.copyBean(dto, Coupon.class);
        // 只更新状态为1的优惠券基本信息，如果失败则是状态已修改
        boolean update = lambdaUpdate().eq(Coupon::getId, 1).update(coupon);
        // 基本信息更新失败则无需更新优惠券范围信息
        if (!update) {
            return;
        }
        // 3.更新优惠券范围信息
        List<Long> scopeIds = dto.getScopes();
        // 3.1只要是优惠券状态不为1，或者优惠券范围为空，则不更新优惠券范围信息
        // 3.2个人写法是先删除优惠券范围信息，再重新插入
        List<Long> ids = scopeService.lambdaQuery()
                .select(CouponScope::getId)
                .eq(CouponScope::getCouponId, dto.getId())
                .list()
                .stream()
                .map(CouponScope::getId)
                .toList();

        scopeService.removeByIds(ids);

        // 3.3删除成功后，并且有范围再插入
        if (CollUtils.isNotEmpty(scopeIds)) {
            List<CouponScope> lis = scopeIds.stream()
                    .map(i -> new CouponScope()
                            .setCouponId(dto.getId())
                            .setType(1).setBizId(i)).collect(Collectors.toList());
            scopeService.saveBatch(lis);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // 1.查询优惠券是否存在并删除
        boolean remove = lambdaUpdate()
                .eq(Coupon::getId, id)
                .eq(Coupon::getStatus, 1)
                .remove();
        if (!remove) {
            throw new BadRequestException("优惠券不存在");
        }

        // 2.查询优惠券范围信息并删除
        scopeService.lambdaUpdate()
                .eq(CouponScope::getCouponId, id)
                .remove();
    }

    @Override
    @Transactional
    public CouponDetailVO queryById(Long id) {
        // 1.查询优惠券基本信息
        Coupon coupon = lambdaQuery()
                .eq(Coupon::getId, id)
                .one();
        // 2.查询优惠券范围列表
        List<CouponScope> CouponScopes = scopeService.lambdaQuery().eq(CouponScope::getCouponId, coupon.getId()).list();
        // 3.查询范围信息<分类id，分类名称>
        Map<Long, String> cateMap = categoryClient.getAllOfOneLevel()
                .stream()
                .collect(Collectors.toMap(CategoryBasicDTO::getId, CategoryBasicDTO::getName));
        // 4.封装范围信息到范围列表
        List<CouponScopeVO> vos = CouponScopes.stream()
                .map(i -> new CouponScopeVO()
                        .setName(cateMap.get(i.getBizId()))
                        .setId(i.getBizId()))
                .toList();

        // 5.封装优惠券详细信息
        CouponDetailVO vo = BeanUtils.copyBean(coupon, CouponDetailVO.class);
        vo.setScopes(vos);

        return vo;
    }

    @Override
    public PageDTO<CouponPageVO> queryCouponByPage(CouponQuery query) {
        String name = query.getName();
        Integer status = query.getStatus();
        Integer type = query.getType();
        // 1.分页查询
        Page<Coupon> page = lambdaQuery()
                .like(StringUtils.isNotEmpty(name), Coupon::getName, name)
                .eq(status != null, Coupon::getStatus, status)
                .eq(type != null, Coupon::getType, type)
                .page(query.toMpPageDefaultSortByCreateTimeDesc());
        List<Coupon> coupons = page.getRecords();
        if (CollUtils.isEmpty(coupons)) {
            return PageDTO.empty(page);
        }
        // 2.封装数据
        List<CouponPageVO> vos = BeanUtils.copyList(coupons, CouponPageVO.class);

        return PageDTO.of(page, vos);
    }

    @Override
    public void beginIssue(CouponIssueFormDTO dto) {
        log.info("发放优惠券,线程id{}线程名{}", Thread.currentThread().getId(), Thread.currentThread().getName());
        Integer termDays = dto.getTermDays();
        LocalDateTime issueBeginTime = dto.getIssueBeginTime();
        // 1.获取该优惠券DB信息
        Coupon coupon = getById(dto.getId());
        // 记录下未修改前状态是否为待发放
        boolean isDraft = coupon.getStatus() == CouponStatus.DRAFT.getValue();
        if (!isDraft) {
            throw new BadRequestException("该优惠券不存在");
        }
        // 2.判断优惠券是定时发放还是立即发放
        LocalDateTime now = LocalDateTime.now();
      /*如果立即发放时间为空，或者立即发放时间小于等于当前时间，则设置立即发放时间为当前时间，
        注意isBefore如果比较的时间都一样，那么比较结果为false */
        boolean isIssueNow = issueBeginTime == null || issueBeginTime.isBefore(now);
        if (isIssueNow) {
            // 设置立即发放时间
            coupon.setIssueBeginTime(now);
            coupon.setStatus(CouponStatus.ISSUING.getValue());
        } else {
            // 定时发放
            coupon.setIssueBeginTime(dto.getIssueBeginTime());
            coupon.setStatus(CouponStatus.UN_ISSUE.getValue());
        }
        coupon.setIssueEndTime(dto.getIssueEndTime());

        // 3.判断优惠券是有效天数还是有效期
        if (termDays != null) {
            coupon.setTermDays(termDays);
        } else {
            coupon.setTermBeginTime(dto.getTermBeginTime());
            coupon.setTermEndTime(dto.getTermEndTime());
        }
        // 4.更新优惠券信息
        updateById(coupon);

      /*  5.如果是立即发放，将优惠券(优惠券id，开始领取时间，结束领取时间，
         可领取数量，限领数量)信息使用HASH结构存到Redis当中*/
        if (isIssueNow) {
            String key = PromotionConstants.COUPON_CACHE_KEY_PREFIX + coupon.getId();
            redisTemplate.boundHashOps(key).put("issueBeginTime", String.valueOf(DateUtils.toEpochMilli(now)));
            redisTemplate.boundHashOps(key).put("issueEndTime", String.valueOf(DateUtils.toEpochMilli(dto.getIssueEndTime())));
            redisTemplate.boundHashOps(key).put("totalNum", String.valueOf(coupon.getTotalNum()));
            redisTemplate.boundHashOps(key).put("userLimit", String.valueOf(coupon.getUserLimit()));
        }
        // 6.如果优惠券状态之前状态为指定发放，和待发放，才会生成兑换码(如果之前状态是暂停状态，发放就会导致生成两次兑换码)
        // TODO
        if (coupon.getObtainWay() == ObtainType.ISSUE) {
            //异步生成兑换码
        }
    }
}




