/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.*;
import com.mycompany.biller.dto.*;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.OrdersService;

import java.util.Date;

/**
 * @author Admin
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDAO orderDAO;

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Autowired
    private InvoiceItemDAO invoiceItemDAO;

    @Autowired
    private OrderItemsDAO orderItemsDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Override
    public Orders create(Orders order) {
        if (order.getToParty().getPartyId() == null || order.getFromParty().getPartyId() == null) {
            throw new NotFoundException("ToParty or FromParty are Null");
        }

        if (partyDAO.findById(order.getToParty().getPartyId()) == null) {
            throw new NotFoundException("To Party Not Found");
        }

        return orderDAO.create(order);
    }

    @Override
    public Orders update(Orders order) {
        return orderDAO.update(order);
    }

    @Override
    public void delete(Long orderId) {
        Orders orders = orderDAO.findById(orderId);
        if (orders == null) {
            throw new NotFoundException("Orders Information Not Exist");
        }
        orderItemsDAO.deleteByOrderId(orderId);
        orderDAO.delete(orders);
    }

    @Override
    public int changeStatus(Long orderId, String status) {
        Orders orders = orderDAO.findById(orderId);
        if (orders == null) {
            throw new NotFoundException("Orders Information Not Exist");
        }
        return orderDAO.changeStatus(orderId, status);
    }

    @Override
    public List<Orders> listAll() {
        return orderDAO.listAll();
    }

    @Override
    public List<Orders> listAll(int page, int size) {
        return orderDAO.listAll(page,size);
    }

    @Override
    public Orders findById(Long orderId) {
        return orderDAO.findById(orderId);
    }

    @Override
    public List<Orders> findByFromPartyId(Long fromPartyId) {
        return orderDAO.findByFromPartyId(fromPartyId);
    }

    @Override
    public List<Orders> findByToPartyId(Long toPartyId) {
        return orderDAO.findByToPartyId(toPartyId);
    }

    @Override
    public List<Orders> findTo(Long fromPartyId) {
        return orderDAO.findTo(fromPartyId);
    }

    @Override
    public List<Orders> findFrom(Long toPartyId) {
        return orderDAO.findFrom(toPartyId);
    }

    @Override
    public List<Orders> findByCriteria(Long orderId, String orderCode, String name, String description, GlobalItem globalItem, Date orderDateLess, Date orderDateGreater, Party toParty, Party fromParty) {
        return orderDAO.findByCriteria(orderId, orderCode, name, description, globalItem, orderDateLess, orderDateGreater, toParty, fromParty);
    }

    //service to create invoice from order
    @Override
    public Long createOrderInvoice(Long orderId) {

        Orders order = orderDAO.findById(orderId);
        Invoice invoice = new Invoice();

        GlobalItem invoiceStatus = new GlobalItem();
        invoiceStatus.setGlobalItemId("INVOICE_CREATED");

        GlobalItem invoiceType = new GlobalItem();
        invoiceType.setGlobalItemId("INVOICE_ITEM");

        //to get current date
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        invoice.setOrder(order);
        invoice.setDescription(order.getDescription());
        invoice.setFromParty(order.getFromParty());
        invoice.setToParty(order.getToParty());
        invoice.setStatus(invoiceStatus);
        invoice.setInvoiceTypeId(invoiceType);
        invoice.setCreatedBy(order.getCreatedBy());
        invoice.setInvoiceDate(date);
        invoice.setPaidNumber(order.getOrderId());
        invoice.setReferenceNumber(order.getOrderId());
        invoice.setIsPartially('N');

        invoice = invoiceDAO.create(invoice);

        //to create invoice-items based on order-items
        for (OrderItems orderItems : order.getOrderItems()) {
            InvoiceItem invoiceItem = new InvoiceItem();

            invoiceItem.setInvoice(invoice);
            invoiceItem.setCategory(invoiceItem.getCategory());
            invoiceItem.setDescription(orderItems.getItem().getBrandName());
            invoiceItem.setItem(orderItems.getItem());
            invoiceItem.setUnit(orderItems.getUnit());
            invoiceItem.setQuantity((long) orderItems.getQuantity());
            invoiceItem.setUnitPrice(orderItems.getUnitPrice());
            invoiceItem.setCreatedBy(invoice.getCreatedBy());

            invoiceItemDAO.create(invoiceItem);
        }

        return invoice.getInvoiceId();
    }

    @Override
    public Orders findByOrderIdAndFromPartyId(Long orderId, Long fromPartyId) {
        Orders orders = orderDAO.findById(orderId);
        if (orders == null) {
            throw new NotFoundException("Orders Information Not Exist");
        }
        Party fromParty = partyDAO.findById(fromPartyId);
        if (fromParty == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        return orderDAO.findByOrderIdAndFromPartyId(orders, fromParty);
    }

    @Override
    public Orders findByOrderIdAndToPartyId(Long orderId, Long toPartyId) {
        Orders orders = orderDAO.findById(orderId);
        if (orders == null) {
            throw new NotFoundException("Orders Information Not Exist");
        }
        Party toParty = partyDAO.findById(toPartyId);
        if (toParty == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        return orderDAO.findByOrderIdAndToPartyId(orders, toParty);
    }

}
