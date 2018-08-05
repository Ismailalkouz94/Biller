/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.UserLogin;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.pushnotifications.PushNotifications;
import com.mycompany.biller.resources.InvoiceResources;
import com.mycompany.biller.service.InvoiceService;
import com.mycompany.biller.service.PartyTemplateRepService;
import com.mycompany.biller.service.UserLoginService;
import com.mycompany.biller.utils.ResponseMessages;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
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
 * @author Rbab3a
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)

    public ResponseEntity<ResponseMessages> add(@RequestBody InvoiceResources invoiceResources) {
        Invoice invoice = invoiceService.create(invoiceResources.toInvoice());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم انشاء الفاتورة بنجاح", "Invoice has been created successfully", false, invoice), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessages> upadte(@RequestBody InvoiceResources invoiceResources) {

        Invoice invoice = invoiceService.update(invoiceResources.toInvoice());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تعديل الفاتورة بنجاح", "Invoice has been updated successfully", false, invoice), HttpStatus.OK);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessages> delete(@RequestParam(value = "id") Long id) {
        invoiceService.delete(id);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم حذف الفاتورة بنجاح", "Invoice has been deleted successfully", false, null), HttpStatus.OK);

    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<ResponseMessages> changeStatus(@RequestParam(value = "invoiceId") Long invoiceId, 
//            @RequestParam(value = "status") String status,
//             @RequestParam(value = "fromParty") String fromParty,
//              @RequestParam(value = "amount") String amount) {
    public ResponseEntity<ResponseMessages> changeStatus(
            @RequestBody String strJson) {
        JSONObject objData = new JSONObject(strJson);

        System.out.println("objData " + objData);
        String status = objData.getString("status");
        Long fromPartyId = objData.getLong("fromPartyId");
        Long toPartyId= objData.getLong("toPartyId");
        String templateId = objData.getString("templateId");
        String title = objData.getString("title");
        Long invoiceId = objData.getLong("invoiceId");
        int result = invoiceService.changeStatus(invoiceId, status);
        System.out.println("FromtParty " + objData.getString("fromPartyName"));
//        String templateId = partyTemplateRepService.findByPartyId(objData.getString("fromParty"));
        if (status.equals("INVOICE_UNPAID")) {

            List<UserLogin> userLogin = userLoginService.findByPartyId(toPartyId);
            ArrayList userLoginList = new ArrayList();
            for (UserLogin row : userLogin) {
                userLoginList.add(row.getUserLoginId());

            }

            StringBuffer message = new StringBuffer();
            message.append("تم اصدار  فاتورة  جديدة من ");
            message.append(objData.getString("fromPartyName"));
            message.append(" ");
            message.append("بقيمة ");
            message.append(objData.getBigDecimal("amount"));
            message.append(" ");
            message.append("دينار اردني");
            PushNotifications pushNotifications = new PushNotifications(message.toString(), title, invoiceId, "invoiceId", fromPartyId, templateId, userLoginList);
            pushNotifications.sendMessage();
        }

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم ارسال  الفاتورة بنجاح", "Invoice has been sent successfully", false, result), HttpStatus.OK);
    }

    @RequestMapping(value = "/changeStatus_Custom", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessages> changeStatusCustom(@RequestParam(value = "invoiceId") Long invoiceId, @RequestParam(value = "status") String status) {
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تمت العمليه بنجاح", "Process has been delivered successfully", false, invoiceService.changeStatus(invoiceId, status)), HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> listAll() {
//        return invoiceService.listAll();
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceService.listAll()), HttpStatus.OK);

    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> findById(@RequestParam(value = "id") Long id) {
//        return invoiceService.findById(id);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceService.findById(id)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByFromPartyAndInvoiceType", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> findByFromPartyAndInvoiceType(@RequestParam(value = "fromPartyId", required = true) Long fromPartyId,
            @RequestParam(value = "invoiceTypeId", required = true) String invoiceTypeId) {
//        return invoiceService.findByFromPartyId(fromPartyId);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceService.findByFromPartyId(fromPartyId, invoiceTypeId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByFromPartyId", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> findByFromPartyId_Mobilty(@RequestParam(value = "fromPartyId", required = true) Long fromPartyId) {
//        return invoiceService.findByFromPartyId(fromPartyId);
        System.out.println("Hello");
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceService.findAllByFromPartyId(fromPartyId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByToPartyId", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> findByToPartyId(@RequestParam(value = "toPartyId") Long toPartyId) {
//        return invoiceService.findByToPartyId(toPartyId);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceService.findByToPartyId(toPartyId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findBySentTo", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> findBySentTo(@RequestParam(value = "fromPartyId") Long fromPartyId) {
//        return invoiceService.findByFromPartyId(fromPartyId);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceService.findBySentTo(fromPartyId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findBySentFrom", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> findBySentFrom(@RequestParam(value = "toPartyId") Long toPartyId) {
//        return invoiceService.findByToPartyId(toPartyId);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceService.findBySentFrom(toPartyId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> findByOrderId(@RequestParam(value = "orderId") Long orderId) {
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceService.findByOrderId(orderId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<Invoice> findByCriteria(
            @RequestParam(value = "fromPartyId", required = true) Long fromPartyId,
            @RequestParam(value = "invoiceTypeId", required = false) String invoiceTypeId,
            @RequestParam(value = "invoiceId", required = false) Long invoiceId,
            @RequestParam(value = "toName", required = false) String toName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "invoiceDate", required = false) String invoiceDate,
            @RequestParam(value = "dueDate", required = false) Date dueDate,
            @RequestParam(value = "toPartyId", required = false) Long toPartyId,
            @RequestParam(value = "paidDate", required = false) Date paidDate,
            @RequestParam(value = "orderId", required = false) Long orderId,
            @RequestParam(value = "paidNumber", required = false) Long paidNumber,
            @RequestParam(value = "statusId", required = false) String statusId) {

        Party toParty = null;
        if (toPartyId != null) {
            toParty = new Party();
            toParty.setPartyId(toPartyId);
        }

        Party fromParty = null;
        if (fromPartyId != null) {
            fromParty = new Party();
            fromParty.setPartyId(fromPartyId);
        }

        GlobalItem invoiceType = null;
        if (invoiceTypeId != null) {
            invoiceType = new GlobalItem();
            invoiceType.setGlobalItemId(invoiceTypeId);
        }
        Orders order = null;
        if (orderId != null) {
            order = new Orders();
            order.setOrderId(orderId);
        }

        GlobalItem status = null;
        if (statusId != null) {
            status = new GlobalItem();
            status.setGlobalItemId(statusId);
        }

        return invoiceService.findByCriteria(fromParty, invoiceType, invoiceId, paidNumber, status, toName, description, invoiceDate, dueDate, paidDate, order, toParty);
    }

}
