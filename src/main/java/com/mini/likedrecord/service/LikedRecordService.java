package com.mini.likedrecord.service;

import com.mini.likedrecord.domain.LikedRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.likedrecord.domain.dto.LikeRecordFormDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author 19256
 * @description 针对表【liked_record(点赞记录表)】的数据库操作Service
 * @createDate 2026-02-20 00:28:56
 */
public interface LikedRecordService extends IService<LikedRecord> {

    void toggleLike(@Valid LikeRecordFormDTO dto);

    Set<Long> getLikedStatusByBizList(List<Long> ids);

    void readLikedTimesAndSendMessage(String bizType, int maxBizSize);
}
