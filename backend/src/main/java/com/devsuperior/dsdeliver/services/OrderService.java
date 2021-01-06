package com.devsuperior.dsdeliver.services;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.entities.enums.OrderStatus;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {

        List<Order> lista = orderRepository.findOrderWithProducts();

        return lista.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {

        Order order = new Order(null, orderDTO.getAddress(), orderDTO.getLatitude(), orderDTO.getLongitude(),
                Instant.now(), OrderStatus.PENDING);

        for (ProductDTO p : orderDTO.getProducts()) {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }

        order = orderRepository.save(order);

        return new OrderDTO(order);
    }
}
