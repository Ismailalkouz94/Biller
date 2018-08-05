/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.RoleGroup;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface RoleGroupDAO {

    public void addRoleGroup(RoleGroup roleGroup);

    public void updateRoleGroup(RoleGroup roleGroup);

    public void deleteRoleGroup(RoleGroup roleGroup);

    public List<RoleGroup> listAllRoleGroup();

    public RoleGroup findById(Long id);
    
    public List<RoleGroup> findByCriteria(Long roleGroupId, String description);
}
