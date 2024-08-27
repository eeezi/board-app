package com.eeezi.boardApp.repository;

import com.eeezi.boardApp.model.DeleteStatus;
import com.eeezi.boardApp.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByDeleteStatus(DeleteStatus deletestatus, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.comments WHERE b.boardId = :boardId")
    Optional<Board> findBoardWithCommentsByBoardId(@Param("boardId") Long boardId);
}
