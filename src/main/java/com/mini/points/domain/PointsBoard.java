package com.mini.points.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 赛季积分前100名
 * @TableName points_board
 */
@TableName(value ="points_board")
@Data
public class PointsBoard {
    /**
     * 榜单ID
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 积分值（用于农资商城积分排行）
     */
    @TableField(value = "points")
    private Integer points;

    /**
     * 排行榜名次，仅记录赛季前100名
     */
    @TableField(value = "rank")
    private Integer rank;

    /**
     * 积分赛季标识，例如：1-第一赛季，2-第二赛季
     */
    @TableField(value = "season")
    private Integer season;
}