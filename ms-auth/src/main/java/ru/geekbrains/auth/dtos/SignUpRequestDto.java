package ru.geekbrains.auth.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String username;
    private String password;

}
