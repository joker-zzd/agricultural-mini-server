package com.mini.productcomment.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.businessuser.constant.UserType;
import com.mini.common.domain.dto.PageDTO;
import com.mini.filter.LoginUser;
import com.mini.productcomment.domain.ProductComment;
import com.mini.productcomment.domain.dto.ProductCommentDTO;
import com.mini.productcomment.domain.dto.ProductCommentReplyDTO;
import com.mini.productcomment.domain.vo.ProductCommentListVO;
import com.mini.productcomment.enums.Status;
import com.mini.productcomment.query.ProductCommentQuery;
import com.mini.productcomment.service.ProductCommentService;
import com.mini.productcomment.mapper.ProductCommentMapper;
import com.mini.resultvo.ResultVO;
import com.mini.utils.AssertUtils;
import com.mini.utils.BeanUtils;
import com.mini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 19256
 * @description 针对表【product_comment(商品评价与回复表)】的数据库操作Service实现
 * @createDate 2026-02-19 15:11:36
 */
@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment>
        implements ProductCommentService {

    private final ProductCommentMapper productCommentMapper;

    @Override
    public ResultVO<Void> savaEvaluate(ProductCommentDTO productCommentDTO) {
        // 1.获取当前用户
        Long userId = SecurityUtils.getUserId();
        AssertUtils.isNotNull(SecurityUtils.getLoginUser(), "用户未登录");

        // 2.数据转换
        ProductComment productComment = BeanUtils.toBean(productCommentDTO, ProductComment.class);
        // 3.补充数据
        productComment.setUserType(UserType.MERCHANT);
        productComment.setUserId(userId);
        // 4.保存
        productCommentMapper.insert(productComment);

        productComment.setRootId(productComment.getId());
        productCommentMapper.updateById(productComment);
        return ResultVO.success();
    }

    @Override
    public ResultVO<Void> deleteEvaluate(Long id) {
        Long userId = SecurityUtils.getUserId();
        ProductComment productComment = productCommentMapper.selectById(id);
        if (productComment == null) {
            return ResultVO.fail("");
        }
        assert userId != null;
        if (!userId.equals(productComment.getUserId())) {
            return ResultVO.fail("该问题提问者非当前用户，无法删除");
        }
        boolean result = productCommentMapper.deleteById(id) > 0;
        if (result) {
            return ResultVO.success();
        }
        return ResultVO.fail();
    }

    @Override
    public PageDTO<ProductCommentListVO> findByPage(ProductCommentQuery query) {
        // 1.校验商品id
        AssertUtils.isTrue(query.getProductId() != null && query.getProductId() > 0,
                "商品id不能为空");

        // 2.获取用户
        Long userId = SecurityUtils.getUserId();

        // 3.分页查询一级评论
        Page<ProductComment> page = lambdaQuery()
                .eq(ProductComment::getProductId, query.getProductId())
                .eq(ProductComment::getUserId, userId)
                .eq(ProductComment::getHidden, Boolean.FALSE)
                .eq(query.getScore() != null, ProductComment::getScore, query.getScore())
                .gt(query.getStartDate() != null, ProductComment::getCreateTime, query.getStartDate())
                .lt(query.getEndDate() != null, ProductComment::getCreateTime, query.getEndDate())
                .page(query.toMpPageDefaultSortByCreateTimeDesc());

        List<Long> rootIds = page.getRecords().stream()
                .map(ProductComment::getId)
                .toList();
        if (rootIds.isEmpty()) {
            Page<ProductCommentListVO> emptyPage = new Page<>();
            BeanUtils.copyProperties(page, emptyPage, "records");
            emptyPage.setRecords(Collections.emptyList());
            return PageDTO.of(emptyPage, Collections.emptyList());
        }

        // 4.查询所有回复
        List<ProductComment> replies = lambdaQuery()
                .eq(ProductComment::getProductId, query.getProductId())
                .in(ProductComment::getRootId, rootIds)
                .ne(ProductComment::getParentId, 0)
                .list();

        // 5.封装数据VO
        Map<Long, List<ProductComment>> replyMap = replies.stream()
                .collect(Collectors.groupingBy(ProductComment::getRootId));

        List<ProductCommentListVO> voList = page.getRecords().stream().map(comment -> {
            ProductCommentListVO vo = new ProductCommentListVO();
            BeanUtils.copyProperties(comment, vo);

            // 5.1.找到这个评价的回复
            List<ProductCommentListVO> replyList =
                    replyMap.getOrDefault(comment.getId(), Collections.emptyList())
                            .stream()
                            .map(reply -> {
                                ProductCommentListVO replyVO = new ProductCommentListVO();
                                BeanUtils.copyProperties(reply, replyVO);
                                return replyVO;
                            }).toList();

            vo.setReplyList(replyList);

            return vo;
        }).toList();

        // 6.返回分页VO
        Page<ProductCommentListVO> voPage = new Page<>();
        BeanUtils.copyProperties(page, voPage, "records");
        voPage.setRecords(voList);
        return PageDTO.of(voPage);
    }

    @Override
    @Transactional
    public ResultVO<Void> addReplyOrAnswer(ProductCommentReplyDTO productCommentReplyDTO) {
        // 1.获取当前用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        AssertUtils.isNotNull(loginUser, "用户未登录");

        // 2.查询父评论（必须存在）
        ProductComment parent = productCommentMapper.selectById(productCommentReplyDTO.getParentId());
        AssertUtils.isNotNull(parent, "父评论不存在");
        // 3.保存回复
        ProductComment productComment = BeanUtils.toBean(productCommentReplyDTO, ProductComment.class);
        assert loginUser != null;
        productComment.setUserId(loginUser.getUserId());
        productComment.setUserType(loginUser.getUserType());
        productComment.setLikedTimes(0);
        productComment.setReplyTimes(0);
        productComment.setParentId(parent.getId());
        productComment.setRootId(
                parent.getParentId() == 0
                        ? parent.getId()
                        : parent.getRootId()
        );

        // 4.设置状态（基于角色）
        productComment.setStatus(getDefaultStatusByUserType(loginUser.getUserType()));
        boolean result = productCommentMapper.insert(productComment) > 0;

        if (result) {
            productCommentMapper.increaseReplyTimes(parent.getId());
            return ResultVO.success();
        }
        return ResultVO.fail();
    }

    private Status getDefaultStatusByUserType(UserType userType) {
        return switch (userType) {
            case MEMBER, MERCHANT -> Status.UNVIEWED;
            case ADMINISTRATOR -> Status.VIEWED;
        };
    }
}




