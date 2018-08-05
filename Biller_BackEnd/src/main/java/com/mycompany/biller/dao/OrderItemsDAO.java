/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.OrderItems;
import com.mycompany.biller.dto.Orders;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public interface OrderItemsDAO {

    public OrderItems create(OrderItems orderItems);

    public OrderItems update(OrderItems orderItems);

    public void delete(OrderItems orderItems);

    public int deleteByOrderId(Long orderId);

    public List<OrderItems> listAll();

    public OrderItems findById(Long orderItemsId);
    
    public  List<OrderItems> findByOrderId (Long orderId);
}
