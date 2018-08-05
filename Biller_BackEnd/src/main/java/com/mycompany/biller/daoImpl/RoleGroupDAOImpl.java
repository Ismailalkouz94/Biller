/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.RoleGroupDAO;
import com.mycompany.biller.dto.RoleGroup;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ismail
 */
@Repository
public class RoleGroupDAOImpl implements RoleGroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRoleGroup(RoleGroup roleGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(roleGroup);
    }

    @Override
    public void updateRoleGroup(RoleGroup roleGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.update(roleGroup);
    }

    @Override
    public void deleteRoleGroup(RoleGroup roleGroup) {
        sessionFactory.getCurrentSession().delete(roleGroup);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RoleGroup> listAllRoleGroup() {
        Session session = sessionFactory.getCurrentSession();
        List<RoleGroup> roleGroupList = session.createQuery("from RoleGroup").list();
        return roleGroupList;
    }

    @Override
    public RoleGroup findById(Long id) {
        return (RoleGroup) sessionFactory.getCurrentSession().get(RoleGroup.class, id);
    }

    @Override
    public List<RoleGroup> findByCriteria(Long roleGroupId, String description) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RoleGroup.class);
        if (roleGroupId != null) {
            criteria.add(Restrictions.eq("roleGroupId", roleGroupId));
        }
        if (description != null) {
            criteria.add(Restrictions.like("description", description, MatchMode.ANYWHERE).ignoreCase());
        }
        return criteria.list();
    }

}
