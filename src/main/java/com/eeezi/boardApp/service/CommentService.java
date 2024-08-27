package com.eeezi.boardApp.service;

import com.eeezi.boardApp.model.DeleteStatus;
import com.eeezi.boardApp.model.entity.Board;
import com.eeezi.boardApp.model.entity.Comment;
import com.eeezi.boardApp.model.request.CommentDeleteReq;
import com.eeezi.boardApp.model.request.CommentPostReq;
import com.eeezi.boardApp.model.response.BoardRes;
import com.eeezi.boardApp.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;

    public BoardRes writeComment(CommentPostReq req) {
        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardId(req.getBoardId());
        Board board = boardOptional.orElseThrow(() -> new IllegalArgumentException("Post not found"));

        board.addComment(req.getCommentContent());
        boardRepository.save(board);

        return BoardRes.from(board);
    }

    public Long updateComment(Long commentId, CommentPostReq req) {
        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardId(req.getBoardId());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("Post Not Found"));
        board.getComments().stream()
                .filter(comment -> comment.getCommentId().equals(commentId))
                .findFirst()
                .ifPresent(comment -> {
                    comment.setContent(req.getCommentContent());
                });
        return commentId;
    }

    public String deleteComment(CommentDeleteReq req) {
        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardId(req.getBoardId());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("Post Not Found"));

        board.getComments().stream()
                .filter(comment -> comment.getCommentId().equals(req.getCommentId()))
                .findFirst()
                .ifPresent(comment -> {
                    comment.setDeleteStatus(DeleteStatus.DELETE);
                });

        return "OK";
    }
}
