/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.InvoiceItemDAO;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceItem;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.dto.UserLogin;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rbab3a
 */
@Repository
public class InvoiceItemDAOImpl implements InvoiceItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public InvoiceItem create(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.save(invoiceItem);
        return invoiceItem;

    }

    @Override
    public InvoiceItem update(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.update(invoiceItem);
        return invoiceItem;

    }

    @Override
    public void delete(InvoiceItem invoiceItem) {
        sessionFactory.getCurrentSession().delete(invoiceItem);

    }

    @Override
    public List<InvoiceItem> listAll() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<InvoiceItem> invoiceItemList = session.createQuery("from InvoiceItem").list();
        return invoiceItemList;

    }

    @Override
    public InvoiceItem findById(Long invoiceItemId) {
        return (InvoiceItem) sessionFactory.getCurrentSession().get(InvoiceItem.class, invoiceItemId);

    }

    @Override
    public List<InvoiceItem> findByCriteria(Long invoiceItemId, double unitPrice, int quantity, Items itemId, Units unitId, String description, Invoice invoiceId, UserLogin createdBy) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Invoice.class);
        if (invoiceItemId != null) {
            criteria.add(Restrictions.eq("invoiceItemId", invoiceItemId));
        }
//    
//            criteria.add(Restrictions.eq("unitPrice", unitPrice));
//        
//            criteria.add(Restrictions.eq("statusId", statusId));
//     
//        if (description != null) {
//            criteria.add(Restrictions.eq("description", description));
//        }
//        if (email != null) {
//            criteria.add(Restrictions.eq("email", email));
//        }
//        if (mobileNumber != null) {
//            criteria.add(Restrictions.eq("mobileNumber", mobileNumber));
//        }
//        if (toName != null) {
//            criteria.add(Restrictions.eq("toName", toName));
//        }
//        if (referenceNumber != null) {
//            criteria.add(Restrictions.eq("referenceNumber", referenceNumber));
//        }
//        if (invoiceDate != null) {
//            criteria.add(Restrictions.eq("invoiceDate", invoiceDate));
//        }
//
//        if (dueDate != null) {
//            criteria.add(Restrictions.eq("dueDate", dueDate));
//        }
//        if (paidDate != null) {
//            criteria.add(Restrictions.eq("paidDate", paidDate));
//        }
//        if (party != null) {
//            criteria.add(Restrictions.eq("partyId", party));
//        }
//        if (fromParty != null) {
//            criteria.add(Restrictions.eq("fromPartyId", fromParty));
//        }

        return criteria.list();

    }

    @Override
    public List<InvoiceItem> listInvoiceItems(Long invoiceId) {
        String selectQuery = "FROM InvoiceItem WHERE INVOICE_ID = :invoiceId";
        List<InvoiceItem> listInvoiceItems = sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("invoiceId", invoiceId)
                .list();
        return listInvoiceItems;

    }

}
