package web.restapiproject.modules.board.entity;

import jakarta.persistence.*;
import lombok.*;
import web.restapiproject.common.entity.BaseEntity;

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

}
