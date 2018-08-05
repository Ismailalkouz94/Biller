/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.RoleType;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface RoleTypeDAO {

    public void addRoleType(RoleType roleType);

    public void updateRoleType(RoleType roleType);

    public void deleteRoleType(RoleType roleType);

    public List<RoleType> listAllRoleType();

    public RoleType findRoleTypeById(Long id);
}