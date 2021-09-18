package ru.geekbrains.auth.controllers;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.auth.entityes.User;
import ru.geekbrains.auth.services.UserService;
import ru.geekbrains.core.interfaces.ITokenService;
import ru.geekbrains.core.models.TokenInfo;
import ru.geekbrains.core.models.UserInfo;
import ru.geekbrains.core.repositories.RedisRepository;
import ru.geekbrains.routing.dtos.AuthRequestDto;
import ru.geekbrains.routing.dtos.AuthResponseDto;
import ru.geekbrains.routing.dtos.SignUpRequestDto;


import java.util.ArrayList;
import java.util.List;

@Log
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private ITokenService iTokenService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setUserName(signUpRequest.getUsername());
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getName()));
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .role(roles)
                .build();
        String token = iTokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }

    @GetMapping("/logout")
    public boolean logout(@RequestHeader(name = "Authorization") String token) {
        redisRepository.add(new TokenInfo(token, iTokenService.parseToken(token.replace("Bearer ", "")).getUserId()));
        return true;
    }
}
