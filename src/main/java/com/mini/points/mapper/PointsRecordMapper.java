package com.mini.points.mapper;

import com.mini.points.domain.PointsRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【points_record(用户积分流水记录表（按月统计/清零）)】的数据库操作Mapper
* @createDate 2026-02-28 01:14:23
* @Entity com.mini.points.domain.PointsRecord
*/
@Mapper
public interface PointsRecordMapper extends BaseMapper<PointsRecord> {

}




