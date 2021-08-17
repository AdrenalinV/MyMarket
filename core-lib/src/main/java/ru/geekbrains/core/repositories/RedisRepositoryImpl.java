package ru.geekbrains.core.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.geekbrains.core.models.TokenInfo;

import javax.annotation.PostConstruct;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private static final String KEY = "TOKEN";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void add(TokenInfo token) {
        hashOperations.put(KEY, token.getToken(), token.getUserId());
    }


    @Override
    public boolean existTokenInfo(String token) {
        return hashOperations.hasKey(KEY,token);
    }

}
