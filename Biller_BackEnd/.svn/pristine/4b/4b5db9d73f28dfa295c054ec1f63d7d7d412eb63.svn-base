/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.ItemsDAO;
import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Items;
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
 * @author Admin
 */
@Repository
public class ItemsDAOImpl implements ItemsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Items create(Items items) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(items);
        return items;
    }

    @Override
    public Items update(Items items) {
        Session session = sessionFactory.getCurrentSession();
        session.update(items);
        return items;
    }

    @Override
    public void delete(Items items) {
        sessionFactory.getCurrentSession().delete(items);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Items> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Items> itemsList = session.createQuery("from Items").list();
        return itemsList;
    }

    @Override
    public Items findById(Long itemsId) {
        return (Items) sessionFactory.getCurrentSession().get(Items.class, itemsId);
    }

    @Override
    public List<Items> findByCriteria(Long itemId, String description, String brandName, Category category) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Items.class);
        if (itemId != null) {
            criteria.add(Restrictions.eq("itemId", itemId));
        }
        if (description != null) {
            criteria.add(Restrictions.like("description", description, MatchMode.ANYWHERE).ignoreCase());
        }
        if (brandName != null) {
            criteria.add(Restrictions.like("brandName", brandName, MatchMode.ANYWHERE).ignoreCase());
        }
        if (category != null) {
            criteria.add(Restrictions.eq("category", category));
        }
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Items> findByCatId(Long categoryId) {
        Session session = sessionFactory.getCurrentSession();
        List<Items> itemsList = session.createQuery("FROM Items WHERE category.categoryId=:categoryId")
                .setParameter("categoryId", categoryId).list();
        return itemsList;
    }

}
