package com.mini.video.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.common.service.CosService;
import com.mini.resultvo.ResultVO;
import com.mini.video.domain.VideoDO;
import com.mini.video.domain.dto.VideoDTO;
import com.mini.video.domain.vo.VideoVO;
import com.mini.video.enums.VideoStatus;
import com.mini.video.service.VideoService;
import com.mini.video.mapper.VideoMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author 19256
 * @description 针对表【video(商城视频介绍表)】的数据库操作Service实现
 * @createDate 2026-02-13 00:54:16
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDO>
        implements VideoService {
    private final VideoMapper videoMapper;
    private final CosService cosService;

    public VideoServiceImpl(VideoMapper videoMapper,
                            CosService cosService) {
        this.videoMapper = videoMapper;
        this.cosService = cosService;
    }

    @Override
    public ResultVO<List<VideoVO>> listOnlineVideos(Integer CurrentPage, Integer pageSize) {
        Page<VideoDO> page = new Page<>(CurrentPage, pageSize);

        LambdaQueryWrapper<VideoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(VideoDO::getCreatedAt)
                .eq(VideoDO::getStatus, VideoStatus.ONLINE);
        this.videoMapper.selectPage(page, queryWrapper);
        List<VideoDO> videos = page.getRecords();
        List<VideoVO> videoVO = BeanUtil.copyToList(videos, VideoVO.class);

        return ResultVO.success(videoVO, page.getTotal());
    }

    @Override
    public ResultVO<Void> addVideo(VideoDTO videoDTO) throws IOException {
        //1.上传视频
        String videoUrl = cosService.uploadFile(videoDTO.getVideoFile(), "videos");

        //2.上传封面
        String coverImageUrl = cosService.uploadFile(videoDTO.getCoverImageFile(), "covers");

        //3. 组装 VideoDO
        VideoDO videoDO = new VideoDO();
        videoDO.setTitle(videoDTO.getTitle());
        videoDO.setDescription(videoDTO.getDescription());
        videoDO.setUrl(videoUrl);
        videoDO.setCoverImageUrl(coverImageUrl);
        videoDO.setStatus(VideoStatus.ONLINE); // 默认上线

        //4.保存
        boolean save = this.save(videoDO);
        if (!save) {
            return ResultVO.fail("操作失败");
        }
        return ResultVO.success("操作成功");
    }

    @Override
    public ResultVO<VideoVO> findById(Long id) {
        VideoDO videoDO = this.videoMapper.selectById(id);
        if (videoDO == null) {
            return ResultVO.fail("视频不存在");
        }
        VideoVO videoVO = BeanUtil.copyProperties(videoDO, VideoVO.class);
        return ResultVO.success(videoVO);
    }
}




