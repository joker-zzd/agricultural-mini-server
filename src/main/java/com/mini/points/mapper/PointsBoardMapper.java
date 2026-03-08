package com.mini.points.mapper;

import com.mini.points.domain.PointsBoard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【points_board(赛季积分前100名)】的数据库操作Mapper
* @createDate 2026-02-28 01:14:11
* @Entity com.mini.points.domain.PointsBoard
*/
@Mapper
public interface PointsBoardMapper extends BaseMapper<PointsBoard> {

}




