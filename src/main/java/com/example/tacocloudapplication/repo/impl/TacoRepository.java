package com.example.tacocloudapplication.repo.impl;

import com.example.tacocloudapplication.table.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TacoRepository extends JpaRepository<Taco, Long> {

    List<Taco> findByTacoOrderIsNullAndCreatedByUserId(Long userId);

    Integer deleteTacoById(Long tacoId);

    @Modifying
    @Query(value = "DELETE FROM ingredient_tacos WHERE taco_id = :tacoId", nativeQuery = true)
    void deleteEntriesFromIngredientTaco(@Param("tacoId") Long tacoId);

    @Modifying
    @Query(value = "UPDATE taco SET taco_order_id = :orderId WHERE id = :tacoId", nativeQuery = true)
    void associateOrderToTaco(@Param("orderId") Long orderId, @Param("tacoId") Long tacoId);
}
