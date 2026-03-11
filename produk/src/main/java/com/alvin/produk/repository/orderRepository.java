package com.alvin.produk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvin.produk.model.order;

@Repository
public interface orderRepository extends JpaRepository<order, Long> {
}