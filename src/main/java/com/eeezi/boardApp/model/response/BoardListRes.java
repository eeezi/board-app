package com.eeezi.boardApp.model.response;

import com.eeezi.boardApp.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class BoardListRes {
    private Long boardId;
    private String title;

    public static BoardListRes from(Board board) {
        return new BoardListRes(
                board.getBoardId(),
                board.getTitle()
        );
    }
}
