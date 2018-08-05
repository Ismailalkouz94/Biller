/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * @author Rbab3a
 */
@Entity
@Table(name = "INVOICE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "invoiceId")
public class Invoice implements Serializable {

    @Id
    @Column(name = "INVOICE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long invoiceId;


    @Column(name = "paid_number")
    private Long paidNumber;

    @ManyToOne
    @JoinColumn(name = "INVOICE_TYPE_ID", nullable = false)
    private GlobalItem invoiceTypeId;

    @ManyToOne
    @JoinColumn(name = "TO_PARTY_ID", nullable = true)
    private Party toParty;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_Id", nullable = true)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID", nullable = false)
    private GlobalItem status;

    @Column(name = "REFERENCE_NUMBER")
    private Long referenceNumber;

    @ManyToOne
    @JoinColumn(name = "FROM_PARTY_ID", nullable = false)
    private Party fromParty;


    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InvoiceItem> invoiceItem;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InvoiceDetails> invoiceDetails;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TO_NAME")
    private String toName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Column(name = "PAID_DATE")
    @Temporal(TemporalType.DATE)
    private Date paidDate;

    @Column(name = "minimum_amount_allowed", columnDefinition = "Decimal(12,3) default '0.000'")
    private Double minimumAmountAllowed;

    @Column(name = "maximum_amount_allowed", columnDefinition = "Decimal(12,3) default '0.000'")
    private Double maximumAmountAllowed;

    @Column(name = "is_partially")
    private Character isPartially; // Y or N

    @ManyToOne
    @JoinColumn(name = "CREATED_BY", nullable = false)
    @JsonIgnore
    private UserLogin createdBy;


    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Party getToParty() {
        return toParty;
    }

    public void setToParty(Party toParty) {
        this.toParty = toParty;
    }

    public List<InvoiceItem> getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(List<InvoiceItem> invoiceItem) {
        this.invoiceItem = invoiceItem;
    }


    public Long getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(Long referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public GlobalItem getInvoiceTypeId() {
        return invoiceTypeId;
    }

    public void setInvoiceTypeId(GlobalItem invoiceTypeId) {
        this.invoiceTypeId = invoiceTypeId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders Order) {
        this.order = Order;
    }

    public GlobalItem getStatus() {
        return status;
    }

    public void setStatus(GlobalItem status) {
        this.status = status;
    }


    public Party getFromParty() {
        return fromParty;
    }

    public void setFromParty(Party fromParty) {
        this.fromParty = fromParty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public UserLogin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserLogin createdBy) {
        this.createdBy = createdBy;
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

    public void setIsPartially(Character isPartially) {
        this.isPartially = isPartially;
    }

    public Long getPaidNumber() {
        return paidNumber;
    }

    public void setPaidNumber(Long paidNumber) {
        this.paidNumber = paidNumber;
    }

    public List<InvoiceDetails> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
