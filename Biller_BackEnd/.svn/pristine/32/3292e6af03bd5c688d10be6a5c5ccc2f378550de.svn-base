/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.InvoiceDetailsDAO;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceDetails;
import com.mycompany.biller.dto.Party;
import java.util.List;

import com.mycompany.biller.exception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Ajarmeh
 */
@Repository
public class InvoiceDetailsDAOImpl implements InvoiceDetailsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public InvoiceDetails create(InvoiceDetails invoiceDetails) {
        Session session = sessionFactory.getCurrentSession();
        session.save(invoiceDetails);
        return invoiceDetails;
    }

    @Override
    public InvoiceDetails update(InvoiceDetails invoiceDetails) {
        Session session = sessionFactory.getCurrentSession();
        session.update(invoiceDetails);
        return invoiceDetails;
    }

    @Override
    public void delete(InvoiceDetails invoiceDetails) {
        sessionFactory.getCurrentSession().delete(invoiceDetails);
    }

    @Override
    public int deleteByInvoiceId(Long invoiceId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query qry = session.createQuery("DELETE FROM InvoiceDetails WHERE invoice.invoiceId=:invoiceId");
            qry.setParameter("invoiceId", invoiceId);
            int res = qry.executeUpdate();
            return res;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<InvoiceDetails> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<InvoiceDetails> invoiceDetailsList = session.createQuery("from InvoiceDetails").list();
        return invoiceDetailsList;
    }

    @Override
    public InvoiceDetails findById(Long invoiceDetailsId) {
        return (InvoiceDetails) sessionFactory.getCurrentSession().get(InvoiceDetails.class, invoiceDetailsId);
    }

    @Override
    public List<InvoiceDetails> findByInvoiceId(Invoice invoice) {
        Long invoiceId = invoice.getInvoiceId();

        Session session = sessionFactory.getCurrentSession();
        List<InvoiceDetails> invoiceDetailsList = session.createQuery("from InvoiceDetails WHERE invoice.invoiceId=:invoiceId")
                .setParameter("invoiceId", invoiceId)
                .list();
        return invoiceDetailsList;
    }

    @Override
    public List<InvoiceDetails> findByInvoiceIdAndToPartyId(Invoice invoice, Party toParty) {
        Long invoiceId = invoice.getInvoiceId();
        Long partyId = toParty.getPartyId();

        Session session = sessionFactory.getCurrentSession();
        List<InvoiceDetails> invoiceDetailsList = session.createQuery("from InvoiceDetails WHERE invoice.invoiceId=:invoiceId AND invoice.toParty.partyId=:partyId")
                .setParameter("invoiceId", invoiceId)
                .setParameter("partyId", partyId)
                .list();
        return invoiceDetailsList;
    }

    @Override
    public List<InvoiceDetails> findByInvoiceIdAndFromPartyId(Invoice invoice, Party fromParty) {
        Long invoiceId = invoice.getInvoiceId();
        Long partyId = fromParty.getPartyId();

        Session session = sessionFactory.getCurrentSession();
        List<InvoiceDetails> invoiceDetailsList = session.createQuery("from InvoiceDetails WHERE invoice.invoiceId=:invoiceId AND invoice.fromParty.partyId=:partyId")
                .setParameter("invoiceId", invoiceId)
                .setParameter("partyId", partyId)
                .list();
        return invoiceDetailsList;
    }

}
