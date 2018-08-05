/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.DataFieldType;
import com.mycompany.biller.resources.DataFieldTypeResources;
import com.mycompany.biller.service.DataFieldTypeService;
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
@RequestMapping(value = "/dataFieldType",
        produces = "application/json; charset=utf-8")
public class DataFieldTypeController {

    @Autowired
    private DataFieldTypeService dataFieldTypeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> add(@RequestBody DataFieldTypeResources dataFieldTypeResources) {
        DataFieldType fieldType = dataFieldTypeService.create(dataFieldTypeResources.toDataFieldType());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم انشاء نوع الحقل بنجاح", "DataFieldType has been created successfully", false, fieldType), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<ResponseMessages> upadte(@RequestBody DataFieldTypeResources dataFieldTypeResources) {
        DataFieldType fieldType = dataFieldTypeService.update(dataFieldTypeResources.toDataFieldType());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تعديل نوع الحقل بنجاح", "DataFieldType has been updated successfully", false, fieldType), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "dataFieldTypeId") Long dataFieldTypeId) {
        dataFieldTypeService.delete(dataFieldTypeId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<DataFieldType> listAll() {
        return dataFieldTypeService.listAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public DataFieldType findById(@RequestParam(value = "dataFieldTypeId") Long dataFieldTypeId) {
        return dataFieldTypeService.findById(dataFieldTypeId);
    }

    @RequestMapping(value = "/findByPartyId", method = RequestMethod.GET)
    @ResponseBody
    public List<DataFieldType> findByPartyId(@RequestParam(value = "partyId") Long partyId) {
        return dataFieldTypeService.findByPartyId(partyId);
    }

}
