/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.CategoryDAO;
import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.resources.CategoryTree;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Admin
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Category create(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.update(category);
        return category;
    }

    @Override
    public void delete(Category category) {
        sessionFactory.getCurrentSession().delete(category);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> categoryList = session.createQuery("from Category").list();
        return categoryList;
    }

    @Override
    public List<Category> findByPartyId(Long partyId) {
        Session session = sessionFactory.getCurrentSession();
        List<Category> categoryList = session.createQuery("FROM Category WHERE party.partyId=:partyId")
                .setParameter("partyId", partyId)
                .list();
        return categoryList;
    }

    @Override
    public Category findById(Long categoryId) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, categoryId);
    }

    @Override
    public Category findById(Long categoryId, Long partyId) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("FROM Category WHERE party.partyId=:partyId AND categoryId=:categoryId");
        query.setParameter("partyId", partyId)
                .setParameter("categoryId", categoryId);
        Category category = (Category) query.uniqueResult();
        return category;
    }

    @Override
    public List<CategoryTree> categoryTree(Long partyId) {
        List<CategoryTree> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("select substr(sys_connect_by_path(CATEGORY_ID,'->'),3) tree,level-1 as LVL,\n"
                        + "CONNECT_BY_ISLEAF as leaf,PARENT_CATEGORY_ID as parentCategoryId,CATEGORY_ID as categoryId, DESCRIPTION as description\n"
                        + "from CATEGORY\n"
                        + "where PARTY_ID = :partyId\n"
                        + "start with PARENT_CATEGORY_ID IS NULL\n"
                        + "connect by prior CATEGORY_ID = PARENT_CATEGORY_ID\n"
                        + "order by tree")
                .addScalar("LVL")
                .addScalar("leaf")
                .addScalar("parentCategoryId")
                .addScalar("categoryId")
                .addScalar("description")
                .setResultTransformer(Transformers.aliasToBean(CategoryTree.class))
                .setParameter("partyId", partyId)
                .list();

        return (List<CategoryTree>) resultList;
    }

    public List<Category> findByCriteria(Long categoryId, String description, Long parentCategoryId, Party party) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        if (categoryId != null) {
            criteria.add(Restrictions.eq("categoryId", categoryId));
        }
        if (parentCategoryId != null) {
            criteria.add(Restrictions.eq("parentCategoryId", parentCategoryId));
        }
        if (description.length()!= 0 && !description.equals("null") && !description.equals("")) {
            System.out.println("*"+description+"*");
            criteria.add(Restrictions.like("description", description, MatchMode.ANYWHERE).ignoreCase());
        }
        if (party != null) {
            criteria.add(Restrictions.eq("party", party));
        }

        return criteria.list();
    }

}
