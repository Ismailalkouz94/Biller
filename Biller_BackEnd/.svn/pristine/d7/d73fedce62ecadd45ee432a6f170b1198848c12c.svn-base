/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    @Column(name = "FISRT_NAME")
    private String fisrtName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

     @Column(name = "NATIONAL_NUMBER")
    private String nationalNumber;
     
    @Column(name = "GENDER")
    private char gender;
    
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    
    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "STREET_NAME")
    private String streetName;
    
    @Column(name = "EMAIL")
    private String eMail;
    
    @Column(name = "PHONE")
    private String phone;
    
    @Column(name = "MOBILE")
    private String mobile;

    @OneToOne
    private Party Party;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @Override
    public String toString() {
        return "Person{" + "personId=" + personId + ", fisrtName=" + fisrtName + ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", Party=" + Party + '}';
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Party getParty() {
        return Party;
    }

    public void setParty(Party Party) {
        this.Party = Party;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
