package com.alvin.produk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvin.produk.model.produk;
import com.alvin.produk.service.produkService;

@RestController
@RequestMapping("/api/produk")
public class produkController {
    @Autowired
    private produkService produkService;

    @GetMapping
    public List<produk> getAllprodukService(){
        return produkService.getAllProduk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<produk> getProdukById(@PathVariable Long id) {
        produk produk = produkService.getProdukById(id);
        return produk != null ? ResponseEntity.ok(produk) : ResponseEntity.notFound().build();
    }

   @PostMapping
    public ResponseEntity<produk> createProduk (@RequestBody produk produk) {
        produk createdProduk = produkService.createProduk(produk);
        return ResponseEntity.ok(createdProduk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduk(@PathVariable Long id) {
        produkService.deleteProduk(id);
        return ResponseEntity.ok().build();
    }

}