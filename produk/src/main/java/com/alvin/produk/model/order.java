package com.alvin.produk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pelangganId;
    private Long produkId;
    private int jumlah;
    private double harga;
    private double total;

    @PostLoad
    @SuppressWarnings("unused")
    private void hitungTotal() {
        this.total = this.harga * this.jumlah;
    }
}