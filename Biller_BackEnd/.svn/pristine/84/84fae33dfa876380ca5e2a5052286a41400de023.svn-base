/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.biller.dto.RoleType;
import com.mycompany.biller.dao.RoleTypeDAO;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.RoleTypeService;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class RoleTypeServiceImpl implements RoleTypeService {

    @Autowired
    private RoleTypeDAO roleTypeDAO;

    @Override
    public void addRoleType(RoleType roleType) {
        roleTypeDAO.addRoleType(roleType);
    }

    @Override
    public void updateRoleType(RoleType roleType) {
        roleTypeDAO.updateRoleType(roleType);
    }

    @Override
    public void deleteRoleType(Long id) {
        RoleType roleType = roleTypeDAO.findRoleTypeById(id);
        if (roleType == null) {
            throw new NotFoundException("Role Type Information Not Exist");
        }
        roleTypeDAO.deleteRoleType(roleType);
    }

    @Override
    public List<RoleType> listAllRoleType() {
        return roleTypeDAO.listAllRoleType();
    }

    @Override
    public RoleType findRoleTypeById(Long id) {
        return roleTypeDAO.findRoleTypeById(id);
    }

}
