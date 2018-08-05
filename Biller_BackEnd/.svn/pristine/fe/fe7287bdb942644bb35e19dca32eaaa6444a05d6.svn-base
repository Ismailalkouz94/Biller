/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyTemplateRep;
import com.mycompany.biller.dto.TemplateRep;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface PartyTemplateRepDAO {

    public PartyTemplateRep create(PartyTemplateRep partyTemplateRep);

    public PartyTemplateRep update(PartyTemplateRep partyTemplateRep);

    public void delete(PartyTemplateRep partyTemplateRep);

    public List<PartyTemplateRep> listAll();

    public PartyTemplateRep findById(Long partyTemplateRepId);

    public List<PartyTemplateRep> findByPartyId(Party party);

    public PartyTemplateRep findByTemplateIdAndPartyId(TemplateRep templateRep, Party party);

    public List<PartyTemplateRep> findByPartyIdAndType(Party party, GlobalItem reportTypeId);

}
