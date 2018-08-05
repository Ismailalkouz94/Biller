/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartyGroupFavDAO;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyGroup;
import com.mycompany.biller.dto.PartyGroupFav;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.PartyGroupFavService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class PartyGroupFavServiceImpl implements PartyGroupFavService {

    @Autowired
    private PartyGroupFavDAO partyGroupFavDAO;

    @Override
    public PartyGroupFav create(PartyGroupFav partyGroupFav) {
        return partyGroupFavDAO.create(partyGroupFav);
    }

    @Override
    public PartyGroupFav update(PartyGroupFav partyGroupFav) {
        return partyGroupFavDAO.update(partyGroupFav);
    }

    @Override
    public void delete(Party party, Party partyFav) {
        PartyGroupFav partyGroupFav = partyGroupFavDAO.findByPartyAndPartyFav(party, partyFav);
        if (partyGroupFav == null) {
            throw new NotFoundException("Party Group Favourite Information Not Exist");
        }
        partyGroupFavDAO.delete(partyGroupFav);
    }

    @Override
    public List<PartyGroupFav> listAll() {
        return partyGroupFavDAO.listAll();
    }

    @Override
    public List<PartyGroupFav> listAllFavByParty(Party party) {
        return partyGroupFavDAO.listAllFavByParty(party);
    }

    public PartyGroupFav findByPartyAndPartyFav(Party party, Party partyFav) {
        return partyGroupFavDAO.findByPartyAndPartyFav(party, partyFav);
    }

    @Override
    public  List listFavPartyGroup(Party party) {
        return partyGroupFavDAO.listFavPartyGroup(party);
    }

}
