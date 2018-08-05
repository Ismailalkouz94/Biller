/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "PARTY_GROUP_FAV",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"PARTY_FAV", "PARTY_ID"})})
public class PartyGroupFav {

    @Id
    @Column(name = "PARTY_GROUP_FAV_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long partyGroupFavId;

    @ManyToOne
    @JoinColumn(name = "PARTY_ID", nullable = false)
    private Party party;

    @ManyToOne
    @JoinColumn(name = "PARTY_FAV", nullable = false)
    private Party partyFav;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public Long getPartyGroupFavId() {
        return partyGroupFavId;
    }

    public void setPartyGroupFavId(Long partyGroupFavId) {
        this.partyGroupFavId = partyGroupFavId;
    }

    public Party getPartyFav() {
        return partyFav;
    }

    public void setPartyFav(Party partyFav) {
        this.partyFav = partyFav;
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

}
