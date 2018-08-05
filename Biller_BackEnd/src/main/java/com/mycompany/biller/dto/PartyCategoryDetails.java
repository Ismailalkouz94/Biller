/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Rbab3a
 */
@Entity

@Table(name = "PARTY_CATEGORY_DETAILS",
        uniqueConstraints = {
            @UniqueConstraint(name = "CategoryId_PartyId", columnNames = {"party_category_id", "party_id"})})
public class PartyCategoryDetails {

    @Id
    @Column(name = "party_Cat_Details_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long partyCatDetailsId;
    
 
    @ManyToOne
    @JoinColumn(name = "party_category_id", nullable = false)
    private PartyCategory partyCategroy;

     
    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Long getPartyCatDetailsId() {
        return partyCatDetailsId;
    }

    public void setPartyCatDetailsId(Long partyCatDetailsId) {
        this.partyCatDetailsId = partyCatDetailsId;
    }

    public PartyCategory getPartyCategroy() {
        return partyCategroy;
    }

    public void setPartyCategroy(PartyCategory partyCategroy) {
        this.partyCategroy = partyCategroy;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public String toString() {
        return "PartyCategoryDetails{" + "partyCatDetailsId=" + partyCatDetailsId + ", partyCategroy=" + partyCategroy + ", party=" + party + ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime + '}';
    }

}
