/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.UserRole;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface UserRoleDAO {

    public UserRole addUserRole(UserRole userRole);

    public UserRole updateUserRole(UserRole userRole);

    public void deleteUserRole(UserRole userRole);

    public List<UserRole> listAllUserRole();

    public UserRole findById(Long id);
}
