package com.mycompany.biller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "PARTY_PROFILE_PREF")
public class PartyProfilePref {

    @Id
    @Column(name = "PARTY_PROFILE_PREF_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long partyProfilePrefId;

    @ManyToOne
    @JoinColumn(name = "TO_PARTY_ID", nullable = false)
    private Party toParty;

    @ManyToOne()
    @JoinColumn(name = "FROM_PARTY_ID", nullable = false)
    private Party fromParty;

    @Column(name = "SUBSCRIPTION_NO", nullable = false)
    private Long subscriptionNo;

    @Column(name = "DESCRIPTION")
    private String description;
    
       @ManyToOne
    @JoinColumn(name = "user_Login_Id", nullable = true)
    @JsonIgnore
    private UserLogin userLoginId;

    public Long getPartyProfilePrefId() {
        return partyProfilePrefId;
    }

    public void setPartyProfilePrefId(Long partyProfilePrefId) {
        this.partyProfilePrefId = partyProfilePrefId;
    }

    public Party getToParty() {
        return toParty;
    }

    public void setToParty(Party toParty) {
        this.toParty = toParty;
    }

    public Party getFromParty() {
        return fromParty;
    }

    public void setFromParty(Party fromParty) {
        this.fromParty = fromParty;
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

    public UserLogin getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(UserLogin userLoginId) {
        this.userLoginId = userLoginId;
    }
    
    
}
