package ru.geekbrains.core.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ru.geekbrains.core.models.TokenInfo;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void add(TokenInfo token) {
        redisTemplate.opsForValue().set(token.getToken(), token.getUserId(), Duration.ofHours(1));
    }

    public boolean existTokenInfo(String token) {
        return redisTemplate.opsForValue().get(token) != null;
    }

}
