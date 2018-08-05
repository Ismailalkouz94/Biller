/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.PartyTemplateRepDAO;
import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyTemplateRep;
import com.mycompany.biller.dto.TemplateRep;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Ajarmeh
 */
@Repository
public class PartyTemplateRepDAOImpl implements PartyTemplateRepDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PartyTemplateRep create(PartyTemplateRep partyTemplateRep) {
        Session session = sessionFactory.getCurrentSession();
        session.save(partyTemplateRep);
        return partyTemplateRep;
    }

    @Override
    public PartyTemplateRep update(PartyTemplateRep partyTemplateRep) {
        Session session = sessionFactory.getCurrentSession();
        session.update(partyTemplateRep);
        return partyTemplateRep;
    }

    @Override
    public void delete(PartyTemplateRep partyTemplateRep) {
        sessionFactory.getCurrentSession().delete(partyTemplateRep);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PartyTemplateRep> listAll() {
        Session session = sessionFactory.getCurrentSession();
        List<PartyTemplateRep> partyTemplateRepList = session.createQuery("from PartyTemplateRep").list();
        return partyTemplateRepList;
    }

    @Override
    public PartyTemplateRep findById(Long partyTemplateRepId) {
        return (PartyTemplateRep) sessionFactory.getCurrentSession().get(PartyTemplateRep.class, partyTemplateRepId);
    }

    @Override
    public List<PartyTemplateRep> findByPartyId(Party party) {
        System.out.println("** party " + party.getPartyId());
        Long partyId = party.getPartyId();
        Session session = sessionFactory.getCurrentSession();
        List<PartyTemplateRep> partyTemplateRepList = session.createQuery("from PartyTemplateRep WHERE party.partyId=:partyId")
                .setParameter("partyId", partyId)
                .list();
        return partyTemplateRepList;
    }

    @Override
    public PartyTemplateRep findByTemplateIdAndPartyId(TemplateRep templateRep, Party party) {
        Long partyId = party.getPartyId();
        String templateId = templateRep.getTemplateId();
        Session session = sessionFactory.getCurrentSession();
        PartyTemplateRep partyTemplateRepList = (PartyTemplateRep) session.createQuery("from PartyTemplateRep WHERE party.partyId=:partyId AND templateRep.templateId=:templateId")
                .setParameter("partyId", partyId)
                .setParameter("templateId", templateId)
                .uniqueResult();
        return partyTemplateRepList;
    }

    @Override
    public List<PartyTemplateRep> findByPartyIdAndType(Party party, GlobalItem reportTypeId) {
        Long partyId = party.getPartyId();
        String globalItemId = reportTypeId.getGlobalItemId();
       
        Session session = sessionFactory.getCurrentSession();
        List<PartyTemplateRep> partyTemplateRepList = session.createQuery("from PartyTemplateRep WHERE party.partyId=:partyId AND reportTypeId.globalItemId=:globalItemId")
                .setParameter("partyId", partyId)
                .setParameter("globalItemId", globalItemId)
                .list();
        return partyTemplateRepList;
    }

}
