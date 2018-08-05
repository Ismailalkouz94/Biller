package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyProfilePref;
import com.mycompany.biller.dto.UserLogin;
import org.springframework.hateoas.ResourceSupport;

public class PartyProfilePrefResource extends ResourceSupport {

    private Long partyProfilePrefId;
    private Long toPartyId;
    private Long fromPartyId;
    private Long subscriptionNo;
    private String description;
    private Long userLoginId;
    
    public Long getPartyProfilePrefId() {
        return partyProfilePrefId;
    }
    
    public void setPartyProfilePrefId(Long partyProfilePrefId) {
        this.partyProfilePrefId = partyProfilePrefId;
    }
    
    public Long getToPartyId() {
        return toPartyId;
    }
    
    public void setToPartyId(Long toPartyId) {
        this.toPartyId = toPartyId;
    }
    
    public Long getFromPartyId() {
        return fromPartyId;
    }
    
    public void setFromPartyId(Long fromPartyId) {
        this.fromPartyId = fromPartyId;
    }
    
    public Long getSubscriptionNo() {
        return subscriptionNo;
    }
    
    public void setSubscriptionNo(Long subscriptionNo) {
        this.subscriptionNo = subscriptionNo;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getUserLoginId() {
        return userLoginId;
    }
    
    public void setUserLoginId(Long userLoginId) {
        this.userLoginId = userLoginId;
    }
    
    public PartyProfilePref toPartyProfilePref() {
        Party toParty = new Party();
        toParty.setPartyId(toPartyId);
        
        Party fromParty = new Party();
        fromParty.setPartyId(fromPartyId);
        UserLogin userLogin = new UserLogin();
        userLogin.setUserLoginId(userLoginId);
        PartyProfilePref partyProfilePref = new PartyProfilePref();
        partyProfilePref.setPartyProfilePrefId(partyProfilePrefId);
        partyProfilePref.setToParty(toParty);
        partyProfilePref.setFromParty(fromParty);
        partyProfilePref.setSubscriptionNo(subscriptionNo);
        partyProfilePref.setDescription(description);
        partyProfilePref.setUserLoginId(userLogin);
        return partyProfilePref;
    }
    
}
