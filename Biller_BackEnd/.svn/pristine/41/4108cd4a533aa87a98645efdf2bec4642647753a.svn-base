/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.resources.CategoryResources;
import com.mycompany.biller.resources.CategoryTree;
import com.mycompany.biller.dto.PartySize;
import com.mycompany.biller.service.PartySizeService;
import com.mycompany.biller.utils.ResponseMessages;
import com.mycompany.biller.utils.Utils1;
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
 * @author Abu3ajram
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/partySize",
        produces = "application/json; charset=utf-8")
public class PartySizeController {

    @Autowired
    private PartySizeService partySizeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> add(@RequestParam(value = "description") String description) {
        PartySize partySize = new PartySize();
        partySize.setDescription(description);

        partySize = partySizeService.create(partySize);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم انشاء بنجاح", "PartySize has been created successfully", false, partySize), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<ResponseMessages> upadte(@RequestParam(value = "partySizeId") Long partySizeId,
            @RequestParam(value = "description") String description) {
        PartySize partySize = new PartySize();
        partySize.setPartySizeId(partySizeId);
        partySize.setDescription(description);

        partySize = partySizeService.update(partySize);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تعديل بنجاح", "PartySize has been updated successfully", false, partySize), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<ResponseMessages> delete(@RequestParam(value = "partySizeId") Long partySizeId) {
        partySizeService.delete(partySizeId);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم حذف بنجاح", "PartySize has been deleted successfully", false, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<PartySize> listAll() {
        return partySizeService.listAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public PartySize findById(@RequestParam(value = "partySizeId") Long partySizeId) {
        return partySizeService.findById(partySizeId);
    }

}
