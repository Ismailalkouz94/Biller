/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.OrderItems;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.dto.UserLogin;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class OrderItemsResources extends ResourceSupport {

    private Long orderItemId;
    private String description;
    private Double unitPrice;
    private Integer quantity;
    private Long categoryId;
    private Long itemId;
    private Long unitId;
    private Long orderId;
    private Long userLoginId;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public long getItemId() {
        return itemId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    
    
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(Long userLoginId) {
        this.userLoginId = userLoginId;
    }

    public OrderItems toOrderItems() {
        Category category=new Category();
        category.setCategoryId(categoryId);
        
        Items item = new Items();
        item.setItemId(itemId);

        Units unit = new Units();
        unit.setUnitId(unitId);

        Orders order = new Orders();
        order.setOrderId(orderId);

        UserLogin createdBy = new UserLogin();
        createdBy.setUserLoginId(userLoginId);
        
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemId(orderItemId);
        orderItems.setDescription(description);
        orderItems.setUnitPrice(unitPrice);
        orderItems.setQuantity(quantity);
        orderItems.setCategory(category);
        orderItems.setItem(item);
        orderItems.setOrder(order);
        orderItems.setUnit(unit);
        orderItems.setCreatedBy(createdBy);
        return orderItems;
    }
}
