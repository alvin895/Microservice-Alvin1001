package com.alvin.produk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvin.produk.model.produk;
import com.alvin.produk.repository.produkRepository;

@Service
public class produkService {
    @Autowired
    private produkRepository produkRepository;

    public List<produk> getAllProduks() {
        return produkRepository.findAll();
    }

    public produk getProdukById(Long id) {
        return produkRepository.findById(id).orElse(null);
    }

    public produk createProduk(produk produk) {
        return produkRepository.save(produk);
    }

    public void deleteProduk(Long id) {
        produkRepository.deleteById(id);
    }
}
