/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.PartyCategoryDAO;
import com.mycompany.biller.dto.PartyCategory;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rbab3a
 */
@Repository
public class PartyCategoryDAOImpl implements PartyCategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PartyCategory create(PartyCategory partyCategory) {
        sessionFactory.getCurrentSession().save(partyCategory);
        return partyCategory;
    }

    @Override
    public PartyCategory update(PartyCategory partyCategory) {

sessionFactory.getCurrentSession().update(partyCategory);
return partyCategory;
    }

    @Override
    public void delete(PartyCategory partyCategory) {
sessionFactory.getCurrentSession().delete(partyCategory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PartyCategory> findAll() {
        return (List<PartyCategory>) sessionFactory.getCurrentSession().createQuery("from PartyCategory").list();
    }

    @Override
    public PartyCategory findById(Long catId) {
return (PartyCategory) sessionFactory.getCurrentSession().get(PartyCategory.class, catId);
    }

}
