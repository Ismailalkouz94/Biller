/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.InvoiceDAO;
import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.InvoiceService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rbab3a
 */
@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Override
    public Invoice create(Invoice invoice) {
        return invoiceDAO.create(invoice);
    }

    @Override
    public Invoice update(Invoice invoice) {
        return invoiceDAO.update(invoice);

    }

    @Override
    public void delete(Long invoiceId) {
        Invoice invoice = invoiceDAO.findById(invoiceId);
        if (invoice == null) {
            throw new NotFoundException("invoice Information Not Exist");
        }
        invoiceDAO.delete(invoice);
    }

    @Override
    public List<Invoice> listAll() {
        return invoiceDAO.listAll();
    }

    @Override
    public Invoice findById(Long invoiceId) {
        return invoiceDAO.findById(invoiceId);
    }

    @Override
    public List<Invoice> findByFromPartyId(Long fromPartyId,String invoiceTypeId) {

        return invoiceDAO.findByFromPartyId(fromPartyId,invoiceTypeId);
    }

    @Override
    public List<Invoice> findByToPartyId(Long toPartyId) {
        return invoiceDAO.findByToPartyId(toPartyId);

    }

    @Override
    public List<Invoice> findByCriteria(Party fromParty,GlobalItem invoiceType, Long invoiceId, Long paidNumber, GlobalItem statusId, String toName, String description, String invoiceDate, Date dueDate, Date paidDate, Orders order, Party toParty) {
        return invoiceDAO.findByCriteria(fromParty,invoiceType, invoiceId, paidNumber, statusId, toName, description, invoiceDate, dueDate, paidDate, order, toParty);
    }

    @Override
    public List<Invoice> findBySentTo(Long fromPartyId) {
        return invoiceDAO.findBySentTo(fromPartyId);
    }

    @Override
    public List<Invoice> findBySentFrom(Long toPartyId) {
        return invoiceDAO.findBySentFrom(toPartyId);
    }

    @Override
    public int changeStatus(Long orderId, String status) {
        return invoiceDAO.changeStatus(orderId, status);

    }

    @Override
    public Invoice findByInvoiceIdAndFromPartyId(Long invoiceId, Long fromPartyId) {
        Invoice invoice = invoiceDAO.findById(invoiceId);
        if (invoice == null) {
            throw new NotFoundException("Invoice Information Not Exist");
        }
        Party fromParty = partyDAO.findById(fromPartyId);
        if (fromParty == null) {
            throw new NotFoundException("Party Information Not Exist");
        }

        return invoiceDAO.findByInvoiceIdAndFromPartyId(invoice, fromParty);
    }

    @Override
    public Invoice findByInvoiceIdAndToPartyId(Long invoiceId, Long toPartyId) {
        Invoice invoice = invoiceDAO.findById(invoiceId);
        if (invoice == null) {
            throw new NotFoundException("Invoice Information Not Exist");
        }
        Party toParty = partyDAO.findById(toPartyId);
        if (toParty == null) {
            throw new NotFoundException("Party Information Not Exist");
        }

        return invoiceDAO.findByInvoiceIdAndToPartyId(invoice, toParty);
    }

    @Override
    public Invoice findByOrderId(Long orderId) {
        return invoiceDAO.findByOrderId(orderId);
    }

    @Override
    public List<Invoice> findAllByFromPartyId(Long fromPartyId) {
return invoiceDAO.findAllByFromPartyId(fromPartyId);}

}
