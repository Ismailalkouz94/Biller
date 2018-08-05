/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "PARTY_GROUP")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "partyGroupId")
public class PartyGroup {

    @Id
    @Column(name = "PARTY_GROUP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long partyGroupId;

    @JsonBackReference
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "PARTY_ID", unique = true, nullable = false)
//    @PrimaryKeyJoinColumn
    private Party party;

//    @Column(name = "PARTY_GROUP_TYPE")
//    private String partyGroupType;
    @ManyToOne
    @JoinColumn(name = "company_type", nullable = true)
    private CompanyType companyType;

    @Column(name = "COMPANY_ID")//, nullable = false
    private String companyId;

    //الرقم الضريبي للشركة
    @Column(name = "PARTY_TAX_ID")
    private Integer partyTaxId;

//رقم السجل التجاري
    @Column(name = "COMMERICAL_REGISTRATION_NUM")
    private String commericalRegistrationNum;

    //رأس المال
    @Column(name = "PARTY_CAPITAL")
    private BigDecimal partyCapital;

    @Column(name = "PARTY_ACTIVITY")
    private String partyActivity;

//    @Column(name = "PARTY_SIZE")
//    private Integer partySize;
    @ManyToOne
    @JoinColumn(name = "PARTY_SIZE_ID")
    private PartySize partySize;

    //معدل الفواتير الشهرية
    @Column(name = "MONTHLY_INVOICING_RATE")
    private Float monthlyInvoicingRate;

    @Column(name = "TELEPHONE_NUMBER1")
    private String telephoneNumber1;

    @Column(name = "TELEPHONE_NUMBER2")
    private String telephoneNumber2;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "POSTAL_CODE")
    private String postalCode; //ضاحية الحسين   11195 

    @Column(name = "POBOX")
    private String pobox; //950545
//================
    @Column(name = "PARTY_GROUP_NAME")
    private String partyGroupName;//  شركة بنك القاهرة عمان م.ع.م.

    @Column(name = "TRADE_NAME") // trade name
    private String tradeName;// بنك القاهرة عمان

    @Column(name = "CATEGORY")
    private String category;// الأولى
//, length = 14
    @Column(name = "PHONE")
    private String phone; //,065638300,065638800

//    @Column(name = "CELLULAR")
//    private String cellular;//0797203512
    @Column(name = "FAX")
    private String fax;//,065682221

    @Column(name = "EMAIL")
    private String eMail;//,info@cab.jo

    @Column(name = "WEBSITE")
    private String website;//,www.cab.jo

    @Column(name = "STREET_NAME")
    private String streetName;//شارع الشابسوغ

    @Column(name = "ADDRESS")
    private String address;//شارع الشابسوغ

//    @Column(name = "PROFESSIONS")
//    private String professions;//,اعمال البنوك
//    =====================
    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public Long getPartyGroupId() {
        return partyGroupId;
    }

    public void setPartyGroupId(Long partyGroupId) {
        this.partyGroupId = partyGroupId;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
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

    public String getPobox() {
        return pobox;
    }

    public void setPobox(String pobox) {
        this.pobox = pobox;
    }

    public String getPartyGroupName() {
        return partyGroupName;
    }

    public void setPartyGroupName(String partyGroupName) {
        this.partyGroupName = partyGroupName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
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

    public PartySize getPartySize() {
        return partySize;
    }

    public void setPartySize(PartySize partySize) {
        this.partySize = partySize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
