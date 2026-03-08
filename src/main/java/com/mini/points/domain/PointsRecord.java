package com.mini.points.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.mini.points.enums.PointsRecordType;
import lombok.Data;

/**
 * 用户积分流水记录表（按月统计/清零）
 * @TableName points_record
 */
@TableName(value ="points_record")
@Data
public class PointsRecord {
    /**
     * 积分记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID（农资商城用户）
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 积分获取方式：1-下单消费，2-每日签到，3-商品评价，4-参与活动，5-其他奖励
     */
    @TableField(value = "type")
    private PointsRecordType type;

    /**
     * 积分值
     */
    @TableField(value = "points")
    private Integer points;

    /**
     * 积分产生时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}