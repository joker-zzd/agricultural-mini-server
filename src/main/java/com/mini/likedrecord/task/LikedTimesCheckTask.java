package com.mini.likedrecord.task;

import com.mini.likedrecord.service.LikedRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mini.common.constants.BizType.PRODUCT_EVALUATION;

@Component
@RequiredArgsConstructor
public class LikedTimesCheckTask {
    // 业务类型
    private static final List<String> BIZ_TYPES = List.of(PRODUCT_EVALUATION);
    //每次发送30个
    private static final int MAX_BIZ_SIZE = 30;

    private final LikedRecordService likedRecordService;

    /**
     * 每20秒检查一次点赞次数，每个业务类型每次发送30个业务总点赞记录
     *
     */
    @Scheduled(fixedDelay = 20 * 1000)
    public void checkLikedTimes() {
        for (String bizType : BIZ_TYPES) {
            likedRecordService.readLikedTimesAndSendMessage(bizType, MAX_BIZ_SIZE);
        }
    }
}
