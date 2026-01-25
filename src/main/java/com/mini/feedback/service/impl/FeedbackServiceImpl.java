package com.mini.feedback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.feedback.domain.FeedbackDO;
import com.mini.feedback.domain.dto.FeedbackDTO;
import com.mini.feedback.service.FeedbackService;
import com.mini.feedback.mapper.FeedbackMapper;
import com.mini.resultvo.ResultVO;
import org.springframework.stereotype.Service;

/**
* @author 19256
* @description 针对表【feedback(反馈表
)】的数据库操作Service实现
* @createDate 2025-06-03 15:03:39
*/
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, FeedbackDO>
    implements FeedbackService{

    private final FeedbackMapper feedbackMapper;

    FeedbackServiceImpl( FeedbackMapper feedbackMapper){
        this.feedbackMapper=feedbackMapper;
    }

    @Override
    public ResultVO<Void> add(FeedbackDTO feedbackDTO) {
        FeedbackDO feedbackDO=new FeedbackDO();
        feedbackDO.setContent(feedbackDO.getContent());
        feedbackDO.setImage(feedbackDTO.getImage());
        feedbackDO.setContactDetails(feedbackDTO.getContactDetails());

        boolean result = this.feedbackMapper.insert(feedbackDO)>0;
        if (result){
            return ResultVO.success("操作成功");
        }else{
            return ResultVO.fail();
        }
    }
}




