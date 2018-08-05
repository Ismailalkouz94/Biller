/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.TemplateRep;
import com.mycompany.biller.resources.TemplateRepResources;
import com.mycompany.biller.service.TemplateRepService;
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
@RequestMapping(value = "/templateRep",
        produces = "application/json; charset=utf-8")
public class TemplateRepController {

    @Autowired
    private TemplateRepService templateRepService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> add(@RequestBody TemplateRepResources templateRepResources) {
        TemplateRep template = templateRepService.create(templateRepResources.toTemplateRep());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم انشاء نموذج الحقل بنجاح", "TemplateRep has been created successfully", false, template), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<ResponseMessages> upadte(@RequestBody TemplateRepResources templateRepResources) {
        TemplateRep template = templateRepService.update(templateRepResources.toTemplateRep());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تعديل نموذج الحقل بنجاح", "TemplateRep has been updated successfully", false, template), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "templateId") String templateId) {
        templateRepService.delete(templateId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<TemplateRep> listAll() {
        return templateRepService.listAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public TemplateRep findById(@RequestParam(value = "templateId") String templateId) {
        return templateRepService.findById(templateId);
    }

}
