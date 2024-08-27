package com.eeezi.boardApp.model.entity;

import com.eeezi.boardApp.model.DeleteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SQLDelete(sql="UPDATE comment SET DELETE_STATUS = 'DELETE' WHERE comment_id = ?")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;
}
