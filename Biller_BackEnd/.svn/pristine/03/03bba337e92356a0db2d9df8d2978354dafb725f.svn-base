/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.PartyGroup;
import com.mycompany.biller.dto.Party;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.biller.service.PartyGroupService;
import com.mycompany.biller.service.PartyService;

/**
 *
 * @author ismail
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/partyGroup")
public class PartyGroupController {

    @Autowired
    private PartyGroupService partyGroupService;

    @Autowired
    private PartyService partyService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(@RequestParam(value = "partyId") Long partyId,
            @RequestParam(value = "partyGroupName") String partyGroupName) {
        Party party = new Party();
        party.setPartyId(partyId);

        PartyGroup partyGroup = new PartyGroup();
//        partyGroup.setPartyGroupCode(partyGroupCode);
        partyGroup.setPartyGroupName(partyGroupName);
        partyGroup.setParty(party);
        partyGroupService.addPartyGroup(partyGroup);
        return "OK";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@RequestParam(value = "partyGroupId") Long partyGroupId,
            @RequestParam(value = "partyGroupName") String partyGroupName,
            @RequestParam(value = "partyId") Long partyId) {

        Party party = new Party();
        party.setPartyId(partyId);

        PartyGroup partyGroup = new PartyGroup();
        partyGroup.setPartyGroupId(partyGroupId);
//        partyGroup.setPartyGroupCode(partyGroupCode);
        partyGroup.setPartyGroupName(partyGroupName);
        partyGroup.setParty(party);

        partyGroupService.updatePartyGroup(partyGroup);
        return "OK";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "partyGroupId") Long partyGroupId) {
        partyGroupService.deletePartyGroup(partyGroupId);
        return "OK";
    }

    @RequestMapping(value = "/listAllPartyGroup", method = RequestMethod.GET)
    @ResponseBody
    public List<PartyGroup> listAllPartyGroup() {

        return partyGroupService.listAllPartyGroup();
    }

    @RequestMapping(value = "/findPartyGroupById", method = RequestMethod.GET)
    @ResponseBody
    public PartyGroup findPartyGroupById(@RequestParam(value = "partyGroupId") Long partyGroupId) {
        return partyGroupService.findPartyGroupById(partyGroupId);

    }
    
    @RequestMapping(value = "/findByPartyId", method = RequestMethod.GET)
    @ResponseBody
    public PartyGroup findByPartyId(@RequestParam(value = "partyId") Long partyId) {
        return partyGroupService.findByPartyId(partyId);

    }

    @RequestMapping(value = "/findByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<PartyGroup> findByCriteria(@RequestParam(value = "partyGroupId", required = false) Long partyGroupId,
            @RequestParam(value = "partyCode", required = false) String partyCode,
            @RequestParam(value = "partyActivity", required = false) String partyActivity,
            @RequestParam(value = "partyGroupName", required = false) String partyGroupName,
            @RequestParam(value = "brandName", required = false) String brandName,
            @RequestParam(value = "category", required = false) String category) {
        return partyGroupService.findByCriteria(partyGroupId, partyCode, partyActivity, partyGroupName, brandName, category);
    }
}
