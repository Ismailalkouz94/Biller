/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.PartySize;
import java.util.List;

/**
 *
 * @author Abu3ajram
 */
public interface PartySizeService {

    public PartySize create(PartySize partySize);

    public PartySize update(PartySize partySize);

    public void delete(Long partySizeId);

    public List<PartySize> listAll();

    public PartySize findById(Long partySizeId);
}
