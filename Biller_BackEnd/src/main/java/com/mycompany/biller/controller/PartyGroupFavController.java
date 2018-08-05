/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Component;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.PartyGroup;
import com.mycompany.biller.dto.PartyGroupFav;
import com.mycompany.biller.resources.PartyFavResources;
import com.mycompany.biller.service.PartyGroupFavService;
import com.mycompany.biller.utils.ResponseMessages;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ismail
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/partyGroupFav")
public class PartyGroupFavController {

    @Autowired
    private PartyGroupFavService partyGroupFavService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)

    public ResponseEntity<ResponseMessages> add(@RequestBody PartyFavResources partyFavResources) {
        PartyGroupFav partyGroupFav = partyGroupFavService.create(partyFavResources.toPartyGroupFav());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم اضافة الشركة الى قائمتك بنجاح", "The Comapny has been added successfully", false, partyGroupFav), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<PartyGroupFav> update(@RequestParam(value = "partyId") Long partyId,
            @RequestParam(value = "partyFav") Long partyFavId) {

        Party party = new Party();
        party.setPartyId(partyId);

        Party partyFav = new Party();
        partyFav.setPartyId(partyFavId);

        PartyGroupFav partyGroupFav = new PartyGroupFav();
        partyGroupFav.setParty(party);
        partyGroupFav.setPartyFav(partyFav);
        partyGroupFavService.update(partyGroupFav);
        return new ResponseEntity<PartyGroupFav>(partyGroupFav, HttpStatus.OK);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessages> delete(@RequestParam(value = "partyId") Long partyId,
            @RequestParam(value = "partyFavId") Long partyFavId) {
        Party party = new Party();
        party.setPartyId(partyId);
        Party partyFav = new Party();
        partyFav.setPartyId(partyFavId);
        PartyGroupFav partyGroupFav = new PartyGroupFav();
        partyGroupFav.setParty(party);
        partyGroupFav.setPartyFav(partyFav);
        partyGroupFavService.delete(party, partyFav);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم حذف  الشركة بنجاح", "The Company has been deleted successfully", false, null), HttpStatus.OK);

    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<PartyGroupFav> listAll() {
        return partyGroupFavService.listAll();
    }

    @RequestMapping(value = "/listAllFavByParty", method = RequestMethod.GET)
    @ResponseBody
    public List listAllFavByParty(@RequestParam(value = "partyId") Long partyId) {
        Party party = new Party();
        party.setPartyId(partyId);
        return partyGroupFavService.listFavPartyGroup(party);

    }

    @RequestMapping(value = "/findAllFavByParty", method = RequestMethod.GET)
    @ResponseBody
    public List<PartyGroupFav> findAllFavByParty(@RequestParam(value = "partyId") Long partyId) {
        Party party = new Party();
        party.setPartyId(partyId);
        return partyGroupFavService.listAllFavByParty(party);

    }

}
