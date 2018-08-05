/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dto;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Ajarmeh
 */
@Entity
@Table(name = "INVOICE_DETAILS",
        uniqueConstraints = {
            @UniqueConstraint(name = "DATA_FIELD_TYPE_ID_AND_INVOICE_ID", columnNames = {"DATA_FIELD_TYPE_ID", "INVOICE_ID"})})
public class InvoiceDetails {

    @Id
    @Column(name = "INVOICE_DETAILS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceDetailsId;

    @ManyToOne
    @JoinColumn(name = "DATA_FIELD_TYPE_ID", nullable = false)
    private DataFieldType dataFieldType;

    @Column(name = "DATA_FIELD_TYPE_VALUE")
    private String dataFieldTypeValue;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    private Invoice invoice;

    @Column(name = "CREATE_DATE_TIME")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public Long getInvoiceDetailsId() {
        return invoiceDetailsId;
    }

    public void setInvoiceDetailsId(Long invoiceDetailsId) {
        this.invoiceDetailsId = invoiceDetailsId;
    }

    public DataFieldType getDataFieldType() {
        return dataFieldType;
    }

    public void setDataFieldType(DataFieldType dataFieldType) {
        this.dataFieldType = dataFieldType;
    }

    public String getDataFieldTypeValue() {
        return dataFieldTypeValue;
    }

    public void setDataFieldTypeValue(String dataFieldTypeValue) {
        this.dataFieldTypeValue = dataFieldTypeValue;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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
