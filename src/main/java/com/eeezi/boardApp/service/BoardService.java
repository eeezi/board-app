package com.eeezi.boardApp.service;

import com.eeezi.boardApp.model.DeleteStatus;
import com.eeezi.boardApp.model.entity.Board;
import com.eeezi.boardApp.model.request.BoardDeleteReq;
import com.eeezi.boardApp.model.request.BoardPostReq;
import com.eeezi.boardApp.model.response.BoardListRes;
import com.eeezi.boardApp.model.response.BoardRes;
import com.eeezi.boardApp.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardRes writeBoard(BoardPostReq req) {
        Board board = new Board();
        board.setTitle(req.getTitle());
        board.setContent(req.getContent());
        board.setDeleteStatus(DeleteStatus.ACTIVE);
        return BoardRes.from(boardRepository.save(board));
    }

    public List<BoardListRes> searchBoardList(int page, int pageSize) {
        return boardRepository.findAllByDeleteStatus(
                DeleteStatus.ACTIVE,
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "boardId"))
        ).map(BoardListRes::from).toList();
    }

    public BoardRes searchBoard(Long boardId) {
        return boardRepository.findBoardWithCommentsByBoardId(boardId)
                .map(BoardRes::from)
                .orElseThrow(() -> new RuntimeException("Post Not Found"));
    }

    public Long updateBoard(Long boardId, BoardPostReq req) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("Post Not Found"));

        board.setTitle(req.getTitle());
        board.setContent(req.getContent());

        return boardRepository.save(board).getBoardId();
    }

    public String deleteBoard(BoardDeleteReq req) {
        Optional<Board> boardOptional = boardRepository.findById(req.getBoardId());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("Post Not Found"));

        boardRepository.delete(board);

        return "OK";
    }
}
