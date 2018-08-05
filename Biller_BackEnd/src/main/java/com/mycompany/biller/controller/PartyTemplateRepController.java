/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.PartyTemplateRep;
import com.mycompany.biller.resources.PartyTemplateRepResources;
import com.mycompany.biller.service.PartyTemplateRepService;
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
 * @author Ajarmeh
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/partyTemplateRep",
        produces = "application/json; charset=utf-8")
public class PartyTemplateRepController {

    @Autowired
    private PartyTemplateRepService partyTemplateRepService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> add(@RequestBody PartyTemplateRepResources partyTemplateRepResources) {
        PartyTemplateRep partyTemplate = partyTemplateRepService.create(partyTemplateRepResources.toPartyTemplateRep());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم الأنشاء بنجاح", "PartyTemplateRep has been created successfully", false, partyTemplate), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<ResponseMessages> upadte(@RequestBody PartyTemplateRepResources partyTemplateRepResources) {
        PartyTemplateRep partyTemplate = partyTemplateRepService.update(partyTemplateRepResources.toPartyTemplateRep());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم التعديل بنجاح", "PartyTemplateRep has been updated successfully", false, partyTemplate), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "partyTemplateRepId") Long partyTemplateRepId) {
        partyTemplateRepService.delete(partyTemplateRepId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<PartyTemplateRep> listAll() {
        return partyTemplateRepService.listAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public PartyTemplateRep findById(@RequestParam(value = "partyTemplateRepId") Long partyTemplateRepId) {
        return partyTemplateRepService.findById(partyTemplateRepId);
    }

    @RequestMapping(value = "/findByPartyId", method = RequestMethod.GET)
    @ResponseBody
    public List<PartyTemplateRep> findByPartyId(@RequestParam(value = "partyId") Long partyId) {
        return partyTemplateRepService.findByPartyId(partyId);
    }

    @RequestMapping(value = "/findByPartyIdAndType", method = RequestMethod.GET)
    @ResponseBody
    public List<PartyTemplateRep> findByPartyIdAndType(@RequestParam(value = "partyId") Long partyId,
            @RequestParam(value = "globalItemId") String globalItemId) {
        List<PartyTemplateRep> partyTemplateReps = partyTemplateRepService.findByPartyIdAndType(partyId, globalItemId);
       
        return partyTemplateRepService.findByPartyIdAndType(partyId, globalItemId);
    }

}
