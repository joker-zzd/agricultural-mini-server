package com.mini.video.controller;

import com.mini.resultvo.ResultVO;
import com.mini.video.domain.dto.VideoDTO;
import com.mini.video.domain.vo.VideoVO;
import com.mini.video.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/video")
@CrossOrigin
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/listOnlineVideos")
    public ResultVO<List<VideoVO>> listOnlineVideos(
            @RequestParam(name = "CurrentPage", defaultValue = "1", required = false) Integer CurrentPage,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return videoService.listOnlineVideos(CurrentPage, pageSize);
    }

    @PostMapping(value = "/addVideo", consumes = "multipart/form-data")
    public ResultVO<Void> addVideo(@ModelAttribute @Valid VideoDTO videoDTO) throws IOException {
        return videoService.addVideo(videoDTO);
    }

    @GetMapping("/findById/{id}")
    public ResultVO<VideoVO> findById(@PathVariable Long id) {
        return videoService.findById(id);
    }
}
