/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.Party;

import java.util.Date;
import java.util.List;

/**
 * @author Rbab3a
 */
public interface InvoiceService {

    public Invoice create(Invoice invoice);

    public Invoice update(Invoice invoice);

    public void delete(Long invoiceId);

    public List<Invoice> listAll();

    public int changeStatus(Long orderId, String status);

    public Invoice findById(Long invoiceId);

    public List<Invoice> findByCriteria(Party fromParty,GlobalItem invoiceType, Long invoiceId, Long paidNumber, GlobalItem statusId, String toName, String description, String invoiceDate, Date dueDate, Date paidDate, Orders order, Party toParty);

    public List<Invoice> findByFromPartyId(Long fromPartyId, String invoiceTypeId);

    public List<Invoice> findAllByFromPartyId(Long fromPartyId);

    public List<Invoice> findByToPartyId(Long toPartyId);

    public List<Invoice> findBySentTo(Long fromPartyId);

    public List<Invoice> findBySentFrom(Long toPartyId);

    public Invoice findByInvoiceIdAndFromPartyId(Long invoiceId, Long fromPartyId);

    public Invoice findByInvoiceIdAndToPartyId(Long invoiceId, Long toPartyId);

    public Invoice findByOrderId(Long orderId);

}
