package com.example.orders.controller;

import com.example.orders.model.Order;
import com.example.orders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    // generates demo traffic so Datadog shows graphs
    @GetMapping("/seed")
    public String seed(@RequestParam(defaultValue = "50") int n) {
        for (int i = 0; i < n; i++) {
            Order ok = new Order();
            ok.setItem("ball");
            ok.setQty(1 + (i % 3));
            ok.setPriceUsd(9.99 + (i % 5));
            service.createOrder(ok);

            // every 10th request -> invalid
            if (i % 10 == 0) {
                Order bad = new Order();
                bad.setItem("bad");
                bad.setQty(0);
                bad.setPriceUsd(-1);
                service.createOrder(bad);
            }

            try { Thread.sleep(30); } catch (InterruptedException ignore) {}
        }
        return "seeded " + n;
    }
}
