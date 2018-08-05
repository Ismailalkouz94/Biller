/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.CompanyTypeDAO;
import com.mycompany.biller.dto.CompanyType;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rbab3a
 */
@Repository
public class CompanyTypeDAOImpl implements CompanyTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CompanyType create(CompanyType companyType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyType update(CompanyType companyType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CompanyType companyType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompanyType> listAll() {
  return (List<CompanyType>) sessionFactory.getCurrentSession()
                .createQuery("From CompanyType")
                .list();

    }

    @Override
    public CompanyType findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
