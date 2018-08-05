/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.UnitsDAO;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.dto.UserLogin;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class UnitsDAOImpl implements UnitsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Units create(Units units) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(units);
        return units;
    }

    @Override
    public Units update(Units units) {
        Session session = sessionFactory.getCurrentSession();
        session.update(units);
        return units;
    }

    @Override
    public void delete(Units units) {
        sessionFactory.getCurrentSession().delete(units);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Units> listAll(Long partyId) {
        Session session = sessionFactory.getCurrentSession();
        List<Units> unitsList = session.createQuery("FROM Units Where party.partyId=:partyId")
                .setParameter("partyId", partyId).list();
        return unitsList;
    }

    @Override
    public Units findById(Long unitsId) {
        return (Units) sessionFactory.getCurrentSession().get(Units.class, unitsId);
    }

    @Override
    public Units findById(Long unitId, Long partyId) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("FROM Units WHERE party.partyId=:partyId AND unitId=:unitId");
        query.setParameter("partyId", partyId)
                .setParameter("unitId", unitId);
        Units units = (Units) query.uniqueResult();
        return units;
    }

    @Override
    public List<Units> findByCriteria(Long unitId, String name, Long partyId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Units.class);
        if (unitId != null) {
            criteria.add(Restrictions.eq("unitId", unitId));
        }
        if (name != null) {
            criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
        }
        if (partyId != null) {
            criteria.add(Restrictions.eq("party.partyId", partyId));
        }

        return criteria.list();
    }

}
