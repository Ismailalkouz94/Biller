/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyGroup;
import com.mycompany.biller.dto.PartyGroupFav;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface PartyGroupFavService {

    public PartyGroupFav create(PartyGroupFav partyGroupFav);

    public PartyGroupFav update(PartyGroupFav partyGroupFav);

    public void delete(Party party, Party partyFav);

    public List<PartyGroupFav> listAll();

    public List<PartyGroupFav> listAllFavByParty(Party party);

    public PartyGroupFav findByPartyAndPartyFav(Party party, Party partyFav);

    public  List listFavPartyGroup(Party party);

}
