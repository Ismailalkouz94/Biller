package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.PartyProfilePrefDAO;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyProfilePref;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartyProfilePrefDAOImpl implements PartyProfilePrefDAO  {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PartyProfilePref create(PartyProfilePref partyProfilePref) {
        Session session = sessionFactory.getCurrentSession();
        session.save(partyProfilePref);
        return partyProfilePref;
    }

    @Override
    public PartyProfilePref update(PartyProfilePref partyProfilePref) {
        Session session = sessionFactory.getCurrentSession();
        session.update(partyProfilePref);
        return partyProfilePref;
    }

    @Override
    public void delete(PartyProfilePref partyProfilePref) {
        sessionFactory.getCurrentSession().delete(partyProfilePref);
    }

    @Override
    public List<PartyProfilePref> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<PartyProfilePref> partyProfilePrefList = session.createQuery("from PartyProfilePref").list();
        return partyProfilePrefList;
    }

    @Override
    public List<PartyProfilePref> listAllByToParty(Long ToParty) {
        String selectQuery = "FROM PartyProfilePref WHERE toParty.partyId = :partyId";
        return sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("partyId", ToParty)
                .list();
    }

    @Override
    public List<PartyProfilePref> findBySubscriptionNo(Long subscriptionNo) {
        String selectQuery = "FROM PartyProfilePref WHERE subscriptionNo = :subscriptionNo";
        return sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("subscriptionNo", subscriptionNo)
                .list();
    }

    @Override
    public PartyProfilePref findById(Long id) {
        return (PartyProfilePref) sessionFactory.getCurrentSession().get(PartyProfilePref.class, id);
    }
}
