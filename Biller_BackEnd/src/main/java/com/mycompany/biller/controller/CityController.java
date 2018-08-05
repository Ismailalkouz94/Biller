/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.City;
import com.mycompany.biller.service.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rbab3a
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/city",
        produces = "application/json; charset=utf-8")
public class CityController {
    
    @Autowired
    private CityService cityService;
    
     @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<City> listAll() {
        return cityService.listAll();
    }

    @RequestMapping(value = "/findByCountryId", method = RequestMethod.GET)
    @ResponseBody
    public List<City> findByPartyId(@RequestParam(value = "countryId") Long countryId) {
        return cityService.findByCountryId(countryId);
    }
}
