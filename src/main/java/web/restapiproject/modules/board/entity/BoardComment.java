package web.restapiproject.modules.board.entity;

import jakarta.persistence.*;
import lombok.*;
import web.restapiproject.common.entity.BaseEntity;
import web.restapiproject.modules.member.entity.Member;

@Entity
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class BoardComment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "board_id", insertable = false, updatable = false)
    private Board board;

}
