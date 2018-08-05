/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.MenuRoleDAO;
import com.mycompany.biller.dao.RoleGroupDAO;
import com.mycompany.biller.dto.MenuRole;
import com.mycompany.biller.dto.RoleGroup;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.MenuRoleService;
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
public class MenuRoleServiceImpl implements MenuRoleService {

    @Autowired
    private MenuRoleDAO menuRoleDAO;

    @Autowired
    private RoleGroupDAO roleGroupDAO;

    @Override
    public void addMenuRole(MenuRole menuRole) {
        menuRoleDAO.addMenuRole(menuRole);
    }

    @Override
    public void updateMenuRole(MenuRole menuRole) {
        menuRoleDAO.updateMenuRole(menuRole);
    }

    @Override
    public void deleteMenuRole(Long menuRoleId) {
        MenuRole menuRole = menuRoleDAO.findById(menuRoleId);
        if (menuRole == null) {
            throw new NotFoundException("Menu Role Information Not Exist");
        }
        menuRoleDAO.deleteMenuRole(menuRole);
    }

    @Override
    public List<MenuRole> listAllCompany() {
        return menuRoleDAO.listAllMenuRole();
    }

    @Override
    public MenuRole findById(Long menuRole) {
        return menuRoleDAO.findById(menuRole);
    }

    @Override
    public List<MenuRole> findByRoleGroupId(Long roleGroupId) {
        RoleGroup roleGroup = roleGroupDAO.findById(roleGroupId);
        if (roleGroup == null) {
            throw new NotFoundException("RoleGroup Information Not Exist");
        }
        return menuRoleDAO.findByRoleGroupId(roleGroup);
    }

}
