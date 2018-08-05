/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dto.UserLogin;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mycompany.biller.dao.UserLoginDAO;
import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.resources.MenusTree;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Admin
 */
@Repository
public class UserLoginDAOImpl implements UserLoginDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUserLogin(UserLogin userLogin) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userLogin);
    }

    @Override
    public void updateUserLogin(UserLogin userLogin) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userLogin);
    }

    @Override
    public void deleteUserLogin(UserLogin userLogin) {
        sessionFactory.getCurrentSession().delete(userLogin);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserLogin> listAllUserLogin() {
        Session session = sessionFactory.getCurrentSession();
        List<UserLogin> userLoginList = session.createQuery("from UserLogin").list();
        return userLoginList;
    }

    @Override
    public UserLogin findById(Long id) {
        return (UserLogin) sessionFactory.getCurrentSession().get(UserLogin.class, id);
    }

    @Override
    public boolean checkLogin(String userName, String password, Long partyId) {
        String selectQuery = "FROM UserLogin WHERE userName = :userName and party.partyId =:partyId";
        UserLogin userLogin = (UserLogin) sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("userName", userName)
                .setParameter("partyId", partyId)
                .uniqueResult();

        if (userLogin == null) {
            return false;
        } else {
            return passwordEncoder.matches(password, userLogin.getPassword());
        }
    }

    @Override
    public boolean UserLoginIsExist(String userName, Long partyId) {
        String selectQuery = "FROM UserLogin WHERE userName = :userName and party.partyId =:partyId";
        UserLogin userLogin = (UserLogin) sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("userName", userName)
                .setParameter("partyId", partyId)
                .uniqueResult();

        if (userLogin == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<MenusTree> userLoginRoleQuery(String userName, Long partyId) {
        List<MenusTree> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT SUBSTR (SYS_CONNECT_BY_PATH (DESCRIPTION, '->'), 3) tree,\n"
                        + "LEVEL - 1 AS LVL,\n"
                        + "CONNECT_BY_ISLEAF AS leaf,\n"
                        + "PARENT_MENUS_ID AS parentMenusId,\n"
                        + "DESCRIPTION AS name,\n"
                        + "MENUS_ID AS menusId,\n"
                        + "MENUS_STATE AS state,\n"
                        + "MENUS_TYPE AS type,\n"
                        + "MENUS_ICON AS icon\n"
                        + "FROM MENUS\n"
                        + "Where MENUS_ID in (SELECT MU.MENUS_ID\n"
                        + "FROM USER_LOGIN UL,\n"
                        + "USER_ROLE UR,\n"
                        + "ROLE_GROUP RG,\n"
                        + "MENU_ROLE MR,\n"
                        + "MENUS MU\n"
                        + "WHERE UL.USER_NAME = :userName\n"
                        + "AND UL.PARTY_ID = :partyId\n"
                        + "AND UL.USER_LOGIN_ID = UR.USER_LOGIN_ID\n"
                        + "AND UR.ROLE_GROUP_ID = RG.ROLE_GROUP_ID\n"
                        + "AND RG.ROLE_GROUP_ID = MR.ROLEGROUP_ROLE_GROUP_ID\n"
                        + "AND MR.MENUS_MENUS_ID = MU.MENUS_ID)\n"
                        + "START WITH PARENT_MENUS_ID IS NULL\n"
                        + "CONNECT BY PRIOR MENUS_ID = PARENT_MENUS_ID")
                .addScalar("LVL")
                .addScalar("leaf")
                .addScalar("parentMenusId")
                .addScalar("name")
                .addScalar("menusId")
                .addScalar("state")
                .addScalar("type")
                .addScalar("icon")
                .setResultTransformer(Transformers.aliasToBean(MenusTree.class))
                .setParameter("userName", userName)
                .setParameter("partyId", partyId)
                .list();

        if (resultList.size() > 0) {
            return (List<MenusTree>) resultList;
        }
        return null;
    }

    @Override
    public List<MenusTree> userLoginParent(String userName, Long partyId) {
        List<MenusTree> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT PARENT_MENUS_ID AS parentMenusId,\n"
                        + "DESCRIPTION AS name,\n"
                        + "MENUS_ID AS menusId,\n"
                        + "MENUS_STATE AS state,\n"
                        + "MENUS_TYPE AS type,\n"
                        + "MENUS_ICON AS icon\n"
                        + "FROM MENUS\n"
                        + "Where MENUS_TYPE != 'child'\n"
                        + "and MENUS_ID in (SELECT MU.MENUS_ID\n"
                        + "FROM USER_LOGIN UL,\n"
                        + "USER_ROLE UR,\n"
                        + "ROLE_GROUP RG,\n"
                        + "MENU_ROLE MR,\n"
                        + "MENUS MU\n"
                        + "WHERE UL.USER_NAME = :userName\n"
                        + "AND UL.PARTY_ID = :partyId\n"
                        + "AND UL.USER_LOGIN_ID = UR.USER_LOGIN_ID\n"
                        + "AND UR.ROLE_GROUP_ID = RG.ROLE_GROUP_ID\n"
                        + "AND RG.ROLE_GROUP_ID = MR.ROLEGROUP_ROLE_GROUP_ID\n"
                        + "AND MR.MENUS_MENUS_ID = MU.MENUS_ID)")
                .addScalar("parentMenusId")
                .addScalar("name")
                .addScalar("menusId")
                .addScalar("state")
                .addScalar("type")
                .addScalar("icon")
                .setResultTransformer(Transformers.aliasToBean(MenusTree.class))
                .setParameter("userName", userName)
                .setParameter("partyId", partyId)
                .list();

        if (resultList.size() > 0) {
            return (List<MenusTree>) resultList;
        }
        return null;
    }

    @Override
    public List<MenusTree> userLoginChild(String userName, Long partyId, Long menusId) {
        List<MenusTree> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT PARENT_MENUS_ID AS parentMenusId,\n"
                        + "DESCRIPTION AS name,\n"
                        + "MENUS_ID AS menusId,\n"
                        + "MENUS_STATE AS state,\n"
                        + "MENUS_TYPE AS type,\n"
                        + "MENUS_ICON AS icon\n"
                        + "FROM MENUS\n"
                        + "Where MENUS_TYPE = 'child'\n"
                        + "and PARENT_MENUS_ID = :menusId\n"
                        + "and MENUS_ID in (SELECT MU.MENUS_ID\n"
                        + "FROM USER_LOGIN UL,\n"
                        + "USER_ROLE UR,\n"
                        + "ROLE_GROUP RG,\n"
                        + "MENU_ROLE MR,\n"
                        + "MENUS MU\n"
                        + "WHERE UL.USER_NAME = :userName\n"
                        + "AND UL.PARTY_ID = :partyId\n"
                        + "AND UL.USER_LOGIN_ID = UR.USER_LOGIN_ID\n"
                        + "AND UR.ROLE_GROUP_ID = RG.ROLE_GROUP_ID\n"
                        + "AND RG.ROLE_GROUP_ID = MR.ROLEGROUP_ROLE_GROUP_ID\n"
                        + "AND MR.MENUS_MENUS_ID = MU.MENUS_ID)")
                .addScalar("parentMenusId")
                .addScalar("name")
                .addScalar("menusId")
                .addScalar("state")
                .addScalar("type")
                .addScalar("icon")
                .setResultTransformer(Transformers.aliasToBean(MenusTree.class))
                .setParameter("userName", userName)
                .setParameter("partyId", partyId)
                .setParameter("menusId", menusId)
                .list();

        if (resultList.size() > 0) {
            return (List<MenusTree>) resultList;
        }
        return null;
    }

    @Override
    public UserLogin findByUserName(String userName, Party party) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("FROM UserLogin WHERE userName = :userName And party=:party");
        query.setParameter("userName", userName)
                .setParameter("party", party);
        UserLogin userLogin = (UserLogin) query.uniqueResult();
        return userLogin;
    }

    @Override
    public List<UserLogin> findByPartyId(Long partyId) {
        String selectQuery = "FROM UserLogin WHERE party.partyId = :partyId";
        return sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("partyId", partyId)
                .list();
    }
}
