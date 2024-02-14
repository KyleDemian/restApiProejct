package web.restapiproject.modules.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import web.restapiproject.common.entity.BaseEntity;
import web.restapiproject.enums.Gender;


@Entity
@Getter @Setter
//@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor @Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;

    @Column(unique = true)
    private String email;

    private String password;

//    private Gender gender;

    private String nickname;

    private String info;
}
