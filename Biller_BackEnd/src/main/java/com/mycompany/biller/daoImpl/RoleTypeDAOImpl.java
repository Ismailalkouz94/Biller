/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dto.RoleType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mycompany.biller.dao.RoleTypeDAO;

/**
 *
 * @author ismail
 */
@Repository
public class RoleTypeDAOImpl implements RoleTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRoleType(RoleType roleType) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(roleType);
    }

    @Override
    public void updateRoleType(RoleType roleType) {
        Session session = sessionFactory.getCurrentSession();
        session.update(roleType);
    }

    @Override
    public void deleteRoleType(RoleType roleType) {
        sessionFactory.getCurrentSession().delete(roleType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RoleType> listAllRoleType() {
        Session session = sessionFactory.getCurrentSession();
        List<RoleType> TypeList = session.createQuery("from RoleType").list();
        return TypeList;
    }

    @Override
    public RoleType findRoleTypeById(Long id) {
        return (RoleType) sessionFactory.getCurrentSession().get(RoleType.class, id);
    }

}
