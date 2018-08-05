/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.ItemUnitPriceDAO;
import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.ItemUnitPrice;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.resources.CategoryTree;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Admin
 */
@Repository
public class ItemUnitPriceDAOImpl implements ItemUnitPriceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ItemUnitPrice create(ItemUnitPrice itemUnitPrice) {
        Session session = sessionFactory.getCurrentSession();
        session.save(itemUnitPrice);
        return itemUnitPrice;
    }

    @Override
    public ItemUnitPrice update(ItemUnitPrice itemUnitPrice) {
        Session session = sessionFactory.getCurrentSession();
        session.update(itemUnitPrice);
        return itemUnitPrice;
    }

    @Override
    public void delete(ItemUnitPrice itemUnitPrice) {
        sessionFactory.getCurrentSession().delete(itemUnitPrice);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ItemUnitPrice> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<ItemUnitPrice> itemUnitPriceList = session.createQuery("from ItemUnitPrice").list();
        return itemUnitPriceList;
    }

    @Override
    public ItemUnitPrice findById(Long itemUnitPriceId) {
        return (ItemUnitPrice) sessionFactory.getCurrentSession().get(ItemUnitPrice.class, itemUnitPriceId);
    }

    public ItemUnitPrice findByUnique(Long itemId, Long unitId) {
        Session session = sessionFactory.getCurrentSession();
        ItemUnitPrice itemUnitPrice = (ItemUnitPrice) session.createQuery("FROM ItemUnitPrice WHERE items.itemId =:itemId AND units.unitId =:unitId")
                .setParameter("itemId", itemId)
                .setParameter("unitId", unitId)
                .uniqueResult();
        return itemUnitPrice;
    }

    public List<ItemUnitPrice> findByCriteria(Long itemUnitPriceId, Items items, Units units, Double price) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItemUnitPrice.class);
        if (itemUnitPriceId != null) {
            criteria.add(Restrictions.eq("itemUnitPriceId", itemUnitPriceId));
        }
        if (items != null) {
            criteria.add(Restrictions.eq("items", items));
        }
        if (units != null) {
            criteria.add(Restrictions.eq("units", units));
        }
        if (price != null) {
            criteria.add(Restrictions.eq("price", price));
        }
        return criteria.list();
    }


}
