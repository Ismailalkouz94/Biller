/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.GlobalItemDAO;
import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dao.PartyTemplateRepDAO;
import com.mycompany.biller.dao.TemplateRepDAO;
import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyTemplateRep;
import com.mycompany.biller.dto.TemplateRep;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.PartyTemplateRepService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class PartyTemplateRepServiceImpl implements PartyTemplateRepService {

    @Autowired
    private PartyTemplateRepDAO partyTemplateRepDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Autowired
    private TemplateRepDAO templateRepDAO;

    @Autowired
    private GlobalItemDAO globalItemDAO;

    @Override
    public PartyTemplateRep create(PartyTemplateRep partyTemplateRep) {
        return partyTemplateRepDAO.create(partyTemplateRep);
    }

    @Override
    public PartyTemplateRep update(PartyTemplateRep partyTemplateRep) {
        return partyTemplateRepDAO.update(partyTemplateRep);
    }

    @Override
    public void delete(Long partyTemplateRepId) {
        PartyTemplateRep partyTemplateRep = partyTemplateRepDAO.findById(partyTemplateRepId);
        if (partyTemplateRep == null) {
            throw new NotFoundException("PartyTemplateRep Information Not Exist");
        }
        partyTemplateRepDAO.delete(partyTemplateRep);
    }

    @Override
    public List<PartyTemplateRep> listAll() {
        return partyTemplateRepDAO.listAll();
    }

    @Override
    public PartyTemplateRep findById(Long partyTemplateRepId) {
        return partyTemplateRepDAO.findById(partyTemplateRepId);
    }

    public List<PartyTemplateRep> findByPartyId(Long partyId) {
        Party party = partyDAO.findById(partyId);
        if (party == null) {
            throw new NotFoundException("party Information Not Exist");
        }
        return partyTemplateRepDAO.findByPartyId(party);
    }

    public PartyTemplateRep findByTemplateIdAndPartyId(String templateId, Long partyId) {
        Party party = partyDAO.findById(partyId);
        if (party == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        TemplateRep templateRep = templateRepDAO.findById(templateId);
        if (templateRep == null) {
            throw new NotFoundException("TemplateRep Information Not Exist");
        }
        return partyTemplateRepDAO.findByTemplateIdAndPartyId(templateRep, party);
    }

    @Override
    public List<PartyTemplateRep> findByPartyIdAndType(Long partyId, String globalItemId) {
        Party party = partyDAO.findById(partyId);
        if (party == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        GlobalItem reportTypeId = globalItemDAO.findGlobalItemById(globalItemId);
        if (reportTypeId == null) {
            throw new NotFoundException("GlobalItem Information Not Exist");
        }
   
        return partyTemplateRepDAO.findByPartyIdAndType(party, reportTypeId);
    }

}
