/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.DataFieldType;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceDetails;
import com.mycompany.biller.resources.InvoiceDetailsResources;
import com.mycompany.biller.service.DataFieldTypeService;
import com.mycompany.biller.service.InvoiceDetailsService;
import com.mycompany.biller.utils.ResponseMessages;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
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
@RequestMapping(value = "/invoiceDetails",
        produces = "application/json; charset=utf-8")
public class InvoiceDetailsController {

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @Autowired
    private DataFieldTypeService dataFieldTypeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> add(@RequestBody InvoiceDetailsResources invoiceDetailsResources) {
        InvoiceDetails invoiceDet = invoiceDetailsService.create(invoiceDetailsResources.toInvoiceDetails());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم انشاء نوع الحقل بنجاح", "InvoiceDetails has been created successfully", false, invoiceDet), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<ResponseMessages> upadte(@RequestBody InvoiceDetailsResources invoiceDetailsResources) {
        InvoiceDetails invoiceDet = invoiceDetailsService.update(invoiceDetailsResources.toInvoiceDetails());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تعديل نوع الحقل بنجاح", "InvoiceDetails has been updated successfully", false, invoiceDet), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "invoiceDetailsId") Long invoiceDetailsId) {
        invoiceDetailsService.delete(invoiceDetailsId);
        return "OK";
    }

    @RequestMapping(value = "/deleteInvoiceAndDetails", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<ResponseMessages>  deleteInvoiceAndDetails(@RequestParam(value = "invoiceId") Long invoiceId) {
        invoiceDetailsService.deleteInvoiceAndDetails(invoiceId);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم الحذف بنجاح", "Invoice has been deleted successfully", false, null), HttpStatus.OK);
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<InvoiceDetails> listAll() {
        return invoiceDetailsService.listAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findById(@RequestParam(value = "invoiceDetailsId") Long invoiceDetailsId) {
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceDetailsService.findById(invoiceDetailsId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/findByInvoiceId", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findByInvoiceId(@RequestParam(value = "invoiceId") Long invoiceId) {
        if (invoiceId == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", true, ""), HttpStatus.OK);
        }
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceDetailsService.findByInvoiceId(invoiceId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> create(@RequestBody String data) {

        JSONObject jSONObject = new JSONObject(data);
        JSONObject jSONObjectSub = (JSONObject) jSONObject.get("data");

        ArrayList<InvoiceDetails> list = new ArrayList<InvoiceDetails>();
        InvoiceDetails invoiceDetails = new InvoiceDetails();

        String invoiceId = (String) jSONObject.get("invoiceId");
        if (invoiceId.isEmpty()) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("رقم الفاتورة مطلوب", "invoiceId is required", true, ""), HttpStatus.OK);
        }
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(Long.valueOf(invoiceId));

        String partyId = (String) jSONObject.get("partyId");
        if (partyId.isEmpty()) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("رقم المشترك مطلوب", "partyId is required", true, ""), HttpStatus.OK);
        }

        List<DataFieldType> fieldType = dataFieldTypeService.findByPartyId(Long.valueOf(partyId));

        DataFieldType dataFieldType = new DataFieldType();
        for (DataFieldType row : fieldType) {
            dataFieldType = new DataFieldType();
            dataFieldType.setDataFieldTypeId(row.getDataFieldTypeId());

            invoiceDetails = new InvoiceDetails();
            invoiceDetails.setInvoice(invoice);
            invoiceDetails.setDataFieldType(dataFieldType);
            invoiceDetails.setDataFieldTypeValue(jSONObjectSub.getString(row.getDataFieldTypeKey()));

            list.add(invoiceDetails);

//            invoiceDetailsService.create(invoiceDetails);
        }
        System.out.println("list **" + list.size());
        System.out.println(list);
        invoiceDetailsService.createList(list);

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم انشاء نوع الحقل بنجاح", "DataFieldType has been created successfully", false, "13213"), HttpStatus.CREATED);
    }

}
