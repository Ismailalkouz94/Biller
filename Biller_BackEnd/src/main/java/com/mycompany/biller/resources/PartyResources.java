/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Party;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class PartyResources extends ResourceSupport {
    
    private Party party;
    
    private String partyCode;
    private String partyType;
    private Long partyId;
    private String description;
    private char isActive;
    
    public Party getParty() {
        return party;
    }
    
    public void setParty(Party party) {
        this.party = party;
    }
    
    public String getPartyCode() {
        return partyCode;
    }
    
    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }
    
    public String getPartyType() {
        return partyType;
    }
    
    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }
    
    public Long getPartyId() {
        return partyId;
    }
    
    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }
    
    public Party toParty() {
        Party party = new Party();
        party.setPartyId(partyId);
        party.setDescription(description);
        party.setPartyCode(partyCode);
        party.setPartyType(partyType);
        party.setIsActive(isActive);
        return party;
    }
}
