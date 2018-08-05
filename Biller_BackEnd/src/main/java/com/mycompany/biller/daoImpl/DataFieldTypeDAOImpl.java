/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.DataFieldTypeDAO;
import com.mycompany.biller.dto.DataFieldType;
import com.mycompany.biller.dto.Party;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Ajarmeh
 */
@Repository
public class DataFieldTypeDAOImpl implements DataFieldTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DataFieldType create(DataFieldType dataFieldType) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dataFieldType);
        return dataFieldType;
    }

    @Override
    public DataFieldType update(DataFieldType dataFieldType) {
        Session session = sessionFactory.getCurrentSession();
        session.update(dataFieldType);
        return dataFieldType;
    }

    @Override
    public void delete(DataFieldType dataFieldType) {
        sessionFactory.getCurrentSession().delete(dataFieldType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DataFieldType> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<DataFieldType> dataFieldTypeList = session.createQuery("from DataFieldType").list();
        return dataFieldTypeList;
    }

    @Override
    public DataFieldType findById(Long dataFieldTypeId) {
        return (DataFieldType) sessionFactory.getCurrentSession().get(DataFieldType.class, dataFieldTypeId);
    }

    @Override
    public List<DataFieldType> findByPartyId(Party party) {
        Long partyId = party.getPartyId();
        Session session = sessionFactory.getCurrentSession();
        List<DataFieldType> dataFieldTypeList = session.createQuery("from DataFieldType WHERE party.partyId=:partyId")
                .setParameter("partyId", partyId)
                .list();
        return dataFieldTypeList;
    }

}
