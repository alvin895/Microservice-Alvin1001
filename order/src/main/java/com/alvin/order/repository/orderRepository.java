package com.alvin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvin.order.model.order;

@Repository
public interface orderRepository extends JpaRepository<order, Long> {
}