/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.PartyCategoryDetailsDAO;
import com.mycompany.biller.dto.PartyCategoryDetails;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rbab3a
 */
@Repository
public class PartyCategoryDetailsDAOImpl implements PartyCategoryDetailsDAO{

        @Autowired
    private SessionFactory sessionFactory;
        
    @Override
    public PartyCategoryDetails create(PartyCategoryDetails partyCategoryDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PartyCategoryDetails update(PartyCategoryDetails partyCategoryDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PartyCategoryDetails partyCategoryDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PartyCategoryDetails> findAll() {
return (List<PartyCategoryDetails>)sessionFactory.getCurrentSession().createQuery("from PartyCategoryDetails").list();

    }

    @Override
    public PartyCategoryDetails findById(Long catId) {
return (PartyCategoryDetails)sessionFactory.getCurrentSession().get(PartyCategoryDetails.class, catId);

    }

    @Override
    public List<PartyCategoryDetails> findByPartyCategroyId(Long partyCategoryId) {
return (List<PartyCategoryDetails>)sessionFactory.getCurrentSession().createQuery("from PartyCategoryDetails where partyCategroy.catId=:partyCategoryId")
        .setParameter("partyCategoryId", partyCategoryId).list();


    }
    
}
