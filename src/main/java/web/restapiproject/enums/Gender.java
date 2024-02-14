package web.restapiproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MAN("man"),
    WOMAN("woman")
    ;

    private final String gender;
}
