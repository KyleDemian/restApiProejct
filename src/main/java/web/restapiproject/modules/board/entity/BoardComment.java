package web.restapiproject.modules.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import web.restapiproject.common.entity.BaseEntity;

@Entity
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class BoardComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
}
