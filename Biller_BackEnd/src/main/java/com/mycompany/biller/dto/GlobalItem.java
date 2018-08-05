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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "GLOBAL_ITEM")
public class GlobalItem {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GLOBAL_ITEM_ID")
    private String globalItemId;

    @Column(name = "GLOBAL_ITEM_CODE")
    private String globalItemCode;

    @Column(name = "DESCRIPTIN")
    private String description;

    @ManyToOne
    private GlobalType globalType;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateDateTime;

    @Override
    public String toString() {
        return "GlobalItem{" + "globalItemId=" + globalItemId + ", globalItemCode=" + globalItemCode + ", description=" + description + ", globalType=" + globalType + '}';
    }

    public String getGlobalItemId() {
        return globalItemId;
    }

    public void setGlobalItemId(String globalItemId) {
        this.globalItemId = globalItemId;
    }

    public String getGlobalItemCode() {
        return globalItemCode;
    }

    public void setGlobalItemCode(String globalItemCode) {
        this.globalItemCode = globalItemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GlobalType getGlobalType() {
        return globalType;
    }

    public void setGlobalType(GlobalType globalType) {
        this.globalType = globalType;
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
}
