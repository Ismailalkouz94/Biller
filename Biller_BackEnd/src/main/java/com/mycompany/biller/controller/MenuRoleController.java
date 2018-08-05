/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.MenuRole;
import com.mycompany.biller.dto.Menus;
import com.mycompany.biller.dto.RoleGroup;
import com.mycompany.biller.service.MenuRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ismail
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/menuRole")
public class MenuRoleController {

    @Autowired
    private MenuRoleService menuRoleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(@RequestParam(value = "menusId") Long menusId,
            @RequestParam(value = "roleGroupId") Long roleGroupId) {

        Menus menus = new Menus();//menusId
        menus.setMenusId(menusId);

        RoleGroup roleGroup = new RoleGroup();//roleGroupId
        roleGroup.setRoleGroupId(roleGroupId);

        MenuRole menuRole = new MenuRole();
        menuRole.setMenus(menus);
        menuRole.setRoleGroup(roleGroup);
        menuRoleService.addMenuRole(menuRole);
        return "OK";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@RequestParam(value = "menuRoleId") Long menuRoleId,
            @RequestParam(value = "menusId") Long menusId,
            @RequestParam(value = "roleGroupId") Long roleGroupId) {

        Menus menus = new Menus();//menusId
        menus.setMenusId(menusId);

        RoleGroup roleGroup = new RoleGroup();//roleGroupId
        roleGroup.setRoleGroupId(roleGroupId);

        MenuRole menuRole = new MenuRole();
        menuRole.setMenuRoleId(menuRoleId);
        menuRole.setMenus(menus);
        menuRole.setRoleGroup(roleGroup);
        menuRoleService.updateMenuRole(menuRole);
        return "OK";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "menuRoleId") Long menuRoleId) {
        menuRoleService.deleteMenuRole(menuRoleId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuRole> listAll() {

        return menuRoleService.listAllCompany();

    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public MenuRole findById(@RequestParam(value = "menuRoleId") Long menuRoleId) {
        return menuRoleService.findById(menuRoleId);

    }

    @RequestMapping(value = "/findByRoleGroupId", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuRole> findByRoleGroupId(@RequestParam(value = "roleGroupId") Long roleGroupId) {
        return menuRoleService.findByRoleGroupId(roleGroupId);

    }

}
