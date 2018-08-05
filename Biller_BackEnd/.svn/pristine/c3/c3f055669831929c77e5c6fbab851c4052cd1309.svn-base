/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.RoleType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.biller.service.RoleTypeService;

/**
 *
 * @author ismail
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/roleType")
public class RoleTypeController {

    @Autowired
    private RoleTypeService roleTypeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(@RequestParam(value = "parentRoleTypeID", required = false) Long parentRoleTypeID,
            @RequestParam(value = "description") String description) {
        RoleType type = new RoleType();
        type.setParentRoleTypeID(parentRoleTypeID);
        type.setDescription(description);
        roleTypeService.addRoleType(type);
        return "OK";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@RequestParam(value = "roleTypeId") Long roleTypeId,
            @RequestParam(value = "parentRoleTypeId") Long parentRoleTypeId,
            @RequestParam(value = "description") String description) {

        RoleType type = new RoleType();
        type.setRoleTypeID(roleTypeId);
        type.setParentRoleTypeID(parentRoleTypeId);
        type.setDescription(description);
        roleTypeService.updateRoleType(type);
        return "OK";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "roleTypeId") Long roleTypeId) {
        roleTypeService.deleteRoleType(roleTypeId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<RoleType> listAll() {
        return roleTypeService.listAllRoleType();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public RoleType findById(@RequestParam(value = "roleTypeId") Long roleTypeId) {
        return roleTypeService.findRoleTypeById(roleTypeId);

    }

}
