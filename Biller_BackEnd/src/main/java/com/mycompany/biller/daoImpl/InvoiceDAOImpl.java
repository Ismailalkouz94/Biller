/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.InvoiceDAO;
import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.exception.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Rbab3a
 */
@Repository
public class InvoiceDAOImpl implements InvoiceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Invoice create(Invoice invoice) {
        try {
        Session session = sessionFactory.getCurrentSession();
//        sessionFactory.getCurrentSession().clear();

        session.save(invoice);
        return invoice;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    public Invoice update(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.update(invoice);
        return invoice;
    }

    @Override
    public void delete(Invoice invoice) {
        sessionFactory.getCurrentSession().delete(invoice);

    }

    @Override
    public List<Invoice> listAll() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Invoice> invoiceList = session.createQuery("from Invoice").list();
        return invoiceList;

    }

    @Override
    public Invoice findById(Long invoiceId) {
        return (Invoice) sessionFactory.getCurrentSession().get(Invoice.class, invoiceId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Invoice> findByCriteria(Party fromParty, GlobalItem invoiceType,Long invoiceId, Long paidNumber, GlobalItem statusId, String toName, String description, String invoiceDate, Date dueDate, Date paidDate, Orders order, Party toParty) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Invoice.class);
        Date invoiceToDate = null;
        if (invoiceId != null) {
            criteria.add(Restrictions.eq("invoiceId", invoiceId));
        }

        if (statusId != null) {
            criteria.add(Restrictions.eq("status", statusId));
        }
//        if (paidNumber != null) {
//            criteria.add(Restrictions.eq("paidNumber", paidNumber));
//        }
        if (paidNumber != null) {
            criteria.add(Restrictions.eq("referenceNumber", paidNumber));
        }

        if (description != null) {
            criteria.add(Restrictions.like("description", description, MatchMode.ANYWHERE).ignoreCase());

        }

        if (toName != null) {

            criteria.add(Restrictions.like("toName", toName, MatchMode.ANYWHERE).ignoreCase());

        }

        if (invoiceDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date date = null;
            try {
                date = formatter.parse(invoiceDate);
            } catch (ParseException ex) {
                Logger.getLogger(InvoiceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(date);
            System.out.println(formatter.format(date));
            criteria.add(Restrictions.le("invoiceDate", date));
        }

        if (dueDate != null) {
            criteria.add(Restrictions.eq("dueDate", dueDate));
        }
        if (paidDate != null) {
            criteria.add(Restrictions.eq("paidDate", paidDate));
        }
        if (order != null) {
            criteria.add(Restrictions.eq("Order", order));
        }
        if (toParty != null) {
            criteria.add(Restrictions.eq("toParty", toParty));
        }
        if (fromParty != null) {
            criteria.add(Restrictions.eq("fromParty", fromParty));
        }
         if(invoiceType !=null){
             criteria.add(Restrictions.eq("invoiceTypeId", invoiceType));
         }

        return criteria.list();

    }

    @Override
    public List<Invoice> findByFromPartyId(Long fromPartyId,String invoiceTypeId) {
        Session session = sessionFactory.getCurrentSession();
        List<Invoice> invoiceList = session.createQuery("From Invoice WHERE fromParty.partyId=:fromPartyId and invoiceTypeId.globalItemId=:invoiceTypeId")
                .setParameter("fromPartyId", fromPartyId).setParameter("invoiceTypeId", invoiceTypeId).list();
        return invoiceList;

    }

    @Override
    public List<Invoice> findByToPartyId(Long toPartyId) {
        Session session = sessionFactory.getCurrentSession();
        List<Invoice> invoiceList = session.createQuery("From Invoice WHERE toParty.partyId=:toPartyId")
                .setParameter("toPartyId", toPartyId).list();
        return invoiceList;

    }

    @Override
    public List<Invoice> findBySentTo(Long fromPartyId) {
        return (List<Invoice>) sessionFactory.getCurrentSession()
                .createQuery("select distinct I.toParty.partyGroup.party.partyId ,I.toParty.partyGroup.tradeName FROM Invoice I WHERE I.fromParty.partyId=:fromPartyId")
                .setParameter("fromPartyId", fromPartyId).list();
    }

    @Override
    public List<Invoice> findBySentFrom(Long toPartyId) {

        return (List<Invoice>) sessionFactory.getCurrentSession()
                .createQuery("select distinct I.fromParty.partyGroup.party.partyId,I.fromParty.partyGroup.tradeName FROM Invoice I WHERE I.toParty.partyId=:toPartyId")
                .setParameter("toPartyId", toPartyId).list();
    }

    @Override
    public int changeStatus(Long invoiceId, String status) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query qry = session.createQuery("UPDATE Invoice SET status.globalItemId=:status WHERE invoiceId=:invoiceId");
            qry.setParameter("status", status)
                    .setParameter("invoiceId", invoiceId);
            int res = qry.executeUpdate();
            return res;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }

    }

    @Override
    public Invoice findByInvoiceIdAndFromPartyId(Invoice invoice, Party fromParty) {
        Long invoiceId = invoice.getInvoiceId();
        Long partyId = fromParty.getPartyId();
        Session session = sessionFactory.getCurrentSession();
        Invoice invoiceResult = (Invoice) session.createQuery("From Invoice WHERE invoiceId=:invoiceId AND fromParty.partyId=:fromPartyId")
                .setParameter("invoiceId", invoiceId)
                .setParameter("fromPartyId", partyId)
                .uniqueResult();
        return invoiceResult;
    }

    @Override
    public Invoice findByInvoiceIdAndToPartyId(Invoice invoice, Party toParty) {
        Long invoiceId = invoice.getInvoiceId();
        Long partyId = toParty.getPartyId();
        Session session = sessionFactory.getCurrentSession();
        Invoice invoiceResult = (Invoice) session.createQuery("From Invoice WHERE invoiceId=:invoiceId AND toParty.partyId=:toPartyId")
                .setParameter("invoiceId", invoiceId)
                .setParameter("toPartyId", partyId)
                .uniqueResult();
        return invoiceResult;
    }

    @Override
    public Invoice findByOrderId(Long orderId) {
        Session session = sessionFactory.getCurrentSession();
        Invoice invoiceResult = (Invoice) session.createQuery("From Invoice WHERE Order.orderId=:orderId")
                .setParameter("orderId", orderId)
                .uniqueResult();
        return invoiceResult;
    }

    @Override
    public List<Invoice> findAllByFromPartyId(Long fromPartyId) {
   Session session = sessionFactory.getCurrentSession();
        List<Invoice> invoiceList = session.createQuery("From Invoice WHERE fromParty.partyId=:fromPartyId")
                .setParameter("fromPartyId", fromPartyId).list();
        return invoiceList;

    }

}
