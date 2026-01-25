package com.mini.feedback.service;

import com.mini.feedback.domain.FeedbackDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.feedback.domain.dto.FeedbackDTO;
import com.mini.resultvo.ResultVO;

/**
* @author 19256
* @description 针对表【feedback(反馈表
)】的数据库操作Service
* @createDate 2025-06-03 15:03:39
*/
public interface FeedbackService extends IService<FeedbackDO> {
    ResultVO<Void> add(FeedbackDTO feedbackDTO);

}
