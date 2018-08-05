/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.OrderItemsDAO;
import com.mycompany.biller.dto.OrderItems;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.OrderItemsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsDAO orderItemsDAO;

    @Override
    public OrderItems create(OrderItems orderItems) {
        return orderItemsDAO.create(orderItems);
    }

    @Override
    public OrderItems update(OrderItems orderItems) {
        return orderItemsDAO.update(orderItems);
    }

    @Override
    public void delete(Long orderItemsId) {
        OrderItems orderItems = orderItemsDAO.findById(orderItemsId);
        if (orderItems == null) {
            throw new NotFoundException("Order Items Information Not Exist");
        }
        orderItemsDAO.delete(orderItems);
    }

    @Override
    public List<OrderItems> listAll() {
        return orderItemsDAO.listAll();
    }

    @Override
    public OrderItems findById(Long orderItemsId) {
        return orderItemsDAO.findById(orderItemsId);
    }

    @Override
    public List<OrderItems> findByOrderId(Long orderId) {
        return orderItemsDAO.findByOrderId(orderId);
    }

}
