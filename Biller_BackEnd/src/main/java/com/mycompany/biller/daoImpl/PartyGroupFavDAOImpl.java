/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.mycompany.biller.dao.PartyGroupFavDAO;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyGroupFav;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mycompany.biller.dto.PartyGroup;
import com.mycompany.biller.dto.UserLogin;
import java.util.ArrayList;
import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
@Repository
public class PartyGroupFavDAOImpl implements PartyGroupFavDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PartyGroupFav create(PartyGroupFav partyGroupFav) {
        Session session = sessionFactory.getCurrentSession();
        session.save(partyGroupFav);
        return partyGroupFav;
    }

    @Override
    public PartyGroupFav update(PartyGroupFav partyGroupFav) {
        Session session = sessionFactory.getCurrentSession();
        session.update(partyGroupFav);
        return partyGroupFav;
    }

    @Override
    public void delete(PartyGroupFav partyGroupFav) {
        sessionFactory.getCurrentSession().delete(partyGroupFav);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PartyGroupFav> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<PartyGroupFav> PartyGroupFavList = session.createQuery("from PartyGroupFav").list();
        return PartyGroupFavList;
    }

    @Override
    public List<PartyGroupFav> listAllFavByParty(Party party) {
        String selectQuery = "FROM PartyGroupFav WHERE PARTY_ID = :partyId";
        return sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("partyId", party.getPartyId())
                .list();
    }

    public PartyGroupFav findByPartyAndPartyFav(Party party, Party partyFav) {
        String selectQuery = "FROM PartyGroupFav WHERE PARTY_ID = :partyId AND PARTY_FAV = :partyFav";
        return (PartyGroupFav) sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery)
                .setParameter("partyId", party.getPartyId())
                .setParameter("partyFav", partyFav.getPartyId()).uniqueResult();
    }

    @Override
    public List<Party> listFavPartyGroup(Party party) {
//        String selectQuery = "from PartyGroup P,PartyGroupFav V where P.party.partyId = V.partyFav.partyId and V.party.partyId = :partyId ";
//        System.out.println("selectQuery " + selectQuery);
//
//        return sessionFactory
//                .getCurrentSession()
//                .createQuery(selectQuery)
//                .setParameter("partyId", party.getPartyId())
//                .list();

        Query query = sessionFactory.getCurrentSession().
                createQuery("select P.party.partyId,P.tradeName from PartyGroup P,PartyGroupFav V where P.party.partyId = V.partyFav.partyId and V.party.partyId = :partyId ");
        query.setParameter("partyId", party.getPartyId());
//        query.list();
        ArrayList list = new ArrayList();
        List<Object[]> rows = query.list();
        for (Object[] row : rows) {
            HashMap mMap = new HashMap();
            mMap.put("partyId",row[0]);
            mMap.put("name",row[1]);
            list.add(mMap);
        }
        return list;

    }
}
