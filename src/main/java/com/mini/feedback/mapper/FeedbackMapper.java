package com.mini.feedback.mapper;

import com.mini.feedback.domain.FeedbackDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【feedback(反馈表
)】的数据库操作Mapper
* @createDate 2025-06-03 15:03:39
* @Entity com.mini.feedback.domain.Feedback
*/
@Mapper
public interface FeedbackMapper extends BaseMapper<FeedbackDO> {

}




