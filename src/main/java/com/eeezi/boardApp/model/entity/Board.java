package com.eeezi.boardApp.model.entity;

import com.eeezi.boardApp.model.DeleteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    @Column(length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @SQLRestriction("DELETE_STATUS = 'ACTIVE'")
    private List<Comment> comments = new ArrayList<>();

    public Board addComment(String comentContent) {
        Comment comment = new Comment();
        comment.setContent(comentContent);
        comment.setDeleteStatus(DeleteStatus.ACTIVE);
        return this;
    }
}
