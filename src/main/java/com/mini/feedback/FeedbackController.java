package com.mini.feedback;

import com.mini.feedback.domain.dto.FeedbackDTO;
import com.mini.feedback.service.FeedbackService;
import com.mini.resultvo.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService){
        this.feedbackService=feedbackService;
    }

    @PostMapping("/add")
    public ResultVO<Void> add(@RequestBody FeedbackDTO feedbackDTO){
        return this.feedbackService.add(feedbackDTO);
    }
}
