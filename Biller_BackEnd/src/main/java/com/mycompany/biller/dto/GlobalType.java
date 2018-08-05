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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "GLOBAL_TYPE")
public class GlobalType {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GLOBAL_TYPE_ID")
    private String globalTypeId;

    @Column(name = "PARENT_GLOBAL_TYPE_ID", nullable = true)
    private String parentGlobalTypeId;

    @Column(name = "DESCRIPTIN")
    private String description;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public String getGlobalTypeId() {
        return globalTypeId;
    }

    public void setGlobalTypeId(String globalTypeId) {
        this.globalTypeId = globalTypeId;
    }

    public String getParentGlobalTypeId() {
        return parentGlobalTypeId;
    }

    public void setParentGlobalTypeId(String parentGlobalTypeId) {
        this.parentGlobalTypeId = parentGlobalTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
