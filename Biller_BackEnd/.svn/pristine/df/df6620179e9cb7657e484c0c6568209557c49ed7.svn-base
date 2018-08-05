/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.Units;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class UnitsResources extends ResourceSupport {

    private Long unitId;
    private String name;
    private Long partyId;

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Units toUnits() {
        Party party = new Party();
        party.setPartyId(partyId);

        Units units = new Units();
        units.setUnitId(unitId);
        units.setName(name);
        units.setParty(party);

        return units;
    }
}
