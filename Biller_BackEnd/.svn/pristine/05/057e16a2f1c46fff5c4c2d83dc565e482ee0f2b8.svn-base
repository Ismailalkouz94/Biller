/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import java.sql.Clob;
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
 * @author Ajarmeh
 */
@Entity
@Table(name = "PARTY_TEMPLATE_REP",
        uniqueConstraints = {
            @UniqueConstraint(name = "TEMPLATE_ID_AND_PARTY_ID", columnNames = {"TEMPLATE_ID", "PARTY_ID"})})
public class PartyTemplateRep {

    @Id
    @Column(name = "PARTY_TEMPLATE_REP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long partyTemplateRepId;

    @ManyToOne
    @JoinColumn(name = "TEMPLATE_ID", nullable = false)
    private TemplateRep templateRep;

    @ManyToOne
    @JoinColumn(name = "PARTY_ID", nullable = false)
    private Party party;

    @Column(name = "TEMPLATE_VALUE", columnDefinition = "CLOB")//FRN
    private String templateValue;

    @Column(name = "TEMPLATE_VALUE_NTV", columnDefinition = "CLOB")
    private String templateValueNtv;

    @ManyToOne
    @JoinColumn(name = "REPORT_TYPE_ID")
    private GlobalItem reportTypeId;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Long getPartyTemplateRepId() {
        return partyTemplateRepId;
    }

    public void setPartyTemplateRepId(Long partyTemplateRepId) {
        this.partyTemplateRepId = partyTemplateRepId;
    }

    public TemplateRep getTemplateRep() {
        return templateRep;
    }

    public void setTemplateRep(TemplateRep templateRep) {
        this.templateRep = templateRep;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public String getTemplateValue() {
        return templateValue;
    }

    public void setTemplateValue(String templateValue) {
        this.templateValue = templateValue;
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

    public GlobalItem getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(GlobalItem reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getTemplateValueNtv() {
        return templateValueNtv;
    }

    public void setTemplateValueNtv(String templateValueNtv) {
        this.templateValueNtv = templateValueNtv;
    }
}
