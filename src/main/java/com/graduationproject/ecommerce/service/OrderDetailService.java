package com.graduationproject.ecommerce.service;

import com.graduationproject.ecommerce.entity.OrderDetail;
import com.graduationproject.ecommerce.exception.customexception.CustomeException;
import com.graduationproject.ecommerce.exception.customexception.OrderNotFoundException;
import com.graduationproject.ecommerce.repository.OrderDetailRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    
    @Transactional
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
    
    public OrderDetail findById(Long id) {
        return orderDetailRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(CustomeException.ORDER_NOT_FOUND_EXCEPTION));
    }
    
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }
    
    @Transactional
    public void delete(Long id) {
        orderDetailRepository.deleteById(findById(id).getId());
    }
}
