/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.MenusDAO;
import com.mycompany.biller.dto.Menus;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.resources.MenusTree;
import com.mycompany.biller.service.MenusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class MenusServiceImpl implements MenusService {

    @Autowired
    private MenusDAO menusDAO;

    @Override
    public void addMenus(Menus menus) {
        menusDAO.addMenus(menus);
    }

    @Override
    public void updateMenus(Menus menus) {
        menusDAO.updateMenus(menus);
    }

    @Override
    public void deleteMenus(Long id) {
        Menus menus = menusDAO.findById(id);
        if (menus == null) {
            throw new NotFoundException("Menus Information Not Exist");
        }
        menusDAO.deleteMenus(menus);
    }

    @Override
    public List<Menus> listAllMenus() {
        return menusDAO.listAllMenus();
    }

    @Override
    public Menus findById(Long id) {
        return menusDAO.findById(id);
    }

    @Override
    public List<MenusTree> menusTree() {
        return menusDAO.menusTree();
    }

}
