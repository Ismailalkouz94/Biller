/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.PartyGroup;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface PartyGroupService {

    public void addPartyGroup(PartyGroup partyGroup);

    public PartyGroup createPartyGroup(PartyGroup partyGroup);

    public void updatePartyGroup(PartyGroup partyGroup);

    public void deletePartyGroup(Long partyGroupId);

    public List<PartyGroup> listAllPartyGroup();

    public PartyGroup findPartyGroupById(Long partyGroupId);

    public PartyGroup findByPartyId(Long partyId);

    public List<PartyGroup> findByCriteria(Long partyGroupId, String partyCode, String partyActivity, String partyGroupName, String brandName, String category);

}
