/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartyCategoryDAO;
import com.mycompany.biller.dto.PartyCategory;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.PartyCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rbab3a
 */
@Service
@Transactional
public class PartyCategoryServiceImpl implements PartyCategoryService{

    @Autowired
    private PartyCategoryDAO partyCategoryDAO;
    
    @Override
    public PartyCategory create(PartyCategory partyCategory) {
return partyCategoryDAO.create(partyCategory);

    }

    @Override
    public PartyCategory update(PartyCategory partyCategory) {
return partyCategoryDAO.update(partyCategory);

    }

    @Override
    public void delete(Long id) {
PartyCategory partyCategory = partyCategoryDAO.findById(id);
if(partyCategory ==null){
                throw new NotFoundException("Party Category information Not Exist");

}
partyCategoryDAO.delete(partyCategory);

    }

    @Override
    public List<PartyCategory> findAll() {
return partyCategoryDAO.findAll();

    }

    @Override
    public PartyCategory findById(Long catId) {
return partyCategoryDAO.findById(catId);

    }
    
}
