/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class MenusTree extends ResourceSupport {

    private Object LVL;
    private Object leaf;
    private Object parentMenusId;
    private Object menusId;
    private String state;
    private String type;
    private String icon;
    private String name;

    public Object getLVL() {
        return LVL;
    }

    public void setLVL(Object LVL) {
        this.LVL = LVL;
    }

    public Object getLeaf() {
        return leaf;
    }

    public void setLeaf(Object leaf) {
        this.leaf = leaf;
    }

    public Object getParentMenusId() {
        return parentMenusId;
    }

    public void setParentMenusId(Object parentMenusId) {
        this.parentMenusId = parentMenusId;
    }

    public Object getMenusId() {
        return menusId;
    }

    public void setMenusId(Object menusId) {
        this.menusId = menusId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
