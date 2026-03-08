package com.mini.productcomment.mapper;

import com.mini.productcomment.domain.ProductComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

/**
 * @author 19256
 * @description 针对表【product_comment(商品评价与回复表)】的数据库操作Mapper
 * @createDate 2026-02-19 15:11:36
 * @Entity com.mini.productcomment.domain.ProductComment
 */
@Mapper
public interface ProductCommentMapper extends BaseMapper<ProductComment> {
    @Update("""
                UPDATE product_comment
                SET reply_times = reply_times + 1
                WHERE id = #{id}
            """)
    void increaseReplyTimes(@Param("id") Long id);

}




