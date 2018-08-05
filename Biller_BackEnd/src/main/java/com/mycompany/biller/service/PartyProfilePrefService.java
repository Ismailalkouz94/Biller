package com.mycompany.biller.service;

import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyProfilePref;

import java.util.List;

public interface PartyProfilePrefService {

    public PartyProfilePref create(PartyProfilePref partyProfilePref);

    public PartyProfilePref update(PartyProfilePref partyProfilePref);

    public void delete(Long partyProfilePrefId);

    public List<PartyProfilePref> listAll();

    public List<PartyProfilePref> listAllByToParty(Long toParty);

    public PartyProfilePref findById(Long id);

}