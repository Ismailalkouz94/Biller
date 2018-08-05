package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dao.PartyProfilePrefDAO;
import com.mycompany.biller.dto.PartyProfilePref;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.PartyProfilePrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PartyProfilePrefServiceImpl implements PartyProfilePrefService {

    @Autowired
    private PartyProfilePrefDAO partyProfilePrefDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Override
    public PartyProfilePref create(PartyProfilePref partyProfilePref) {
        if (partyDAO.findById(partyProfilePref.getToParty().getPartyId()) == null || partyDAO.findById(partyProfilePref.getFromParty().getPartyId()) == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        return partyProfilePrefDAO.create(partyProfilePref);
    }

    @Override
    public PartyProfilePref update(PartyProfilePref partyProfilePref) {
        return partyProfilePrefDAO.update(partyProfilePref);
    }

    @Override
    public void delete(Long partyProfilePrefId) {

        PartyProfilePref partyProfilePref = partyProfilePrefDAO.findById(partyProfilePrefId);
        if (partyProfilePref == null) {
            throw new NotFoundException("PartyProfilePref Information Not Exist");
        }
        partyProfilePrefDAO.delete(partyProfilePref);
    }

    @Override
    public List<PartyProfilePref> listAll() {
        return partyProfilePrefDAO.listAll();
    }

    @Override
    public List<PartyProfilePref> listAllByToParty(Long toParty) {
        return partyProfilePrefDAO.listAllByToParty(toParty);
    }

    @Override
    public PartyProfilePref findById(Long id) {
        return partyProfilePrefDAO.findById(id);
    }
}
