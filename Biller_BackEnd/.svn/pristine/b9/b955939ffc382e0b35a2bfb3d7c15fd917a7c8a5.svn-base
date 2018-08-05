/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.InvoiceDetails;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 *
 * @author ismail
 */
public interface InvoiceDetailsService {

    public InvoiceDetails create(InvoiceDetails invoiceDetails);

    public InvoiceDetails update(InvoiceDetails invoiceDetails);

    public void delete(Long invoiceDetailsId);

    public void deleteInvoiceAndDetails(Long invoiceId);

    public List<InvoiceDetails> listAll();

    public InvoiceDetails findById(Long invoiceDetailsId);

    public List<InvoiceDetails> findByInvoiceId(Long invoiceId);

    public List<InvoiceDetails> findByInvoiceIdAndToPartyId(Long invoiceId, Long toPartyId);

    public List<InvoiceDetails> findByInvoiceIdAndFromPartyId(Long invoiceId, Long fromPartyId);

    public List<InvoiceDetails> createList(List<InvoiceDetails> invoiceDetailsList);

    public void createFromFile(Workbook workbook,Long partyId,Long userLoginId);

}
