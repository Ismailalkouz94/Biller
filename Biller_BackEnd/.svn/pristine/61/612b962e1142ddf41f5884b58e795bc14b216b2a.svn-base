package com.mycompany.biller.controller;

import com.mycompany.biller.dto.PartyProfilePref;
import com.mycompany.biller.resources.PartyProfilePrefResource;
import com.mycompany.biller.service.PartyProfilePrefService;
import com.mycompany.biller.utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/PartyProfilePref")
public class PartyProfilePrefController {

    @Autowired
    private PartyProfilePrefService partyProfilePrefService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessages> add(@RequestBody PartyProfilePrefResource partyProfilePrefResource) {
        PartyProfilePref partyProfilePref = partyProfilePrefService.create(partyProfilePrefResource.toPartyProfilePref());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم الأضافه بنجاح", "created successfully", false, partyProfilePref), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessages> update(@RequestBody PartyProfilePrefResource partyProfilePrefResource) {
        PartyProfilePref partyProfilePref = partyProfilePrefService.update(partyProfilePrefResource.toPartyProfilePref());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم التعديل بنجاح", "updated successfully", false, partyProfilePref), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessages> delete(@RequestParam(value = "id") Long id) {
        partyProfilePrefService.delete(id);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم الحذف بنجاح", "deleted successfully", false, null), HttpStatus.OK);

    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> listAll() {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, partyProfilePrefService.listAll()), HttpStatus.OK);

    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findById(@RequestParam(value = "id") Long id) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, partyProfilePrefService.findById(id)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByToParty", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findByToPartyId(@RequestParam(value = "toPartyId") Long toPartyId) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, partyProfilePrefService.listAllByToParty(toPartyId)), HttpStatus.OK);

    }

}
