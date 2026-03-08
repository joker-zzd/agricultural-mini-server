package com.mini.likedrecord.mapper;

import com.mini.likedrecord.domain.LikedRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【liked_record(点赞记录表)】的数据库操作Mapper
* @createDate 2026-02-20 00:28:56
* @Entity com.mini.likedrecord.domain.LikedRecord
*/
@Mapper
public interface LikedRecordMapper extends BaseMapper<LikedRecord> {

}




