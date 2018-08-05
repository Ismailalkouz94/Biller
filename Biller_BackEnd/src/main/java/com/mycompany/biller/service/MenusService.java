/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.Menus;
import com.mycompany.biller.resources.MenusTree;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface MenusService {

    public void addMenus(Menus menus);

    public void updateMenus(Menus menus);

    public void deleteMenus(Long id);

    public List<Menus> listAllMenus();

    public Menus findById(Long id);

    public List<MenusTree> menusTree();
}
