/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.PartyTemplateRep;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface PartyTemplateRepService {

    public PartyTemplateRep create(PartyTemplateRep partyTemplateRep);

    public PartyTemplateRep update(PartyTemplateRep partyTemplateRep);

    public void delete(Long partyTemplateRepId);

    public List<PartyTemplateRep> listAll();

    public PartyTemplateRep findById(Long partyTemplateRepId);

    public List<PartyTemplateRep> findByPartyId(Long partyId);

    public PartyTemplateRep findByTemplateIdAndPartyId(String templateId, Long partyId);

    public List<PartyTemplateRep> findByPartyIdAndType(Long partyId, String globalItemId);

}
