/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.PartyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ismail
 */
@Service
@Transactional
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyDAO partyDAO;

    @Override
    public void addParty(Party party) {
        partyDAO.addParty(party);
    }

    @Override
    public void updateParty(Party party) {
        partyDAO.updateParty(party);
    }

    @Override
    public void deleteParty(Long partyId) {
        Party party = partyDAO.findById(partyId);
        if (party == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        partyDAO.deleteParty(party);
    }

    @Override
    public List<Party> listAllParty() {
        return partyDAO.listAllParty();
    }

    @Override
    public List<Party> listAll(int page, int size) {
        return partyDAO.listAll(page, size);
    }

    @Override
    public Party findById(Long partyId) {
        return partyDAO.findById(partyId);
    }

    @Override
    public Party createParty(Party party) {
        return partyDAO.createParty(party);
    }

    @Override
    public List<Party> findPartyByPartyType(String partyType) {
        return partyDAO.findPartyByPartyType(partyType);

    }

}
