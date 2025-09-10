package com.example.orders.service;

import com.example.orders.model.Order;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {
    private final List<Order> store = new ArrayList<>();

    // simple business metrics
    private final Counter createdCount;
    private final Counter invalidCount;
    private final Timer createLatency;

    public OrderService(MeterRegistry registry) {
        this.createdCount = Counter.builder("orders.created.count").register(registry);
        this.invalidCount = Counter.builder("orders.invalid.count").register(registry);
        this.createLatency = Timer.builder("orders.create.latency")
                .publishPercentileHistogram()
                .publishPercentiles(0.5, 0.95, 0.99)
                .register(registry);
    }

    public Order createOrder(Order order) {
        long start = System.nanoTime();

        // super basic validation just to exercise metrics
        if (order.getQty() <= 0 || order.getPriceUsd() <= 0) {
            invalidCount.increment();
            // return unchanged (controller can keep returning it), or throw if you prefer
            return order;
        }

        store.add(order);
        createdCount.increment();

        createLatency.record(System.nanoTime() - start, TimeUnit.NANOSECONDS);
        return order;
    }

    public List<Order> getAllOrders() {
        return store;
    }
}
