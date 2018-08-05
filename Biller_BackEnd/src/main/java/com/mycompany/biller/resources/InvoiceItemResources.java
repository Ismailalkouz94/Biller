/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceItem;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.dto.UserLogin;

/**
 *
 * @author Rbab3a
 */
public class InvoiceItemResources {

    private Long invoiceItemId;
    private Double unitPrice;
    private Long quantity;
    private Long itemId;
    private Long unitId;
    private String description;
    private Long invoiceId;
    private Long userLoginId;
    private Long categoryId;

    public Long getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(Long invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(Long userLoginId) {
        this.userLoginId = userLoginId;
    }

    public InvoiceItem toInvoiceItem() {

        UserLogin createdBy = new UserLogin();
        createdBy.setUserLoginId(userLoginId);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);

        Items item = null;
        if (itemId != null) {
            item = new Items();
            item.setItemId(itemId);
        }
           Units unit = null;
        if(unitId !=null){
           unit = new Units();
        unit.setUnitId(unitId); 
        }
     

        Category category = null;
if(categoryId !=null){
    category = new Category();
        category.setCategoryId(categoryId);
}
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setCreatedBy(createdBy);
        invoiceItem.setDescription(description);
        invoiceItem.setItem(item);
        invoiceItem.setInvoice(invoice);
        invoiceItem.setInvoiceItemId(invoiceItemId);
        invoiceItem.setQuantity(quantity);
        invoiceItem.setUnitPrice(unitPrice);
        invoiceItem.setUnit(unit);
        invoiceItem.setCategory(category);

        return invoiceItem;
    }

}
