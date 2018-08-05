/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.GlobalItemDAO;
import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.GlobalType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ismail
 */
@Repository
public class GlobalItemDAOImpl implements GlobalItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addGlobalItem(GlobalItem globalItem) {
        Session session = sessionFactory.getCurrentSession();
        session.save(globalItem);
    }

    @Override
    public void updateGlobalItem(GlobalItem globalItem) {
        Session session = sessionFactory.getCurrentSession();
        session.update(globalItem);
    }

    @Override
    public void deleteGlobalItem(GlobalItem globalItem) {
        sessionFactory.getCurrentSession().delete(globalItem);
    }

    @Override
    public List<GlobalItem> listAllGlobalItem() {
        Session session = sessionFactory.getCurrentSession();
        List<GlobalItem> globalItems = session.createQuery("from GlobalItem").list();
        return globalItems;
    }

    @Override
    public GlobalItem findGlobalItemById(String globalItemId) {
        return (GlobalItem) sessionFactory.getCurrentSession().get(GlobalItem.class, globalItemId);
    }

    @Override
    public List<GlobalItem> findByCriteria(String globalItemId, String description, String globalItemCode, GlobalType globalType) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GlobalItem.class);
        if (globalItemId != null) {
            criteria.add(Restrictions.eq("globalItemId", globalItemId));//like
        }
        if (description != null) {
            criteria.add(Restrictions.like("description", description, MatchMode.ANYWHERE).ignoreCase());
        }
        if (globalItemCode != null) {
            criteria.add(Restrictions.eq("globalItemCode", globalItemCode));//like
        }
        if (globalType != null) {
            criteria.add(Restrictions.eq("globalType", globalType));//li;e
        }

        return criteria.list();
    }
}
