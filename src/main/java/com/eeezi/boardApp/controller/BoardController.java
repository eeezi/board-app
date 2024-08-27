package com.eeezi.boardApp.controller;

import com.eeezi.boardApp.model.request.BoardDeleteReq;
import com.eeezi.boardApp.model.request.BoardPostReq;
import com.eeezi.boardApp.model.response.BoardListRes;
import com.eeezi.boardApp.model.response.BoardRes;
import com.eeezi.boardApp.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public BoardRes writeBoard(@RequestBody BoardPostReq boardPostReq) {
        return boardService.writeBoard(boardPostReq);
    }

    @GetMapping("/boards")
    public List<BoardListRes> searchBoardList(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return boardService.searchBoardList(page, pageSize);
    }

    @GetMapping
    public BoardRes searchBoard(@RequestParam("boardId") Long boardId) {
        return boardService.searchBoard(boardId);
    }

    @PostMapping("/board/{boardId}")
    public Long updateBoard(@RequestParam("boardId") Long boardId, @RequestBody BoardPostReq boardPostReq) {
        return boardService.updateBoard(boardId, boardPostReq);
    }

    @PostMapping("/board/delete")
    public String deleteBoard(@RequestBody BoardDeleteReq boardDeleteReq) {
        return boardService.deleteBoard(boardDeleteReq);
    }
}
