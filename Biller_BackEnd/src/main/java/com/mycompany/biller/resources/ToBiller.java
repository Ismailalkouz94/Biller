/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import java.util.List;

/**
 *
 * @author Rbab3a
 */
public class ToBiller {

    private Object partyId;
    private Object partyFav;
    private String trandName;
    private Object companyType;
    private String companyTypeDesc;
    private String commericalRegistrationNum;
    private String partyGroupName;
    private Object partySizeId;
    private String partySizeDesc;
    private Object cityId;
    private String cityDesc;
    private String countryDesc;
    private String companyId;
    private Object userLoginId;

    private List<String> companyTypeList;
    private List<String> cityIdList;

    public Object getPartyId() {
        return partyId;
    }

    public void setPartyId(Object partyId) {
        this.partyId = partyId;
    }

    public Object getPartyFav() {
        return partyFav;
    }

    public void setPartyFav(Object partyFav) {
        this.partyFav = partyFav;
    }

    public String getTrandName() {
        return trandName;
    }

    public void setTrandName(String trandName) {
        this.trandName = trandName;
    }

    public List<String> getCompanyTypeList() {
        return companyTypeList;
    }

    public void setCompanyTypeList(List<String> companyTypeList) {
        this.companyTypeList = companyTypeList;
    }

    public Object getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Object companyType) {
        this.companyType = companyType;
    }

    public String getCommericalRegistrationNum() {
        return commericalRegistrationNum;
    }

    public void setCommericalRegistrationNum(String commericalRegistrationNum) {
        this.commericalRegistrationNum = commericalRegistrationNum;
    }

    public String getPartyGroupName() {
        return partyGroupName;
    }

    public void setPartyGroupName(String partyGroupName) {
        this.partyGroupName = partyGroupName;
    }

    public Object getPartySizeId() {
        return partySizeId;
    }

    public void setPartySizeId(Object partySizeId) {
        this.partySizeId = partySizeId;
    }

    public List<String> getCityIdList() {
        return cityIdList;
    }

    public void setCityIdList(List<String> cityIdList) {
        this.cityIdList = cityIdList;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyTypeDesc() {
        return companyTypeDesc;
    }

    public void setCompanyTypeDesc(String companyTypeDesc) {
        this.companyTypeDesc = companyTypeDesc;
    }

    public String getPartySizeDesc() {
        return partySizeDesc;
    }

    public void setPartySizeDesc(String partySizeDesc) {
        this.partySizeDesc = partySizeDesc;
    }

    public Object getCityId() {
        return cityId;
    }

    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public String getCountryDesc() {
        return countryDesc;
    }

    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc;
    }

    public Object getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(Object userLoginId) {
        this.userLoginId = userLoginId;
    }

}
