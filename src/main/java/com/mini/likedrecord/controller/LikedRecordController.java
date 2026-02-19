package com.mini.likedrecord.controller;

import com.mini.likedrecord.domain.dto.LikeRecordFormDTO;
import com.mini.likedrecord.service.LikedRecordService;
import com.mini.resultvo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "点赞相关接口")
@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikedRecordController {
    private final LikedRecordService likedRecordService;

    @ApiOperation("点赞或者取消赞")
    @PostMapping()
    public void toggleLike(@RequestBody @Valid LikeRecordFormDTO dto) {
        likedRecordService.toggleLike(dto);
    }
}
