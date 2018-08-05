/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.UserLogin;
import com.mycompany.biller.dto.UserPreference;
import com.mycompany.biller.service.UserPreferenceService;
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
 * @author ismail
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/userPref")
public class UserPrefController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<UserPreference> add(
            @RequestParam(value = "userLoginId") Long userLoginId,
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value
    ) {

        UserLogin userLogin = new UserLogin();
        userLogin.setUserLoginId(userLoginId);

        UserPreference userPreference = new UserPreference();
        userPreference.setKey(key);
        userPreference.setValue(value);
        userPreference.setUserLogin(userLogin);
        userPreferenceService.add(userPreference);
        return new ResponseEntity<UserPreference>(userPreference, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<UserPreference> update(
            @RequestParam(value = "userLoginId") Long userLoginId,
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value
    ) {

        UserLogin userLogin = new UserLogin();
        userLogin.setUserLoginId(userLoginId);

        UserPreference userPreference = new UserPreference();
        userPreference.setKey(key);
        userPreference.setValue(value);
        userPreference.setUserLogin(userLogin);
        userPreferenceService.update(userPreference);
        return new ResponseEntity<UserPreference>(userPreference, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "Id") Long Id) {
        userPreferenceService.delete(Id);
        return "OK";
    }

    @RequestMapping(value = "/findByUserLoginId", method = RequestMethod.GET)
    @ResponseBody
    public List<UserPreference> listAll(
            @RequestParam(value = "userLoginId") Long userLoginId
    ) {
        return userPreferenceService.findByUserLoginId(userLoginId);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    @ResponseBody
    public UserPreference findByName(
            @RequestParam(value = "userLoginId") Long userLoginId,
            @RequestParam(value = "key") String key) {
        return userPreferenceService.findByKey(userLoginId, key);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public UserPreference findById(
            @RequestParam(value = "id") Long id) {
        return userPreferenceService.findById(id);
    }

}
