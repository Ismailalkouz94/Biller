/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.PartyCategory;
import java.util.List;

/**
 *
 * @author Rbab3a
 */
public interface PartyCategoryService {
    
    public PartyCategory create(PartyCategory partyCategory);
    public PartyCategory update(PartyCategory partyCategory);
    public void delete (Long id);
    
    public List<PartyCategory> findAll();
    
       public PartyCategory findById(Long catId);
}
