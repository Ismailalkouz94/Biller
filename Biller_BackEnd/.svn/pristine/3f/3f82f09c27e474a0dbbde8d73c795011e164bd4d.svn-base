/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.UserLogin;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class UserLoginResources extends ResourceSupport {

    private String userName;
    private String password;
    private char enabled;
    private Long partyId;
    private Long userLoginId;
    private Long menusId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getEnabled() {
        return enabled;
    }

    public void setEnabled(char enabled) {
        this.enabled = enabled;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(Long userLoginId) {
        this.userLoginId = userLoginId;
    }

    public Long getMenusId() {
        return menusId;
    }

    public void setMenusId(Long menusId) {
        this.menusId = menusId;
    }

    public UserLogin toUserLogin() {
        Party party = new Party();
        party.setPartyId(partyId);

        UserLogin user = new UserLogin();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setParty(party);

        return user;
    }
}
