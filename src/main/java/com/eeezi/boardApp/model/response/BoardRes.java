package com.eeezi.boardApp.model.response;

import com.eeezi.boardApp.model.DeleteStatus;
import com.eeezi.boardApp.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data @AllArgsConstructor
public class BoardRes {
    private Long boardId;
    private String title;
    private String content;
    private DeleteStatus deleteStatus;
    private List<CommentRes> comments;

    public static BoardRes from(Board board) {
        return new BoardRes(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getDeleteStatus(),
                board.getComments().stream().map(CommentRes::from).collect(Collectors.toList()));
    }
}
