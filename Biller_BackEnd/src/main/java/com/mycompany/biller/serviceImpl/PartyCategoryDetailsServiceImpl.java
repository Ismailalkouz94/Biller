/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartyCategoryDetailsDAO;
import com.mycompany.biller.dto.PartyCategoryDetails;
import com.mycompany.biller.service.PartyCategoryDetailsService;
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
public class PartyCategoryDetailsServiceImpl implements PartyCategoryDetailsService{

    @Autowired
    private PartyCategoryDetailsDAO partyCategoryDetailsDAO;
    
    @Override
    public PartyCategoryDetails create(PartyCategoryDetails partyCategoryDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PartyCategoryDetails update(PartyCategoryDetails partyCategoryDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PartyCategoryDetails> findAll() {
return partyCategoryDetailsDAO.findAll();

    }

    @Override
    public PartyCategoryDetails findById(Long catId) {
return partyCategoryDetailsDAO.findById(catId);

    }

    @Override
    public List<PartyCategoryDetails> findByPartyCategroyId(Long partyCategoryId) {
return partyCategoryDetailsDAO.findByPartyCategroyId(partyCategoryId);
    }
    
}
