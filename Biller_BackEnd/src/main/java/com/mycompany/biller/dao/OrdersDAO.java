/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.Party;

import java.util.Date;
import java.util.List;

/**
 * @author Admin
 */
public interface OrdersDAO {

    public Orders create(Orders orders);

    public Orders update(Orders orders);

    public void delete(Orders orders);

    public int changeStatus(Long orderId, String status);

    public List<Orders> listAll();

    public List<Orders> listAll(int page , int size);

    public Orders findById(Long orderId);

    public List<Orders> findByFromPartyId(Long fromPartyId);

    public List<Orders> findByToPartyId(Long toPartyId);

    public List<Orders> findTo(Long fromPartyId);

    public List<Orders> findFrom(Long toPartyId);

    public List<Orders> findByCriteria(Long orderId, String orderCode, String name, String description, GlobalItem globalItem, Date orderDateLess, Date orderDateGreater, Party toParty, Party fromParty);

    public Orders findByOrderIdAndFromPartyId(Orders orders, Party fromParty);

    public Orders findByOrderIdAndToPartyId(Orders orders, Party toParty);
}
