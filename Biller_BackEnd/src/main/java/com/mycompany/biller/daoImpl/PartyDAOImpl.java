/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dto.Party;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ismail
 */
@Repository
public class PartyDAOImpl implements PartyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addParty(Party party) {
        Session session = sessionFactory.getCurrentSession();
        session.save(party);
    }

    @Override
    public void updateParty(Party party) {
        Session session = sessionFactory.getCurrentSession();
        session.update(party);
    }

    @Override
    public void deleteParty(Party party) {
        sessionFactory.getCurrentSession().delete(party);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Party> listAllParty() {
        Session session = sessionFactory.getCurrentSession();
        List<Party> partyList = session.createQuery("from Party").list();
        return partyList;
    }

    @Override
    public List<Party> listAll(int page, int size) {
        int pageNew = (page - 1) * size;
        if(page>1){
            size = (page -1) * size;
        }else{
            size = (page) * size;
        }

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Party ORDER BY partyId");
        query.setFirstResult(pageNew);
        query.setMaxResults(size);
        List<Party> partyList = query.list();
        return partyList;
    }

    @Override
    public Party findById(Long partyId) {
        return (Party) sessionFactory.getCurrentSession().get(Party.class, partyId);
    }

    @Override
    public Party createParty(Party party) {
        Long id = (Long) sessionFactory.getCurrentSession().save(party);
        //sessionFactory.getCurrentSession().flush();
        party.setPartyId(id);
        return party;
    }

    @Override
    public List<Party> findPartyByPartyType(String partyType) {

   return (List<Party>) sessionFactory.getCurrentSession()
                .createQuery("From Party WHERE partyType=:partyType")
                .setParameter("partyType", partyType).list();
    }

}
