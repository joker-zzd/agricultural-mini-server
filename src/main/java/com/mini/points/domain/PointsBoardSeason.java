package com.mini.points.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 积分排行榜赛季表
 * @TableName points_board_season
 */
@TableName(value ="points_board_season")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PointsBoardSeason implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 赛季ID（自增，赛季标识）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 赛季名称，例如：第1积分赛季
     */
    @TableField(value = "name")
    private String name;

    /**
     * 赛季开始时间
     */
    @TableField(value = "begin_time")
    private LocalDate beginTime;

    /**
     * 赛季结束时间
     */
    @TableField(value = "end_time")
    private LocalDate endTime;
}