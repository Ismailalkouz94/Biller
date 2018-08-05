/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.CompanyType;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyGroup;
import com.mycompany.biller.dto.PartySize;
import java.math.BigDecimal;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class PartyGroupResources extends ResourceSupport {

    private Party party;

    private String partyCode;
    private String partyType;
    private Long partyId;
    private String description;
    
    private Long partyGroupId;
//    private String partyGroupCode;
    private String partyGroupName;

//    private String partyGroupType;
    private Long companyTypeId;
    private String companyId;
    private int partyTaxId;
    private String commericalRegistrationNum;
    private BigDecimal partyCapital;
    private String partyActivity;
    private Long partySizeId;
    private float monthlyInvoicingRate;
    private String telephoneNumber1;
    private String telephoneNumber2;
    private String mobileNumber;
    private String postalCode;

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

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPartyGroupId() {
        return partyGroupId;
    }

    public void setPartyGroupId(Long partyGroupId) {
        this.partyGroupId = partyGroupId;
    }

    public String getPartyGroupName() {
        return partyGroupName;
    }

    public Long getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(Long companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public void setPartyGroupName(String partyGroupName) {
        this.partyGroupName = partyGroupName;
    }

    public int getPartyTaxId() {
        return partyTaxId;
    }

    public void setPartyTaxId(int partyTaxId) {
        this.partyTaxId = partyTaxId;
    }

    public String getCommericalRegistrationNum() {
        return commericalRegistrationNum;
    }

    public void setCommericalRegistrationNum(String commericalRegistrationNum) {
        this.commericalRegistrationNum = commericalRegistrationNum;
    }

    public BigDecimal getPartyCapital() {
        return partyCapital;
    }

    public void setPartyCapital(BigDecimal partyCapital) {
        this.partyCapital = partyCapital;
    }

    public String getPartyActivity() {
        return partyActivity;
    }

    public void setPartyActivity(String partyActivity) {
        this.partyActivity = partyActivity;
    }

    public Long getPartySizeId() {
        return partySizeId;
    }

    public void setPartySizeId(Long partySizeId) {
        this.partySizeId = partySizeId;
    }

    public float getMonthlyInvoicingRate() {
        return monthlyInvoicingRate;
    }

    public void setMonthlyInvoicingRate(float monthlyInvoicingRate) {
        this.monthlyInvoicingRate = monthlyInvoicingRate;
    }

    public String getTelephoneNumber1() {
        return telephoneNumber1;
    }

    public void setTelephoneNumber1(String telephoneNumber1) {
        this.telephoneNumber1 = telephoneNumber1;
    }

    public String getTelephoneNumber2() {
        return telephoneNumber2;
    }

    public void setTelephoneNumber2(String telephoneNumber2) {
        this.telephoneNumber2 = telephoneNumber2;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public PartyGroup toPartyGroup() {
        Party party = new Party();
        party.setPartyId(partyId);
        party.setDescription(description);
        party.setPartyCode(partyCode);
        party.setPartyType(partyType);
        party.setIsActive('N');

        CompanyType companyType = null;
        if (companyTypeId != null) {
            companyType = new CompanyType();
            companyType.setId(companyTypeId);
        }
        PartySize partySize = null;
        if (partySizeId != null) {
            partySize = new PartySize();
            partySize.setPartySizeId(partySizeId);
        }

        PartyGroup partyGroup = new PartyGroup();
        partyGroup.setPartyGroupId(partyGroupId);
        partyGroup.setPartyGroupName(partyGroupName);
        partyGroup.setParty(party);
        partyGroup.setCompanyType(companyType);
        partyGroup.setCompanyId(companyId);
        partyGroup.setPartyTaxId(partyTaxId);
        partyGroup.setCommericalRegistrationNum(commericalRegistrationNum);
        partyGroup.setPartyActivity(partyActivity);
        partyGroup.setPartyCapital(partyCapital);
        partyGroup.setPartySize(partySize);
        partyGroup.setMobileNumber(mobileNumber);
        partyGroup.setMonthlyInvoicingRate(monthlyInvoicingRate);
        partyGroup.setPostalCode(postalCode);
        partyGroup.setTelephoneNumber1(telephoneNumber1);
        partyGroup.setTelephoneNumber2(telephoneNumber2);
        return partyGroup;
    }
}
