package ru.geekbrains.core.repositories;


import ru.geekbrains.core.models.TokenInfo;

import java.util.Map;


public interface RedisRepository {
//     Add key-value pair to Redis.
    void add(TokenInfo token);

//    Determine if given hash hashKey exists.
    boolean existTokenInfo(String token);

}
