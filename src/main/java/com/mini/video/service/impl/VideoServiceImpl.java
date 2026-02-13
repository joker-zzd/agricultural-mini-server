package com.mini.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.video.domain.VideoDO;
import com.mini.video.service.VideoService;
import com.mini.video.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author 19256
* @description 针对表【video(商城视频介绍表)】的数据库操作Service实现
* @createDate 2026-02-13 00:54:16
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDO>
    implements VideoService{

}




