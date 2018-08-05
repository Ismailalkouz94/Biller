/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dto.PartyGroup;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.biller.dao.PartyGroupDAO;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.service.PartyGroupService;

/**
 *
 * @author ismail
 */
@Service
@Transactional
public class PartyGroupServiceImpl implements PartyGroupService {

    @Autowired
    private PartyGroupDAO partyGroupDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Override
    public void addPartyGroup(PartyGroup partyGroup) {
        if (partyDAO.findById(partyGroup.getParty().getPartyId()) == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        partyGroupDAO.addPartyGroup(partyGroup);
    }

    @Override
    public void updatePartyGroup(PartyGroup partyGroup) {
        partyGroupDAO.updatePartyGroup(partyGroup);
    }

    @Override
    public void deletePartyGroup(Long partyGroupId) {
        PartyGroup partyGroup = partyGroupDAO.findPartyGroupById(partyGroupId);
        if (partyGroup == null) {
            throw new NotFoundException("Party Group Information Not Exist");
        }
        partyGroupDAO.deletePartyGroup(partyGroup);
    }

    @Override
    public List<PartyGroup> listAllPartyGroup() {
        return partyGroupDAO.listAllPartyGroup();
    }

    @Override
    public PartyGroup findPartyGroupById(Long partyGroupId) {
        return partyGroupDAO.findPartyGroupById(partyGroupId);
    }

    @Override
    public PartyGroup createPartyGroup(PartyGroup partyGroup) {

        Party party = partyDAO.createParty(partyGroup.getParty());

        partyGroup.setParty(party);

        return partyGroupDAO.createPartyGroup(partyGroup);
    }

    public List<PartyGroup> findByCriteria(Long partyGroupId, String partyCode, String partyActivity, String partyGroupName, String brandName, String category) {
        return partyGroupDAO.findByCriteria(partyGroupId, partyCode, partyActivity, partyGroupName, brandName, category);
    }

    @Override
    public PartyGroup findByPartyId(Long partyId) {
        return partyGroupDAO.findByPartyId(partyId);
    }
}
