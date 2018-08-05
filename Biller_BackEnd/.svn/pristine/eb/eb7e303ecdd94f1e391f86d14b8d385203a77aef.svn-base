/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.CompanyType;
import com.mycompany.biller.service.CompanyTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rbab3a
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/companyType",
        produces = "application/json; charset=utf-8")
public class CompanyTypeController {
    
    @Autowired
    private CompanyTypeService companyTypeService; 
       @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<CompanyType> listAll() {
        return companyTypeService.listAll();
    }
}
