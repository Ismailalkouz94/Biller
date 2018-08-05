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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "ROLE_TYPE")
public class RoleType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_TYPE_ID")
    private Long roleTypeID;

    @Column(name = "PARENT_ROLE_TYPE_ID")
    private Long parentRoleTypeID;

    @Column(name = "DESCRIPTIN")
    private String description;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Long getRoleTypeID() {
        return roleTypeID;
    }

    public void setRoleTypeID(Long roleTypeID) {
        this.roleTypeID = roleTypeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentRoleTypeID() {
        return parentRoleTypeID;
    }

    public void setParentRoleTypeID(Long parentRoleTypeID) {
        this.parentRoleTypeID = parentRoleTypeID;
    }

    @Override
    public String toString() {
        return "RoleType{" + "roleTypeID=" + roleTypeID + ", parentRoleTypeID=" + parentRoleTypeID + ", description=" + description + '}';
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
