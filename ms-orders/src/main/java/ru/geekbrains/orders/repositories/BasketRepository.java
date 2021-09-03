package ru.geekbrains.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.orders.models.Basket;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BasketRepository extends JpaRepository<Basket, UUID> {
    @Query("select b from Basket b where b.userId = ?1")
    Optional<Basket> findByUserId(Long id);
}
