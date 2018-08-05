/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyTemplateRep;
import com.mycompany.biller.dto.TemplateRep;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class PartyTemplateRepResources extends ResourceSupport {
    
    private Long partyTemplateRepId;
    private String templateId;
    private Long partyId;
    private String templateValue;
    private String templateValueNtv;
    
    public Long getPartyTemplateRepId() {
        return partyTemplateRepId;
    }
    
    public void setPartyTemplateRepId(Long partyTemplateRepId) {
        this.partyTemplateRepId = partyTemplateRepId;
    }
    
    public String getTemplateId() {
        return templateId;
    }
    
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    
    public Long getPartyId() {
        return partyId;
    }
    
    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
    
    public String getTemplateValue() {
        return templateValue;
    }
    
    public void setTemplateValue(String templateValue) {
        this.templateValue = templateValue;
    }
    
    public String getTemplateValueNtv() {
        return templateValueNtv;
    }
    
    public void setTemplateValueNtv(String templateValueNtv) {
        this.templateValueNtv = templateValueNtv;
    }
    
    public PartyTemplateRep toPartyTemplateRep() {
        TemplateRep templateRep = new TemplateRep();
        templateRep.setTemplateId(templateId);
        
        Party party = new Party();
        party.setPartyId(partyId);
        
        PartyTemplateRep partyTemplateRep = new PartyTemplateRep();
        partyTemplateRep.setPartyTemplateRepId(partyTemplateRepId);
        partyTemplateRep.setTemplateRep(templateRep);
        partyTemplateRep.setParty(party);
        partyTemplateRep.setTemplateValue(templateValue);
        partyTemplateRep.setTemplateValueNtv(templateValueNtv);
        
        return partyTemplateRep;
    }
}
