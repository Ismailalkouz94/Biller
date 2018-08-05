/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.UploadBills;
import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author Admin
 */
public class UploadBillsResources extends ResourceSupport {

    private GlobalItem globalItem;

    private String companyName;
    private Long companyNubmer;
    private double minAmount;
    private double maxAmount;
    private char partialyPay;
    private Date dateOfPost;
    private Date dateOfPayment;
    private String billStatus;

    public GlobalItem getGlobalItem() {
        return globalItem;
    }

    public void setGlobalItem(GlobalItem globalItem) {
        this.globalItem = globalItem;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
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

    public char getPartialyPay() {
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

    public UploadBills toUploadBills() {
        GlobalItem globalItem = new GlobalItem();
        globalItem.setGlobalItemId(billStatus);

        UploadBills uploadBills = new UploadBills();

        uploadBills.setCompanyName(companyName);
        uploadBills.setCompanyNubmer(companyNubmer);
        uploadBills.setMinAmount(minAmount);
        uploadBills.setMaxAmount(maxAmount);
        uploadBills.setPartialyPay(partialyPay);
        uploadBills.setDateOfPost(dateOfPost);
        uploadBills.setDateOfPost(dateOfPost);
        uploadBills.setGlobalItem(globalItem);

        return uploadBills;

    }

}
