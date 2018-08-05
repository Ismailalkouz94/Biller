/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.MenusDAO;
import com.mycompany.biller.dto.Menus;
import com.mycompany.biller.resources.MenusTree;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ismail
 */
@Repository
public class MenusDAOImpl implements MenusDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMenus(Menus menus) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(menus);
    }

    @Override
    public void updateMenus(Menus menus) {
        Session session = sessionFactory.getCurrentSession();
        session.update(menus);
    }

    @Override
    public void deleteMenus(Menus menus) {
        sessionFactory.getCurrentSession().delete(menus);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Menus> listAllMenus() {
        Session session = sessionFactory.getCurrentSession();
        List<Menus> menusList = session.createQuery("from Menus").list();
        return menusList;
    }

    @Override
    public Menus findById(Long id) {
        return (Menus) sessionFactory.getCurrentSession().get(Menus.class, id);
    }

    @Override
    public List<MenusTree> menusTree() {
        List<MenusTree> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("select substr(sys_connect_by_path(MENUS_ID,'->'),3) tree,level-1 as LVL,\n"
                        + "CONNECT_BY_ISLEAF as leaf,PARENT_MENUS_ID as parentMenusId,DESCRIPTION as name ,MENUS_ID as menusId,\n"
                        + "MENUS_STATE as state,MENUS_TYPE as type,MENUS_ICON as icon\n"
                        + "from MENUS\n"
                        + "start with PARENT_MENUS_ID IS NULL\n"
                        + "connect by prior MENUS_ID = PARENT_MENUS_ID\n"
                        + "order by tree")
                .addScalar("LVL")
                .addScalar("leaf")
                .addScalar("parentMenusId")
                .addScalar("name")
                .addScalar("menusId")
                .addScalar("state")
                .addScalar("type")
                .addScalar("icon")
                .setResultTransformer(Transformers.aliasToBean(MenusTree.class))
                //                .setParameter("partyId", partyId)
                .list();

        return (List<MenusTree>) resultList;
    }
}
