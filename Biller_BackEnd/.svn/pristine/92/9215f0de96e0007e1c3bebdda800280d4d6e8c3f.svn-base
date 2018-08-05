/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dto.PartyRole;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mycompany.biller.dao.PartyRoleDAO;

/**
 *
 * @author ismail
 */
@Repository
public class PartyRoleDAOImpl implements PartyRoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPartyRole(PartyRole partyRole) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(partyRole);
    }

    @Override
    public void updatePartyRole(PartyRole partyRole) {
        Session session = sessionFactory.getCurrentSession();
        session.update(partyRole);
    }

    @Override
    public void deletePartyRole(PartyRole partyRole) {
        sessionFactory.getCurrentSession().delete(partyRole);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PartyRole> listAllPartyRole() {
        Session session = sessionFactory.getCurrentSession();
        List<PartyRole> partyRoleList = session.createQuery("from PartyRole").list();
        return partyRoleList;
    }

    @Override
    public PartyRole findPartyRoleById(Long partyRoleId) {
        return (PartyRole) sessionFactory.getCurrentSession().get(PartyRole.class, partyRoleId);
    }

}
