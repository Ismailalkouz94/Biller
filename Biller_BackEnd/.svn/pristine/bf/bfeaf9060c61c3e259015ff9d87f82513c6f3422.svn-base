/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "ITEM_UNIT_PRICE",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"ITEM_ID", "UNIT_ID"})})
public class ItemUnitPrice {

    @Id
    @Column(name = "ITEM_UNIT_PRICE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemUnitPriceId;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Items items;

    @ManyToOne
    @JoinColumn(name = "UNIT_ID", nullable = false)
    private Units units;

    @Column(name = "PRICE", columnDefinition = "Decimal(12,3) default '0.000'")
    private Double price;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public Long getItemUnitPriceId() {
        return itemUnitPriceId;
    }

    public void setItemUnitPriceId(Long itemUnitPriceId) {
        this.itemUnitPriceId = itemUnitPriceId;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
