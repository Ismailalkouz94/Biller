/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dao.PushNotificationsCenterDAO;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.resources.ToBiller;
import com.mycompany.biller.resources.ToPerson;
import com.mycompany.biller.service.PushNotificationsCenterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Abu3jram
 */
@Service
@Transactional
public class PushNotificationsCenterServiceImpl implements PushNotificationsCenterService {

    @Autowired
    private PushNotificationsCenterDAO pushNotificationsCenterDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Override
    public List<ToBiller> findSubscribers(ToBiller toBiller) {
        Long partyId = Long.valueOf((String) toBiller.getPartyId());
        Party party = partyDAO.findById(partyId);
        if (party == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        return pushNotificationsCenterDAO.findSubscribers(toBiller);
    }

    @Override
    public List<ToBiller> findAll(ToBiller toBiller) {
        return pushNotificationsCenterDAO.findAll(toBiller);
    }

    @Override
    public List<ToBiller> findNonSubscribers(ToBiller toBiller) {
        return pushNotificationsCenterDAO.findNonSubscribers(toBiller);
    }

    @Override
    public List<ToPerson> findAllPerson(ToPerson toPerson) {
         return pushNotificationsCenterDAO.findAllPerson(toPerson);
    }

}
