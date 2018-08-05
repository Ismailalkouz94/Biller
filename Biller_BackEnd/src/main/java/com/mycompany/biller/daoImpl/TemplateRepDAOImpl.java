/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.TemplateRepDAO;
import com.mycompany.biller.dto.TemplateRep;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Ajarmeh
 */
@Repository
public class TemplateRepDAOImpl implements TemplateRepDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TemplateRep create(TemplateRep templateRep) {
        Session session = sessionFactory.getCurrentSession();
        session.save(templateRep);
        return templateRep;
    }

    @Override
    public TemplateRep update(TemplateRep templateRep) {
        Session session = sessionFactory.getCurrentSession();
        session.update(templateRep);
        return templateRep;
    }

    @Override
    public void delete(TemplateRep templateRep) {
        sessionFactory.getCurrentSession().delete(templateRep);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TemplateRep> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<TemplateRep> templateRepList = session.createQuery("from TemplateRep").list();
        return templateRepList;
    }

    @Override
    public TemplateRep findById(String templateId) {
        return (TemplateRep) sessionFactory.getCurrentSession().get(TemplateRep.class, templateId);
    }

}
