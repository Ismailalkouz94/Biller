/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.UserLogin;
import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class OrdersResources extends ResourceSupport {

    private Long orderId;
    private String orderCode;
    private String name;
    private String description;
    private String globalItemId;
    private Date orderDate;
    private Long toPartyId;
    private Long fromPartyId;
    private Long userLoginId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGlobalItemId() {
        return globalItemId;
    }

    public void setGlobalItemId(String globalItemId) {
        this.globalItemId = globalItemId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getFromPartyId() {
        return fromPartyId;
    }

    public void setFromPartyId(Long fromPartyId) {
        this.fromPartyId = fromPartyId;
    }

    public Long getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(Long userLoginId) {
        this.userLoginId = userLoginId;
    }

    public Long getToPartyId() {
        return toPartyId;
    }

    public void setToPartyId(Long toPartyId) {
        this.toPartyId = toPartyId;
    }

    public Orders toOrder() {
        Party toParty = new Party();
        toParty.setPartyId(toPartyId);

        Party fromParty = new Party();
        fromParty.setPartyId(fromPartyId);

        UserLogin createdBy = new UserLogin();
        createdBy.setUserLoginId(userLoginId);

        GlobalItem globalItem = new GlobalItem();
        globalItem.setGlobalItemId(globalItemId);

        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setOrderCode(orderCode);
        order.setName(name);
        order.setDescription(description);
        order.setGlobalItem(globalItem);
        order.setOrderDate(orderDate);
        order.setToParty(toParty);
        order.setFromParty(fromParty);
        order.setCreatedBy(createdBy);
        
        return order;
    }
}
