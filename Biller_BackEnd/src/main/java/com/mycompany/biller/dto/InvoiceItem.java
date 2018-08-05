/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.sql.Date;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Rbab3a
 */
@Entity
@Table(name = "INVOICE_ITEM")
public class InvoiceItem {

    @Id
    @Column(name = "INVOICE_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceItemId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "UNIT_PRICE", columnDefinition = "Decimal(12,3) default '0.000'")
    private Double unitPrice;

    @Column(name = "QUANTITY")
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = true)
    private Items item;

    @ManyToOne
    @JoinColumn(name = "UNIT_ID", nullable = true)
    private Units unit;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = true)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY", nullable = false)
    private UserLogin createdBy;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public Long getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(Long invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public UserLogin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserLogin createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" + "invoiceItemId=" + invoiceItemId + ", description=" + description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", item=" + item + ", unit=" + unit + ", invoice=" + invoice + ", category=" + category + ", createdBy=" + createdBy + ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime + '}';
    }

}
