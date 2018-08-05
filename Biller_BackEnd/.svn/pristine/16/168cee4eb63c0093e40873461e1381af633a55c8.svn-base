/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceDetails;
import com.mycompany.biller.dto.Party;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface InvoiceDetailsDAO {

    public InvoiceDetails create(InvoiceDetails invoiceDetails);

    public InvoiceDetails update(InvoiceDetails invoiceDetails);

    public void delete(InvoiceDetails invoiceDetails);

    public int deleteByInvoiceId(Long invoiceId);

    public List<InvoiceDetails> listAll();

    public InvoiceDetails findById(Long partyTempleteRepId);

    public List<InvoiceDetails> findByInvoiceId(Invoice invoice);

    public List<InvoiceDetails> findByInvoiceIdAndToPartyId(Invoice invoice, Party toParty);

    public List<InvoiceDetails> findByInvoiceIdAndFromPartyId(Invoice invoice, Party fromParty);

}
