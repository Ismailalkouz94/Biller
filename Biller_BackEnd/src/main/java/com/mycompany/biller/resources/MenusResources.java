/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Menus;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class MenusResources extends ResourceSupport {

    private Long menusId;
    private Long parentMenusId;
    private String description;
    private String state;
    private String type;
    private String icon;

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

    public Menus toMenus() {
        Menus menus = new Menus();
        menus.setMenusId(menusId);
        menus.setParentMenusId(parentMenusId);
        menus.setDescription(description);
        menus.setState(state);
        menus.setType(type);
        menus.setIcon(icon);

        return menus;
    }
}
