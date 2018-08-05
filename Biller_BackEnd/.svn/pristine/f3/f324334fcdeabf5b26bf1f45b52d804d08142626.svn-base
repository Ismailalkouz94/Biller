/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dto.Orders;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mycompany.biller.dao.OrdersDAO;
import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.exception.NotFoundException;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * @author Admin
 */
@Repository
public class OrdersDAOImpl implements OrdersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Orders create(Orders orders) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(orders);
            return orders;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    public Orders update(Orders orders) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(orders);
            return orders;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    public void delete(Orders orders) {
        try {
            sessionFactory.getCurrentSession().delete(orders);
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    public int changeStatus(Long orderId, String status) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query qry = session.createQuery("UPDATE Orders SET globalItem.globalItemId=:status WHERE orderId=:orderId");
            qry.setParameter("status", status)
                    .setParameter("orderId", orderId);
            int res = qry.executeUpdate();
            return res;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> listAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Orders> orderList = session.createQuery("from Orders").list();
            return orderList;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> listAll(int page, int size) {

        int pageNew = (page - 1) * size;
        if(page>1){
            size = (page -1) * size;
        }else{
            size = (page) * size;
        }

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Orders order by orderId");
        query.setFirstResult(pageNew);
        query.setMaxResults(size);
        List<Orders> orderList = query.list();
        return orderList;
    }

    @Override
    public Orders findById(Long orderId) {
        try {
            return (Orders) sessionFactory.getCurrentSession()
                    .createQuery("From Orders WHERE orderId=:orderId")
                    .setParameter("orderId", orderId).uniqueResult();
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    public List<Orders> findByFromPartyId(Long fromPartyId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Orders> orderList = session.createQuery("From Orders WHERE fromParty.partyId=:fromPartyId")
                    .setParameter("fromPartyId", fromPartyId).list();
            return orderList;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    public List<Orders> findByToPartyId(Long toPartyId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Orders> orderList = session.createQuery("FROM Orders WHERE toParty.partyId=:toPartyId AND globalItem.globalItemId!='CREATED'")
                    .setParameter("toPartyId", toPartyId).list();
            return orderList;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    //lookup //Dropdown
    public List<Orders> findTo(Long toPartyId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Orders> orderList = session.createQuery("SELECT DISTINCT O.fromParty.partyGroup.party.partyId , O.fromParty.partyGroup.tradeName  FROM Orders O WHERE O.toParty.partyId=:toPartyId")
                    .setParameter("toPartyId", toPartyId).list();
            return orderList;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    //lookup //Dropdown
    public List<Orders> findFrom(Long fromPartyId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Orders> orderList = session.createQuery("SELECT DISTINCT O.toParty.partyGroup.party.partyId , O.toParty.partyGroup.tradeName  FROM Orders O WHERE O.fromParty.partyId=:fromPartyId")
                    .setParameter("fromPartyId", fromPartyId).list();
            return orderList;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    public List<Orders> findByCriteria(Long orderId, String orderCode, String name, String description, GlobalItem globalItem, Date orderDateLess, Date orderDateGreater, Party toParty, Party fromParty) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Orders.class);
            if (orderId != null) {
                criteria.add(Restrictions.eq("orderId", orderId));
            }
            if (orderCode != null) {
                criteria.add(Restrictions.like("orderCode", orderCode, MatchMode.ANYWHERE).ignoreCase());
            }
            if (name != null) {
                criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
            }
            if (description != null) {
                criteria.add(Restrictions.like("description", description, MatchMode.ANYWHERE).ignoreCase());
            }
            if (globalItem != null) {
                criteria.add(Restrictions.eq("globalItem", globalItem));
            }
            if (orderDateLess != null) {
                criteria.add(Restrictions.le("orderDate", orderDateLess));
            }
            if (orderDateGreater != null) {
                criteria.add(Restrictions.ge("orderDate", orderDateGreater));
            }
            if (toParty != null) {
                criteria.add(Restrictions.eq("toParty.partyId", toParty.getPartyId()));
            }
            if (fromParty != null) {
                criteria.add(Restrictions.eq("fromParty.partyId", fromParty.getPartyId()));
            }
            return criteria.list();
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    public Orders findByOrderIdAndFromPartyId(Orders orders, Party fromParty) {
        Long orderId = orders.getOrderId();
        Long partyId = fromParty.getPartyId();
        Session session = sessionFactory.getCurrentSession();
        Orders ordersResult = (Orders) session.createQuery("From Orders WHERE orderId=:orderId AND fromParty.partyId=:fromPartyId")
                .setParameter("orderId", orderId)
                .setParameter("fromPartyId", partyId)
                .uniqueResult();
        return ordersResult;
    }

    @Override
    public Orders findByOrderIdAndToPartyId(Orders orders, Party toParty) {
        Long orderId = orders.getOrderId();
        Long partyId = toParty.getPartyId();
        Session session = sessionFactory.getCurrentSession();
        Orders ordersResult = (Orders) session.createQuery("From Orders WHERE orderId=:orderId AND toParty.partyId=:toParty")
                .setParameter("orderId", orderId)
                .setParameter("toParty", partyId)
                .uniqueResult();
        return ordersResult;
    }
}
