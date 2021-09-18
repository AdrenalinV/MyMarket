package ru.geekbrains.routing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestDto {
    private String username;
    private String password;
}
