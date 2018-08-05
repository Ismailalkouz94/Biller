/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.RoleGroup;
import com.mycompany.biller.dto.UserLogin;
import com.mycompany.biller.dto.UserRole;
import com.mycompany.biller.service.UserRoleService;
import com.mycompany.biller.utils.ResponseMessages;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages>  add(@RequestParam(value = "userLoginId") Long userLoginId,
            @RequestParam(value = "roleGroupId") Long roleGroupId) {

        UserLogin userLogin = new UserLogin();
        userLogin.setUserLoginId(userLoginId);

        RoleGroup roleGroup = new RoleGroup();
        roleGroup.setRoleGroupId(roleGroupId);

        UserRole userRole = new UserRole();
//        userRole.setDescription(description);
        userRole.setUserLogin(userLogin);
        userRole.setRoleGroup(roleGroup);

        userRoleService.addUserRole(userRole);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم اضافة الصلاحية بنجاح", "Add UserRole successfully", false, ""), HttpStatus.OK);
//        return new ResponseEntity<UserRole>(userRole, HttpStatus.CREATED);/
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<UserRole> update(@RequestParam(value = "userRoleId") Long userRoleId,
            @RequestParam(value = "userLoginId") Long userLoginId,
            @RequestParam(value = "roleGroupId") Long roleGroupId) {

        UserLogin userLogin = new UserLogin();
        userLogin.setUserLoginId(userLoginId);

        RoleGroup roleGroup = new RoleGroup();
        roleGroup.setRoleGroupId(roleGroupId);

        UserRole userRole = new UserRole();
        userRole.setUserRoleId(userRoleId);
//        userRole.setDescription(description);
        userRole.setUserLogin(userLogin);
        userRole.setRoleGroup(roleGroup);

        userRoleService.updateUserRole(userRole);
        return new ResponseEntity<UserRole>(userRole, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "userRoleId") Long userRoleId) {
        userRoleService.deleteUserRole(userRoleId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<UserRole> listAll() {
        return userRoleService.listAllUserRole();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public UserRole findById(@RequestParam(value = "userRoleId") Long userRoleId) {
        return userRoleService.findById(userRoleId);

    }
}
