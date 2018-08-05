/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.OrderItems;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public interface OrderItemsService {

    public OrderItems create(OrderItems orderItems);

    public OrderItems update(OrderItems orderItems);

    public void delete(Long orderItemsId);

    public List<OrderItems> listAll();

    public OrderItems findById(Long orderItemsId);

    public List<OrderItems> findByOrderId(Long orderId);
}
