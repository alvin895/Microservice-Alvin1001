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

import com.alvin.produk.model.order;
import com.alvin.produk.service.orderService;

@RestController
@RequestMapping("/api/order")
public class orderController {
    @Autowired
    private orderService orderService;

    @GetMapping
    public List<order> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public ResponseEntity<order> getOrderById(@PathVariable Long id) {
        order o = orderService.getOrderById(id);
        return o != null ? ResponseEntity.ok(o) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<order> createOrder(@RequestBody order order) {
        order created = orderService.createOrder(order);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}