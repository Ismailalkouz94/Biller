/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.OrderItemsDAO;
import com.mycompany.biller.dto.OrderItems;
import com.mycompany.biller.dto.Orders;
import java.util.List;
import java.util.Vector;

import com.mycompany.biller.exception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class OrderItemsDAOImpl implements OrderItemsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public OrderItems create(OrderItems orderItems) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(orderItems);
        return orderItems;
    }

    @Override
    public OrderItems update(OrderItems orderItems) {
        Session session = sessionFactory.getCurrentSession();
        session.update(orderItems);
        return orderItems;
    }

    @Override
    public void delete(OrderItems orderItems) {
        sessionFactory.getCurrentSession().delete(orderItems);
    }

    @Override
    public int deleteByOrderId(Long orderId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query qry = session.createQuery("DELETE FROM OrderItems WHERE order.orderId=:orderId");
            qry.setParameter("orderId", orderId);
            int res = qry.executeUpdate();
            return res;
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderItems> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<OrderItems> orderItemsList = session.createQuery("from OrderItems").list();
        return orderItemsList;
    }

    @Override
    public OrderItems findById(Long orderItemsId) {
        return (OrderItems) sessionFactory.getCurrentSession()
                .createQuery("From OrderItems WHERE orderItemId=:orderItemsId")
                .setParameter("orderItemsId",orderItemsId)
                .uniqueResult();
    }

    @Override
    public List<OrderItems> findByOrderId(Long orderId) {
        String selectQuery = "FROM OrderItems WHERE ORDER_ID = :orderId";
        List<OrderItems> listOrderItems =  sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("orderId", orderId)
                .list();
        return listOrderItems;
    }

}
