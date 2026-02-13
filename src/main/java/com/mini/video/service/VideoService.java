package com.mini.video.service;

import com.mini.resultvo.ResultVO;
import com.mini.video.domain.VideoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.video.domain.dto.VideoDTO;
import com.mini.video.domain.vo.VideoVO;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;

/**
* @author 19256
* @description 针对表【video(商城视频介绍表)】的数据库操作Service
* @createDate 2026-02-13 00:54:16
*/
public interface VideoService extends IService<VideoDO> {
    /**
     * 查询所有上线的视频
     */
    ResultVO<List<VideoVO>> listOnlineVideos(Integer CurrentPage, Integer pageSize);

    /**
     * 新增视频
     */
    ResultVO<Void> addVideo(VideoDTO videoDTO) throws IOException;

    /**
     * 根据ID查询视频详情
     */
    ResultVO<VideoVO> findById(@NotEmpty(message = "id不能为空") Long id);
}
