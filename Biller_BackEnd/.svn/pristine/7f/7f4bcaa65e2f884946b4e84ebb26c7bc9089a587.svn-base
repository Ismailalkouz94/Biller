/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.UserLogin;
import com.mycompany.biller.resources.MenusTree;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserLoginDAO {

    public void addUserLogin(UserLogin userLogin);

    public void updateUserLogin(UserLogin userLogin);

    public void deleteUserLogin(UserLogin userLogin);

    public List<UserLogin> listAllUserLogin();

    public UserLogin findById(Long id);

    public UserLogin findByUserName(String userName, Party party);

    public List<UserLogin> findByPartyId(Long partyId);

    public boolean checkLogin(String userName, String password, Long partyId);

    public List<MenusTree> userLoginRoleQuery(String userName, Long partyId);

    public List<MenusTree> userLoginParent(String userName, Long partyId);

    public List<MenusTree> userLoginChild(String userName, Long partyId, Long menusId);

    public boolean UserLoginIsExist(String userName, Long partyId);
}
