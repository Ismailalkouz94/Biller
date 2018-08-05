/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dto.UserLogin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.biller.dao.UserLoginDAO;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.resources.MenusTree;
import com.mycompany.biller.service.PartyService;
import com.mycompany.biller.service.UserLoginService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private PartyService partyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUserLogin(UserLogin userLogin) {
        Party party = partyService.findById(userLogin.getParty().getPartyId());
        if (party == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        if (userLoginDAO.UserLoginIsExist(userLogin.getUserName(), userLogin.getParty().getPartyId())) {
            throw new NotFoundException("UserLogin Information Is Exist");
        }

        userLogin.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        userLoginDAO.addUserLogin(userLogin);
    }

    @Override
    public void updateUserLogin(UserLogin userLogin) {
        userLoginDAO.updateUserLogin(userLogin);
    }

    @Override
    public void deleteUserLogin(Long id) {
        UserLogin userLogin = userLoginDAO.findById(id);
        if (userLogin == null) {
            throw new NotFoundException("User Login Information Not Exist");
        }
        userLoginDAO.deleteUserLogin(userLogin);
    }

    @Override
    public List<UserLogin> listAllUserLogin() {
        return userLoginDAO.listAllUserLogin();
    }

    @Override
    public UserLogin findById(Long id) {
        return userLoginDAO.findById(id);
    }

    @Override
    public boolean checkLogin(String userName, String password, Long partyId) {

        return userLoginDAO.checkLogin(userName, password, partyId);
//                return userLoginDAO.checkLogin(userName, passwordEncoder.encode(password));

    }

    @Override
    public List<MenusTree> userLoginRoleQuery(String userName, Long partyId) {
        return userLoginDAO.userLoginRoleQuery(userName, partyId);
    }

    @Override
    public List<MenusTree> userLoginParent(String userName, Long partyId) {
        return userLoginDAO.userLoginParent(userName, partyId);
    }

    @Override
    public List<MenusTree> userLoginChild(String userName, Long partyId, Long menusId) {
        return userLoginDAO.userLoginChild(userName, partyId, menusId);
    }

    @Override
    public UserLogin findByUserName(String userName, Party party) {
        return userLoginDAO.findByUserName(userName, party);
    }

    @Override
    public List<UserLogin> findByPartyId(Long partyId) {
        return userLoginDAO.findByPartyId(partyId);
    }

    @Override
    public boolean UserLoginIsExist(String userName, Long partyId) {
        Party party = partyService.findById(partyId);
        if (party == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        return userLoginDAO.UserLoginIsExist(userName, partyId);
    }

}
