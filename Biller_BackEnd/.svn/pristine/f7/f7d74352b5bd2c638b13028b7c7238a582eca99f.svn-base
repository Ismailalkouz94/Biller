/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ajarmeh
 */
@Entity
@Table(name = "DATA_FIELD_TYPE")
public class DataFieldType {

    @Id
    @Column(name = "DATA_FIELD_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dataFieldTypeId;

    @Column(name = "DATA_FIELD_TYPE_KEY")
    private String dataFieldTypeKey;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "PARTY_ID", nullable = false)
    private Party party;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Long getDataFieldTypeId() {
        return dataFieldTypeId;
    }

    public void setDataFieldTypeId(Long dataFieldTypeId) {
        this.dataFieldTypeId = dataFieldTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDataFieldTypeKey() {
        return dataFieldTypeKey;
    }

    public void setDataFieldTypeKey(String dataFieldTypeKey) {
        this.dataFieldTypeKey = dataFieldTypeKey;
    }

}
