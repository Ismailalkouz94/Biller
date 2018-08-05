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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "MENUS")
public class Menus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MENUS_ID")
    private Long menusId;

    @Column(name = "PARENT_MENUS_ID", nullable = true)
    private Long parentMenusId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MENUS_STATE")
    private String state;

    @Column(name = "MENUS_TYPE")
    private String type;

    @Column(name = "MENUS_ICON")
    private String icon;

//    @ManyToOne
//    private Component component;
    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Long getMenusId() {
        return menusId;
    }

    public void setMenusId(Long menusId) {
        this.menusId = menusId;
    }

    public Long getParentMenusId() {
        return parentMenusId;
    }

    public void setParentMenusId(Long parentMenusId) {
        this.parentMenusId = parentMenusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
