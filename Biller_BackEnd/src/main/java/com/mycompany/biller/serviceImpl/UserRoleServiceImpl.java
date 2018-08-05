/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.RoleGroupDAO;
import com.mycompany.biller.dao.UserLoginDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.biller.dao.UserRoleDAO;
import com.mycompany.biller.dto.RoleGroup;
import com.mycompany.biller.dto.UserLogin;
import com.mycompany.biller.dto.UserRole;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.UserRoleService;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private RoleGroupDAO roleGroupDAO;

    @Override
    public UserRole addUserRole(UserRole userRole) {
        UserLogin userLogin = userLoginDAO.findById(userRole.getUserLogin().getUserLoginId());
        if (userLogin == null) {
            throw new NotFoundException("UserLogin Information Not Exist");
        }
        RoleGroup roleGroup = roleGroupDAO.findById(userRole.getRoleGroup().getRoleGroupId());
        if (roleGroup == null) {
            throw new NotFoundException("RoleGroup Information Not Exist");
        }
        return userRoleDAO.addUserRole(userRole);
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        return userRoleDAO.updateUserRole(userRole);
    }

    @Override
    public void deleteUserRole(Long id) {
        UserRole userRole = userRoleDAO.findById(id);
        if (userRole == null) {
            throw new NotFoundException("User Role Information Not Exist");
        }
        userRoleDAO.deleteUserRole(userRole);
    }

    @Override
    public List<UserRole> listAllUserRole() {
        return userRoleDAO.listAllUserRole();
    }

    @Override
    public UserRole findById(Long id) {
        return userRoleDAO.findById(id);
    }

}
