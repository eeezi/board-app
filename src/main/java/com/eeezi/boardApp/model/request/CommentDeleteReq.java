package com.eeezi.boardApp.model.request;

import lombok.Data;

@Data
public class CommentDeleteReq {
    private Long boardId;
    private Long commentId;
}
