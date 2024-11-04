package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.request.OrderRequest;
import com.graduationproject.ecommerce.dto.response.ProductResponse;
import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.entity.Order;
import com.graduationproject.ecommerce.entity.Product;
import com.graduationproject.ecommerce.entity.User;
import com.graduationproject.ecommerce.entity.enums.OrderStatus;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.OrderRepository;
import com.graduationproject.ecommerce.util.JWTManager;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final JWTManager jWTManager;

    public void addOrder(String token, List<OrderRequest> orderRequests) {
        Double totalPrice = 0.0;

        UserResponse userResponse = userService.findById(jWTManager.validToken(token).orElseThrow(() -> new RuntimeException("Not valid Token")));

        Set<Product> products = new HashSet<>();

        for (OrderRequest o : orderRequests) {
            ProductResponse productResponse = productService.findById(o.getProductId());
            products.add(Product.builder()
                    .id(productResponse.getId())
                    .build());
            totalPrice = (productResponse.getProductPrice() * o.getQuantity()) + totalPrice;
        }

        Order order = new Order();
        order.setProducts(products);
        order.setOrderPrice(totalPrice);
        order.setUser(User.builder().id(userResponse.getId()).build());
        order.setOrderStatus(OrderStatus.WAIT);
        order.setOrderDateTime(LocalDateTime.now());

        orderRepository.save(order);
    }

    public void findById() {

    }

    public void findAll() {

    }

    public void updateOrderStatus() {

    }
}
