/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.PartyCategory;
import com.mycompany.biller.service.PartyCategoryService;
import com.mycompany.biller.utils.ResponseMessages;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rbab3a
 */
@RestController
@RequestMapping(value = "/partyCategory",
        produces = "application/json; charset=utf-8")
public class PartyCategoryController {
    
    @Autowired
    private PartyCategoryService partyCategoryService;
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<ResponseMessages> findAll() {
        
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, partyCategoryService.findAll()), HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<ResponseMessages> findById(@RequestParam(value = "id") Long id) {
        
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, partyCategoryService.findById(id)), HttpStatus.OK);
        
    }
}
