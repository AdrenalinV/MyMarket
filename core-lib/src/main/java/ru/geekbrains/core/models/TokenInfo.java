package ru.geekbrains.core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;


@NoArgsConstructor
@Setter
@Getter
public class TokenInfo implements Serializable {
    @Id
    private String token;

    private Long userId;

    public TokenInfo(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}
