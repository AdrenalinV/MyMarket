package ru.geekbrains.auth.dtos;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;
}
