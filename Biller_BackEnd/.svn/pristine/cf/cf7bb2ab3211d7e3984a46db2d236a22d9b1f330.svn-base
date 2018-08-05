/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.PartySizeDAO;
import com.mycompany.biller.dto.PartySize;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Abu3ajram
 */
@Repository
public class PartySizeDAOImpl implements PartySizeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PartySize create(PartySize partySize) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(partySize);
        return partySize;
    }

    @Override
    public PartySize update(PartySize partySize) {
        Session session = sessionFactory.getCurrentSession();
        session.update(partySize);
        return partySize;
    }

    @Override
    public void delete(PartySize partySize) {
        sessionFactory.getCurrentSession().delete(partySize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PartySize> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<PartySize> partySizeList = session.createQuery("from PartySize").list();
        return partySizeList;
    }

    @Override
    public PartySize findById(Long partySizeId) {
        return (PartySize) sessionFactory.getCurrentSession().get(PartySize.class, partySizeId);
    }

}
