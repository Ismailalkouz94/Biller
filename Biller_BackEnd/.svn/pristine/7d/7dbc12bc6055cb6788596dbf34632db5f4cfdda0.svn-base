/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyGroupFav;

/**
 *
 * @author Rbab3a
 */
public class PartyFavResources {
    private Long partyGroupFavId;
    private Long partyId;
    private Long partyFavId;

    public Long getPartyGroupFavId() {
        return partyGroupFavId;
    }

    public void setPartyGroupFavId(Long partyGroupFavId) {
        this.partyGroupFavId = partyGroupFavId;
    }

    
    
    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPartyFavId() {
        return partyFavId;
    }

    public void setPartyFavId(Long partyFavId) {
        this.partyFavId = partyFavId;
    }
    
    public PartyGroupFav toPartyGroupFav(){
        Party party = new Party();
        party.setPartyId(partyId);
        
        Party partFav= new Party();
        partFav.setPartyId(partyFavId);
        
        PartyGroupFav partyGroupFav =new  PartyGroupFav();
        partyGroupFav.setParty(party);
        partyGroupFav.setPartyFav(partFav);
        partyGroupFav.setPartyGroupFavId(partyGroupFavId);
        
        return partyGroupFav;
                
    }
    
    
}
