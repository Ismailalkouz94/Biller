/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.UploadBillsDAO;
import com.mycompany.biller.dto.UploadBills;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ismail
 */
@Repository
public class UploadBillsDAOImpl implements UploadBillsDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public UploadBills addUploadBills(UploadBills uploadBills) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(uploadBills);
        return uploadBills;
    }

    @Override
    public UploadBills updateUploadBills(UploadBills uploadBills) {
        Session session = sessionFactory.getCurrentSession();
        session.update(uploadBills);
        return uploadBills;
    }

    @Override
    public void deleteUploadBills(UploadBills uploadBills) {
        sessionFactory.getCurrentSession().delete(uploadBills);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UploadBills> listAllUploadBills() {
        Session session = sessionFactory.getCurrentSession();
        List<UploadBills> uploadBillsList = session.createQuery("from UploadBills").list();
        return uploadBillsList;
    }

    @Override
    public UploadBills findUploadBillsById(Long id) {
        return (UploadBills) sessionFactory.getCurrentSession().get(UploadBills.class, id);
    }

    @Override
    public UploadBills updateUploadBillsStatus(UploadBills uploadBills) {
        Session session = sessionFactory.getCurrentSession();
        session.update(uploadBills);
        return this.findUploadBillsById(uploadBills.getId());
    }
}
