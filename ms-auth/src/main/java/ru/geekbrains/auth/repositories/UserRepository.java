package ru.geekbrains.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.auth.entityes.User;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    Optional<User> findById(Long id);
}
