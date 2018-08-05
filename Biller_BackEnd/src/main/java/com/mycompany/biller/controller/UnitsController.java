/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Units;
import com.mycompany.biller.resources.UnitsResources;
import com.mycompany.biller.service.UnitsService;
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
@RequestMapping(value = "/units")
public class UnitsController {

    @Autowired
    private UnitsService unitsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Units> add(@RequestBody UnitsResources unitsResources) {
        Units units = unitsService.create(unitsResources.toUnits());
        return new ResponseEntity<Units>(units, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Units> upadte(@RequestBody UnitsResources unitsResources) {

        Units units = unitsService.update(unitsResources.toUnits());
        return new ResponseEntity<Units>(units, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "id") Long id) {
        unitsService.delete(id);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Units> listAll(@RequestParam(value = "partyId") Long partyId) {
        return unitsService.listAll(partyId);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Units findById(@RequestParam(value = "id") Long id, @RequestParam(value = "partyId") Long partyId) {
        return unitsService.findById(id, partyId);
    }

    @RequestMapping(value = "/findByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<Units> findByCriteria(
            @RequestParam(value = "unitId", required = false) Long unitId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "partyId", required = false) Long partyId) {

        return unitsService.findByCriteria(unitId, name, partyId);
    }

}
