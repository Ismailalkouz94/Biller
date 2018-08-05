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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ismail
 */
@Entity
@Table(name = "UPLOAD_BILLS")
public class UploadBills {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "COMPANY_NUMBER")
    private Long companyNubmer;

    @Column(name = "MIN_AMOUNT")
    private double minAmount;

    @Column(name = "MAX_AMOUNT")
    private double maxAmount;

    @Column(name = "PARTIALY_PAY")
    private char partialyPay;

    @Column(name = "DATE_OF_POST")
    @Temporal(TemporalType.DATE)
    private Date dateOfPost;

    @Column(name = "DATE_OF_PAYMENT")
    @Temporal(TemporalType.DATE)
    private Date dateOfPayment;

    @ManyToOne
    @JoinColumn(name = "BILL_STATUS")
    private GlobalItem globalItem;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public GlobalItem getGlobalItem() {
        return globalItem;
    }

    public void setGlobalItem(GlobalItem globalItem) {
        this.globalItem = globalItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyNubmer() {
        return companyNubmer;
    }

    public void setCompanyNubmer(Long companyNubmer) {
        this.companyNubmer = companyNubmer;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public char isPartialyPay() {
        return partialyPay;
    }

    public void setPartialyPay(char partialyPay) {
        this.partialyPay = partialyPay;
    }

    public Date getDateOfPost() {
        return dateOfPost;
    }

    public void setDateOfPost(Date dateOfPost) {
        this.dateOfPost = dateOfPost;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
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
