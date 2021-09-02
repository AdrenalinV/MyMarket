package ru.geekbrains.routing.interfeces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.geekbrains.routing.dtos.AuthRequestDto;
import ru.geekbrains.routing.dtos.AuthResponseDto;
import ru.geekbrains.routing.dtos.SignUpRequestDto;

@FeignClient("ms-auth")
public interface AuthClient {
    @PostMapping("/api/v1/auth/signup")
    void registerUser(@RequestBody SignUpRequestDto signUpRequest);

    @PostMapping("/api/v1/auth/login")
    AuthResponseDto login(@RequestBody AuthRequestDto request);


}
