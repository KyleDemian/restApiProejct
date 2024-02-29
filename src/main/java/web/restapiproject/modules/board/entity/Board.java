package web.restapiproject.modules.board.entity;

import jakarta.persistence.*;
import lombok.*;
import web.restapiproject.common.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    // mappedBy 가 있을경우 @JoinColumn(name = "comment_id") 사용 불가.
    @Builder.Default
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BoardComment> comments = new ArrayList<>();
}
