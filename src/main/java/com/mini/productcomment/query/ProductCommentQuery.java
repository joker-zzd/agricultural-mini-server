package com.mini.productcomment.query;

import com.mini.common.query.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@ApiModel(description = "评价分页查询条件")
public class ProductCommentQuery extends PageQuery {

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "评分")
    private Integer score;

    @ApiModelProperty(value = "开始时间")
    private LocalDate startDate;

    @ApiModelProperty(value = "结束时间")
    private LocalDate endDate;
}
