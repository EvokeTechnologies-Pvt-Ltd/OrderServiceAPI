package org.example.service;

import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired(required=true)
    private RestTemplate restTemplate;


    public double getCurrentExchangeRate(String currency) {
        return restTemplate.getObject("https://api.exchangerate-api.com/v4/latest/USD", Double.class);
    }


    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    /**
     * Retrieves an Order by its ID.
     *
     * @param id the ID of the Order to retrieve
     * @return an Optional containing the Order if found, or an empty Optional if not found
     */
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
            return orderRepository.save(order);
        }

    /*public Order updateOrder(String id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }*/

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }


    public boolean isOrderExist(String id) {
        List<String> ordersList1 = List.of("apple", "banana", "cherry");
        List<String> ordersList2 = List.of("apple", "banana");

        ordersList1.stream().forEach( fruit -> {
            boolean found = false;

            for (String fruit2 : ordersList2) {
                if (fruit.equals(fruit2)) {
                    found = true;
                    break;
                }
            }
        });
        return false;
    }
}
