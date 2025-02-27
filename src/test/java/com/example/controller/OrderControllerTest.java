package com.example.controller;

import org.example.controller.OrderController;
import org.example.model.Order;
import org.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrders_returnsListOfOrders() {
        Order order = new Order();
        when(orderService.getAllOrders()).thenReturn(List.of(order));

        List<Order> orders = orderController.getAllOrders();

        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void getAllOrders_returnsEmptyListWhenNoOrders() {
        when(orderService.getAllOrders()).thenReturn(Collections.emptyList());

        List<Order> orders = orderController.getAllOrders();

        assertNotNull(orders);
        assertTrue(orders.isEmpty());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void getOrderById_returnsOrderWhenFound() {
        Order order = new Order();
        when(orderService.getOrderById("1")).thenReturn(Optional.of(order));

        ResponseEntity<Order> response = orderController.getOrderById("1");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).getOrderById("1");
    }

    @Test
    void getOrderById_returnsNotFoundWhenOrderNotFound() {
        when(orderService.getOrderById("1")).thenReturn(Optional.empty());

        ResponseEntity<Order> response = orderController.getOrderById("1");

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(orderService, times(1)).getOrderById("1");
    }

    @Test
    void createOrder_createsAndReturnsOrder() {
        Order order = new Order();
        when(orderService.createOrder(order)).thenReturn(order);

        Order createdOrder = orderController.createOrder(order);

        assertNotNull(createdOrder);
        assertEquals(order, createdOrder);
        verify(orderService, times(1)).createOrder(order);
    }

    @Test
    void deleteOrder_deletesOrder() {
        doNothing().when(orderService).deleteOrder("1");

        ResponseEntity<Void> response = orderController.deleteOrder("1");

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(orderService, times(1)).deleteOrder("1");
    }
}