/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.UserRoleDAO;
import com.mycompany.biller.dto.UserRole;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ismail
 */
@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserRole addUserRole(UserRole userRole) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userRole);
        return userRole;
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userRole);
        return userRole;
    }

    @Override
    public void deleteUserRole(UserRole userRole) {
        sessionFactory.getCurrentSession().delete(userRole);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserRole> listAllUserRole() {
        Session session = sessionFactory.getCurrentSession();
        List<UserRole> userRoleList = session.createQuery("from UserRole").list();
        return userRoleList;
    }

    @Override
    public UserRole findById(Long id) {
        return (UserRole) sessionFactory.getCurrentSession().get(UserRole.class, id);
    }

}
