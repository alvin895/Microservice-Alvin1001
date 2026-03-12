package com.alvin.pelanggan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvin.pelanggan.model.pelanggan;

@Repository
public interface pelangganRepository extends JpaRepository<pelanggan, Long> {
}