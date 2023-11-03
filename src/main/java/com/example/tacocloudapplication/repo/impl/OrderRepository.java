package com.example.tacocloudapplication.repo.impl;

import com.example.tacocloudapplication.table.TacoOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readTacoOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate);

    @Query("select order from TacoOrder order where order.user.id = :userId order by order.placedAt")
    List<TacoOrder> findTacoOrdersByUserIdOrderByPlacedAtDate(Long userId, Pageable pageable);

}
