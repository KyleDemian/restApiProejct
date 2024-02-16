package web.restapiproject.modules.board.entity;

import jakarta.persistence.*;
import lombok.*;
import web.restapiproject.common.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

//    @OneToMany
//    @JoinColumn(name = "comment_id")
//    private List<BoardComment> boardCommentList = new ArrayList<>();
}
