/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dto.PartyGroup;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mycompany.biller.dao.PartyGroupDAO;
import com.mycompany.biller.dto.UserLogin;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/**
 *
 * @author ismail
 */
@Repository
public class PartyGroupDAOImpl implements PartyGroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPartyGroup(PartyGroup partyGroup) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(partyGroup);
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().save(partyGroup);
    }

    @Override
    public void updatePartyGroup(PartyGroup partyGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.update(partyGroup);
    }

    @Override
    public void deletePartyGroup(PartyGroup partyGroup) {
        sessionFactory.getCurrentSession().delete(partyGroup);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PartyGroup> listAllPartyGroup() {
        Session session = sessionFactory.getCurrentSession();
        List<PartyGroup> partyGroups = session.createQuery("from PartyGroup").list();
        return partyGroups;
    }

    @Override
    public PartyGroup findPartyGroupById(Long partyGroupId) {
        return (PartyGroup) sessionFactory.getCurrentSession().get(PartyGroup.class, partyGroupId);
    }

    @Override
    public PartyGroup createPartyGroup(PartyGroup partyGroup) {
        Long id = (Long) sessionFactory.getCurrentSession().save(partyGroup);

//        partyGroup.setPartyGroupId(id);
        return partyGroup;
    }
//    public List<Account> getAccountListByOrgName(String name){
//    return sessionFactory.getCurrentSession().createCriteria(Account.class)
//                .createAlias("book", "book")
//                .createAlias("book.organization", "organization")
//                .add(Restrictions.eq("organization.name", name))
//                .list();
//}

    @Override
    public List<PartyGroup> findByCriteria(Long partyGroupId, String partyCode, String partyActivity, String partyGroupName, String brandName, String category) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PartyGroup.class);

        criteria.createAlias("party", "party");

        criteria.add(Restrictions.eq("party.isActive", 'Y'));
        if (partyGroupId != null) {
            criteria.add(Restrictions.eq("partyGroupId", partyGroupId));
        }
        if (partyCode != null) {
            criteria.add(Restrictions.like("party.partyCode", partyCode, MatchMode.ANYWHERE).ignoreCase());
        }
        if (partyActivity != null) {
            criteria.add(Restrictions.like("partyActivity", partyActivity, MatchMode.ANYWHERE).ignoreCase());
        }
        if (partyGroupName != null) {
            criteria.add(Restrictions.like("partyGroupName", partyGroupName, MatchMode.ANYWHERE).ignoreCase());
        }
        if (brandName != null) {
            criteria.add(Restrictions.like("brandName", brandName, MatchMode.ANYWHERE).ignoreCase());
        }
        if (category != null) {
            criteria.add(Restrictions.like("category", category, MatchMode.ANYWHERE).ignoreCase());
        }

        return criteria.list();
    }

    @Override
    public PartyGroup findByPartyId(Long partyId) {
        Query query = sessionFactory.getCurrentSession().
                    createQuery("FROM PartyGroup WHERE party.partyId =:partyId");
        query.setParameter("partyId", partyId);
        PartyGroup partyGroup = (PartyGroup) query.uniqueResult();
        return partyGroup;
    }

}
