/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.DataFieldType;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceDetails;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class InvoiceDetailsResources extends ResourceSupport {

    private Long invoiceDetailsId;
    private Long dataFieldTypeId;
    private String dataFieldTypeValue;
    private Long invoiceId;

    public Long getInvoiceDetailsId() {
        return invoiceDetailsId;
    }

    public void setInvoiceDetailsId(Long invoiceDetailsId) {
        this.invoiceDetailsId = invoiceDetailsId;
    }

    public Long getDataFieldTypeId() {
        return dataFieldTypeId;
    }

    public void setDataFieldTypeId(Long dataFieldTypeId) {
        this.dataFieldTypeId = dataFieldTypeId;
    }

    public String getDataFieldTypeValue() {
        return dataFieldTypeValue;
    }

    public void setDataFieldTypeValue(String dataFieldTypeValue) {
        this.dataFieldTypeValue = dataFieldTypeValue;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public InvoiceDetails toInvoiceDetails() {
        DataFieldType dataFieldType = new DataFieldType();
        dataFieldType.setDataFieldTypeId(dataFieldTypeId);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);

        InvoiceDetails invoiceDetails = new InvoiceDetails();
        invoiceDetails.setInvoiceDetailsId(invoiceDetailsId);
        invoiceDetails.setDataFieldType(dataFieldType);
        invoiceDetails.setDataFieldTypeValue(dataFieldTypeValue);
        invoiceDetails.setInvoice(invoice);
        return invoiceDetails;
    }
}
