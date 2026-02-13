package com.mini.video.mapper;

import com.mini.video.domain.VideoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【video(商城视频介绍表)】的数据库操作Mapper
* @createDate 2026-02-13 00:54:16
* @Entity com.mini.video.domain.Video
*/
@Mapper
public interface VideoMapper extends BaseMapper<VideoDO> {

}




