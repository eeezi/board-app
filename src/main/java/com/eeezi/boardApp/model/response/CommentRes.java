package com.eeezi.boardApp.model.response;

import com.eeezi.boardApp.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CommentRes {
    private Long commentId;
    private String content;

    public static CommentRes from(Comment comment) {
        return new CommentRes(comment.getCommentId(), comment.getContent());
    }
}
