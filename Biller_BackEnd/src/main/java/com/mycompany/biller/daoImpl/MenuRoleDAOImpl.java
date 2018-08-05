/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.MenuRoleDAO;
import com.mycompany.biller.dto.MenuRole;
import com.mycompany.biller.dto.RoleGroup;
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
public class MenuRoleDAOImpl implements MenuRoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMenuRole(MenuRole menuRole) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(menuRole);
    }

    @Override
    public void updateMenuRole(MenuRole menuRole) {
        Session session = sessionFactory.getCurrentSession();
        session.update(menuRole);
    }

    @Override
    public void deleteMenuRole(MenuRole menuRole) {
        sessionFactory.getCurrentSession().delete(menuRole);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MenuRole> listAllMenuRole() {
        Session session = sessionFactory.getCurrentSession();
        List<MenuRole> menuRoleList = session.createQuery("from MenuRole").list();
        return menuRoleList;
    }

    @Override
    public MenuRole findById(Long menuRoleId) {
        return (MenuRole) sessionFactory.getCurrentSession().get(MenuRole.class, menuRoleId);
    }

    @Override
    public List<MenuRole> findByRoleGroupId(RoleGroup roleGroup) {
        Long roleGroupId = roleGroup.getRoleGroupId();
        Session session = sessionFactory.getCurrentSession();
        List<MenuRole> menuRoles = session.createQuery("From MenuRole WHERE roleGroup.roleGroupId=:roleGroupId")
                .setParameter("roleGroupId", roleGroupId)
                .list();
        return menuRoles;
    }

}
