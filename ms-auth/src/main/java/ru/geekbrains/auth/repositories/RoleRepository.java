package ru.geekbrains.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.auth.entityes.Role;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
