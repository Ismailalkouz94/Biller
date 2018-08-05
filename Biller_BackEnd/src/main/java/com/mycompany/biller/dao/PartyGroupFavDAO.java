/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyGroup;
import com.mycompany.biller.dto.PartyGroupFav;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ismail
 */
public interface PartyGroupFavDAO {

    public PartyGroupFav create(PartyGroupFav partyGroupFav);

    public PartyGroupFav update(PartyGroupFav partyGroupFav);

    public void delete(PartyGroupFav partyGroupFav);

    public List<PartyGroupFav> listAll();

    public List<PartyGroupFav> listAllFavByParty(Party party);

    public PartyGroupFav findByPartyAndPartyFav(Party party, Party partyFav);

    public  List<Party> listFavPartyGroup(Party party);
}
