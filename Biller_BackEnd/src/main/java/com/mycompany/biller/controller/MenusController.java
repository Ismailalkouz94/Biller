/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Menus;
import com.mycompany.biller.resources.MenusResources;
import com.mycompany.biller.resources.MenusTree;
import com.mycompany.biller.service.MenusService;
import com.mycompany.biller.service.UserLoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.biller.utils.Utils;

/**
 *
 * @author ismail
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/menus")
public class MenusController {

    @Autowired
    private MenusService menusService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(@RequestBody MenusResources menusResources) {
//        Component component = new Component();
//        component.setComponentId(componentId);
        menusService.addMenus(menusResources.toMenus());
        return "OK";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@RequestParam(value = "menusId") Long menusId,
            @RequestParam(value = "parentMenusId", required = false) Long parentMenusId,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "state") String state,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "icon") String icon) {

//        Component component = new Component();
//        component.setComponentId(componentId);
        Menus menus = new Menus();
        menus.setMenusId(menusId);
        menus.setParentMenusId(parentMenusId);
        menus.setDescription(description);
        menus.setState(state);
        menus.setType(type);
        menus.setIcon(icon);
        menusService.updateMenus(menus);
        return "OK";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "menusId") Long menusId) {
        menusService.deleteMenus(menusId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Menus> listAll() {
        return menusService.listAllMenus();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Menus findById(@RequestParam(value = "menusId") Long menusId) {
        return menusService.findById(menusId);

    }

    @RequestMapping(value = "/menusTree", method = RequestMethod.GET)
    @ResponseBody
    public List<MenusTree> menusTree() {
        return menusService.menusTree();
    }

    @RequestMapping(value = "/getMenusTree", method = RequestMethod.GET)
    @ResponseBody
    public String menusTreeTest(@RequestParam(value = "partyId") Long partyId,
            @RequestParam(value = "userName") String userName) {
        List<MenusTree> menusList = userLoginService.userLoginRoleQuery(userName, partyId);
        return Utils.parseTree(menusList);
    }

}
