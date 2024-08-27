package com.eeezi.boardApp.model.request;

import lombok.Data;

@Data
public class CommentPostReq {
    private Long boardId;
    private String commentContent;
}
