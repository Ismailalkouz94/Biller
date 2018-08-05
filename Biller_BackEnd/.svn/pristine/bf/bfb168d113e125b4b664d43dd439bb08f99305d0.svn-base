/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartySizeDAO;
import com.mycompany.biller.dto.PartySize;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.PartySizeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Abu3ajram
 */
@Service
@Transactional
public class PartySizeServiceImpl implements PartySizeService {

    @Autowired
    private PartySizeDAO partySizeDAO;

    @Override
    public PartySize create(PartySize partySize) {
        return partySizeDAO.create(partySize);
    }

    @Override
    public PartySize update(PartySize partySize) {
        return partySizeDAO.update(partySize);
    }

    @Override
    public void delete(Long partySizeId) {
        PartySize partySize = partySizeDAO.findById(partySizeId);
        if (partySize == null) {
            throw new NotFoundException("PartySize Information Not Exist");
        }
        partySizeDAO.delete(partySize);
    }

    @Override
    public List<PartySize> listAll() {
        return partySizeDAO.listAll();
    }

    @Override
    public PartySize findById(Long partySizeId) {
        return partySizeDAO.findById(partySizeId);
    }

}
