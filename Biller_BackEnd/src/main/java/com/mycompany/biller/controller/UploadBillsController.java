/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.UploadBills;
import com.mycompany.biller.resources.UploadBillsResources;
import com.mycompany.biller.service.GlobalItemService;
import com.mycompany.biller.service.UploadBillsService;
import java.util.Date;
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
@RequestMapping(value = "/uploadBills")
public class UploadBillsController {

    @Autowired
    private UploadBillsService uploadBillsService;

    @Autowired
    private GlobalItemService globalItemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<UploadBills> add(@RequestBody UploadBillsResources uploadBillsResources) {
        UploadBills uploadBills = uploadBillsService.addUploadBills(uploadBillsResources.toUploadBills());

        return new ResponseEntity<UploadBills>(uploadBills, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    String upadte(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "companyName") String companyName,
            @RequestParam(value = "companyNubmer") Long companyNubmer,
            @RequestParam(value = "minAmount") double minAmount,
            @RequestParam(value = "maxAmount") double maxAmount,
            @RequestParam(value = "partialyPay") char partialyPay,
            @RequestParam(value = "dateOfPost") Date dateOfPost
    ) {
        UploadBills uploadBills = new UploadBills();
        uploadBills.setId(id);
        uploadBills.setCompanyName(companyName);
        uploadBills.setCompanyNubmer(companyNubmer);
        uploadBills.setMaxAmount(maxAmount);
        uploadBills.setMinAmount(minAmount);
        uploadBills.setPartialyPay(partialyPay);
        uploadBills.setDateOfPost(dateOfPost);

        uploadBillsService.updateUploadBills(uploadBills);
        return "OK";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "id") Long id) {
        uploadBillsService.deleteUploadBills(id);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<UploadBills> listAll() {

        return uploadBillsService.listAllUploadBills();

    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public UploadBills findById(@RequestParam(value = "id") Long id) {
        return uploadBillsService.findUploadBillsById(id);

    }

    @RequestMapping(value = "/upadteStatus", method = RequestMethod.POST)
    @ResponseBody
    public UploadBills upadteStatus(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "globalItemId") String globalItemId
    ) {
        GlobalItem globalItem = globalItemService.findGlobalItemById(globalItemId);

        UploadBills uploadBills = (UploadBills) this.findById(id);

        uploadBills.setGlobalItem(globalItem);

        return uploadBillsService.updateUploadBillsStatus(uploadBills);
    }

}
