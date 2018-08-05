/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.UserLogin;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.resources.MenusTree;
import com.mycompany.biller.resources.UserLoginResources;
import com.mycompany.biller.service.UserLoginService;
import com.mycompany.biller.utils.ResponseMessages;
import com.mycompany.biller.utils.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/userLogin")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> checkLogin(@RequestBody UserLoginResources userLoginResources, HttpSession session) {

        Map<String, String> data = new HashMap<String, String>();
        String userName = userLoginResources.getUserName();
        String password = userLoginResources.getPassword();
        Long partyId = userLoginResources.getPartyId();

        if (userName.isEmpty() || password.isEmpty() || partyId == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم تسجيل الدخول", "Login failed", true, data), HttpStatus.OK);
        }

        if (userLoginService.checkLogin(userName, password, partyId)) {
            Party party = new Party();
            party.setPartyId(partyId);
            UserLogin userLogin = userLoginService.findByUserName(userName, party);
            data.put("token", session.getId());
            data.put("userLoginId", userLogin.getUserLoginId().toString());
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تسجيل الدخول بنجاح", "Logged in successfully", false, data), HttpStatus.OK);
        } else {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("يرجى التأكد من اسم المستخدم والرقم السري", "Login failed", true, data), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(
            @RequestParam(value = "userName") String name,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "enabled") char enabled,
            @RequestParam(value = "partyId") Long partyId) {
        System.out.println("adeed");

        Party party = new Party();
        party.setPartyId(partyId);

        UserLogin user = new UserLogin();
        user.setUserName(name);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setParty(party);
        userLoginService.addUserLogin(user);
        return "OK";
    }

    @RequestMapping(value = "/defaultUserLogin", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> defaultUserLogin(@RequestBody UserLoginResources userLoginResources) {

        if (userLoginResources.getPartyId() == null) {
            throw new NotFoundException("PartyId Not Found");
        }

        Party party = new Party();
        party.setPartyId(userLoginResources.getPartyId());

        String name = "admin";
        if (!userLoginResources.getUserName().isEmpty()) {
            name = userLoginResources.getUserName();
        }

        Random rand = new Random();
        String password = String.valueOf(rand.nextInt(50000 - 10000) + 10000);

        UserLogin user = new UserLogin();
        user.setUserName(name);
        user.setPassword(password);
        user.setEnabled('Y');
        user.setParty(party);
        userLoginService.addUserLogin(user);
        
         Map<String, String> data = new HashMap<String, String>();
         data.put("userLoginId", user.getUserLoginId().toString());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم اضافة مستخدم جديد بنجاح", "Add New User Login successfully", false, data), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@RequestParam(value = "userLoginId") Long userLoginId,
            @RequestParam(value = "userName") String name,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "enabled") char enabled,
            @RequestParam(value = "partyId") Long partyId) {
        Party party = new Party();
        party.setPartyId(partyId);

        UserLogin user = new UserLogin();
        user.setUserName(name);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setParty(party);
        userLoginService.updateUserLogin(user);

        return "OK";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "userLoginId") Long userId) {
        userLoginService.deleteUserLogin(userId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<UserLogin> listAll() {
        return userLoginService.listAllUserLogin();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public UserLogin findById(@RequestParam(value = "userLoginId") Long userId) {
        return userLoginService.findById(userId);
    }

    @RequestMapping(value = "/findByUserName", method = RequestMethod.GET)
    @ResponseBody
    public UserLogin findByUserName(@RequestParam(value = "userName") String userName, @RequestParam(value = "partyId") Long partyId) {
        Party party = new Party();
        party.setPartyId(partyId);
        return userLoginService.findByUserName(userName, party);
    }

    @RequestMapping(value = "/findByPartyId", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findByPartyId(@RequestParam(value = "partyId") Long partyId) {
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, userLoginService.findByPartyId(partyId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseEntity<List<MenusTree>> query(@RequestParam(value = "userName") String userName,
            @RequestParam(value = "partyId") Long partyId) {
        System.out.println("** query **");
        List<MenusTree> menusTree = userLoginService.userLoginRoleQuery(userName, partyId);
        return new ResponseEntity<List<MenusTree>>(menusTree, HttpStatus.OK);
    }

    @RequestMapping(value = "/userLoginChild", method = RequestMethod.POST)
    public ResponseEntity<List<MenusTree>> userLoginChild(@RequestBody UserLoginResources userLoginResources) {
        String userName = userLoginResources.getUserName();
        Long partyId = userLoginResources.getPartyId();
        Long menusId = userLoginResources.getMenusId();

        List<MenusTree> menusTree = userLoginService.userLoginChild(userName, partyId, menusId);
        return new ResponseEntity<List<MenusTree>>(menusTree, HttpStatus.OK);
    }

    @RequestMapping(value = "/getMenusSys", method = RequestMethod.POST)
    @ResponseBody
    public String getMenusSys(@RequestBody UserLoginResources userLoginResources) {
        String userName = userLoginResources.getUserName();
        Long partyId = userLoginResources.getPartyId();
        List<MenusTree> menusList = userLoginService.userLoginRoleQuery(userName, partyId);
        return Utils.parseTreeMenu(menusList);
    }

    @RequestMapping(value = "/rest/token", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> token(HttpSession session) {
        return Collections.singletonMap("x-auth-token", session.getId());
    }
}
