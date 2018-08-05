/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dto.PartyRole;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.biller.dao.PartyRoleDAO;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.PartyRoleService;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class PartyRoleServiceImpl implements PartyRoleService {

    @Autowired
    private PartyRoleDAO partyRoleDAO;

    @Override
    public void addPartyRole(PartyRole partyRole) {
        partyRoleDAO.addPartyRole(partyRole);
    }

    @Override
    public void updatePartyRole(PartyRole partyRole) {
        partyRoleDAO.updatePartyRole(partyRole);
    }

    @Override
    public void deletePartyRole(Long partyRoleId) {
        PartyRole partyRole = partyRoleDAO.findPartyRoleById(partyRoleId);
        if (partyRole == null) {
            throw new NotFoundException("Party Role Information Not Exist");
        }
        partyRoleDAO.deletePartyRole(partyRole);
    }

    @Override
    public List<PartyRole> listAllPartyRole() {
        return partyRoleDAO.listAllPartyRole();
    }

    @Override
    public PartyRole findPartyRoleById(Long partyRoleId) {
        return partyRoleDAO.findPartyRoleById(partyRoleId);
    }

}
