package com.eeezi.boardApp.controller;

import com.eeezi.boardApp.model.request.CommentDeleteReq;
import com.eeezi.boardApp.model.request.CommentPostReq;
import com.eeezi.boardApp.model.response.BoardRes;
import com.eeezi.boardApp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public BoardRes writeComment(@RequestBody CommentPostReq commentPostReq) {
        return commentService.writeComment(commentPostReq);
    }

    @PostMapping("/comment/{commentId}")
    public Long updateComment(@RequestParam("commentId") Long commentId, @RequestBody CommentPostReq commentPostReq) {
        return commentService.updateComment(commentId, commentPostReq);
    }

    @PostMapping("/comment")
    public String deleteComment(@RequestBody CommentDeleteReq commentDeleteReq) {
        return commentService.deleteComment(commentDeleteReq);
    }
}
