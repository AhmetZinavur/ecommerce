package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.dto.request.create.OrderRequest;
import com.graduationproject.ecommerce.dto.request.update.ProductUpdateRequest;
import com.graduationproject.ecommerce.dto.request.update.UpdateOrderStatusRequest;
import com.graduationproject.ecommerce.dto.response.ProductResponse;
import com.graduationproject.ecommerce.dto.response.UserResponse;
import com.graduationproject.ecommerce.entity.Order;
import com.graduationproject.ecommerce.entity.OrderDetail;
import com.graduationproject.ecommerce.entity.Product;
import com.graduationproject.ecommerce.entity.User;
import com.graduationproject.ecommerce.entity.enums.OrderStatus;
import com.graduationproject.ecommerce.entity.enums.Role;
import com.graduationproject.ecommerce.exception.customexception.ApproveOrRejectExcepiton;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.InvalidTokenException;
import com.graduationproject.ecommerce.exception.customexception.NotEnoughStockException;
import com.graduationproject.ecommerce.exception.customexception.OrderNotFoundException;
import com.graduationproject.ecommerce.exception.customexception.UnauthorizedAccessException;
import org.springframework.stereotype.Service;

import com.graduationproject.ecommerce.repository.OrderRepository;
import com.graduationproject.ecommerce.util.JWTManager;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final JWTManager jWTManager;

    @Transactional
    public void addOrder(String token, OrderRequest orderRequest) {
        
        UserResponse userResponse = userService.findById(jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION)));
        
        if(userResponse.getRole().equals(Role.STOREOWNER.toString()) && userResponse.equals(Role.ADMIN.toString())) {
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS_EXCEPTION);
        }
        
        ProductResponse pr = productService.findById(orderRequest.getProductId());
        
        if(pr.getProductQuantity() < orderRequest.getProductQuantity()) {
            throw new NotEnoughStockException(CustomeException.NOT_ENOUGH_STOCK_EXCEPTION);
        }
        
        Order o = new Order();
        o.setCreateAt(LocalDateTime.now());
        o.setOrderPrice(orderRequest.getProductQuantity() * pr.getProductPrice());
        o.setOrderStatus(OrderStatus.WAIT);
        o.setUser(User.builder()
                .id(userResponse.getId())
                .build());
        
        orderRepository.save(o);
        
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(o);
        orderDetail.setProduct(Product.builder()
                .id(pr.getId())
                .build());
        orderDetail.setProductQuantity(orderRequest.getProductQuantity());
        orderDetailService.create(orderDetail);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(CustomeException.ORDER_NOT_FOUND_EXCEPTION));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    protected void updateOrderStatus(String token, UpdateOrderStatusRequest request) {
        
        UserResponse userResponse = userService.findById(jWTManager.validToken(token).orElseThrow(() -> new InvalidTokenException(CustomeException.INVALID_TOKEN_EXCEPTION)));
        
        if(userResponse.getRole().equals(Role.CUSTOMER.toString()) && userResponse.equals(Role.ADMIN.toString())) {
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS_EXCEPTION);
        }
        
        Order o = findById(request.getId());
                
        switch (request.getOrderStatus()) {
            case "APPROVED" -> {
                OrderDetail detail = orderDetailService.findById(o.getId());
                
                ProductResponse pr = productService.findById(detail.getProduct().getId());
                
                ProductUpdateRequest updateRequest = new ProductUpdateRequest();
                updateRequest.setId(pr.getId());
                updateRequest.setProductName(pr.getProductName());
                updateRequest.setProductPrice(pr.getProductPrice());
                updateRequest.setStockQuantity(pr.getProductQuantity() - detail.getProductQuantity());
                productService.update(updateRequest);
                
                o.setOrderStatus(OrderStatus.APPROVED);
                orderRepository.save(o);
            }
            case "REJECTED" -> {
                o.setOrderStatus(OrderStatus.REJECTED);
                orderRepository.save(o);
            }
            default -> {
               throw new ApproveOrRejectExcepiton(CustomeException.APPROVE_OR_REJECT_EXCEPTION);
            }
        }
    }
}
