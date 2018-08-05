/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.UserPreferenceDAO;
import com.mycompany.biller.dto.UserPreference;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ismail
 */
@Repository
public class UserPreferenceDAOImpl implements UserPreferenceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(UserPreference userPreference) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userPreference);
    }

    @Override
    public void update(UserPreference userPreference) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userPreference);
    }

    @Override
    public void update(Long userLoginId, String key, String valueٍ) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.createQuery("UPDATE UserPreference  SET value=:value WHERE key=:key AND userLogin.userLoginId=:userLoginId");
        qry.setParameter("value", valueٍ)
                .setParameter("userLoginId", userLoginId)
                .setParameter("key", key);
        int res = qry.executeUpdate();
    }

    @Override
    public void delete(UserPreference userPreference) {
        sessionFactory.getCurrentSession().delete(userPreference);
    }

    @Override
    public List<UserPreference> findByUserLoginId(Long userLoginId) {
        Session session = sessionFactory.getCurrentSession();
        List<UserPreference> userPreferenceList = session.createQuery("from UserPreference where userLogin.userLoginId=:userLoginId")
                .setParameter("userLoginId", userLoginId)
                .list();
        return userPreferenceList;
    }

    @Override
    public UserPreference findByKey(Long userLoginId, String key) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from UserPreference where userLogin.userLoginId=:userLoginId AND key=:key");
        query.setParameter("userLoginId", userLoginId)
                .setParameter("key", key);
        UserPreference userPreference = (UserPreference) query.uniqueResult();
        return userPreference;

    }

    @Override
    public UserPreference findById(Long id) {
        return (UserPreference) sessionFactory.getCurrentSession().get(UserPreference.class, id);
    }

}
