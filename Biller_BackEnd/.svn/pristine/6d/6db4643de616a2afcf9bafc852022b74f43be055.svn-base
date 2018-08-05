/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.UserLogin;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author Rbab3a
 */
public class InvoiceResources extends ResourceSupport {

    private Long invoiceId;
    private Long paidNumber;
    private String invoiceTypeId;
    private Long fromPartyId;
    private Long partyIdTo;
    private Long orderId;
    private String description;
    private String email;
    private String toName;
    private String statusId;
    private Long referenceNumber;
    private String mobileNumber;
    private Date invoiceDate;
    private Date paidDate;
    private Date dueDate;
    private Long userLoginId;
    private Double minimumAmountAllowed;
    private Double maximumAmountAllowed;
    private Character isPartially;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getPaidNumber() {
        return paidNumber;
    }

    public void setPaidNumber(Long paidNumber) {
        this.paidNumber = paidNumber;
    }

    public String getInvoiceTypeId() {
        return invoiceTypeId;
    }

    public void setInvoiceTypeId(String invoiceTypeId) {
        this.invoiceTypeId = invoiceTypeId;
    }

    public Long getFromPartyId() {
        return fromPartyId;
    }

    public void setFromPartyId(Long fromPartyId) {
        this.fromPartyId = fromPartyId;
    }

    public Long getPartyIdTo() {
        return partyIdTo;
    }

    public void setPartyIdTo(Long partyIdTo) {
        this.partyIdTo = partyIdTo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Long getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(Long referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(Long userLoginId) {
        this.userLoginId = userLoginId;
    }

    public Double getMinimumAmountAllowed() {
        return minimumAmountAllowed;
    }

    public void setMinimumAmountAllowed(Double minimumAmountAllowed) {
        this.minimumAmountAllowed = minimumAmountAllowed;
    }

  

    public Double getMaximumAmountAllowed() {
        return maximumAmountAllowed;
    }

    public void setMaximumAmountAllowed(Double maximumAmountAllowed) {
        this.maximumAmountAllowed = maximumAmountAllowed;
    }

      public Character getIsPartially() {
        return isPartially;
    }

    public void setIsPartially(char isPartially) {
        this.isPartially = isPartially;
    }
    
    public Invoice toInvoice() {
        Party toParty = null;
        if(partyIdTo !=null){
            toParty = new Party();
             toParty.setPartyId(partyIdTo);
        }
       

        Party fromParty = new Party();
        fromParty.setPartyId(fromPartyId);

        UserLogin createdBy = new UserLogin();
        createdBy.setUserLoginId(userLoginId);

        Orders order = null;
        
        if (orderId != null) {
            order = new Orders();
            order.setOrderId(orderId);
        }

        GlobalItem status = new GlobalItem();
        status.setGlobalItemId(statusId);

        GlobalItem invoiceType = new GlobalItem();
        invoiceType.setGlobalItemId(invoiceTypeId);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setCreatedBy(createdBy);
        invoice.setDescription(description);
        invoice.setDueDate(dueDate);
        invoice.setEmail(email);
        invoice.setFromParty(fromParty);
        invoice.setInvoiceDate(invoiceDate);
        invoice.setInvoiceTypeId(invoiceType);
        invoice.setMobileNumber(mobileNumber);
        invoice.setOrder(order);
        invoice.setPaidDate(paidDate);
        invoice.setToParty(toParty);
        invoice.setReferenceNumber(referenceNumber);
        invoice.setStatus(status);
        invoice.setPaidNumber(paidNumber);
        invoice.setToName(toName);
      invoice.setMaximumAmountAllowed(maximumAmountAllowed);
      invoice.setMinimumAmountAllowed(minimumAmountAllowed);
        invoice.setIsPartially(isPartially);

        System.out.println("orderId: " + orderId);
        System.out.println("invoice: " + invoice.getOrder());
        return invoice;
    }

}
