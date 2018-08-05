/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.DataFieldType;
import com.mycompany.biller.dto.Party;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class DataFieldTypeResources extends ResourceSupport {

    private Long dataFieldTypeId;
    private String dataFieldTypeKey;
    private String description;
    private Long partyId;

    public Long getDataFieldTypeId() {
        return dataFieldTypeId;
    }

    public void setDataFieldTypeId(Long dataFieldTypeId) {
        this.dataFieldTypeId = dataFieldTypeId;
    }

    public String getDataFieldTypeKey() {
        return dataFieldTypeKey;
    }

    public void setDataFieldTypeKey(String dataFieldTypeKey) {
        this.dataFieldTypeKey = dataFieldTypeKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public DataFieldType toDataFieldType() {
        Party party = new Party();
        party.setPartyId(partyId);

        DataFieldType dataFieldType = new DataFieldType();
        dataFieldType.setDataFieldTypeId(dataFieldTypeId);
        dataFieldType.setDataFieldTypeKey(dataFieldTypeKey);
        dataFieldType.setDescription(description);
        dataFieldType.setParty(party);

        return dataFieldType;
    }
}
