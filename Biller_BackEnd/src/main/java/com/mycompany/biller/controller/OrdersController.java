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
import com.mycompany.biller.resources.OrdersResources;
import com.mycompany.biller.service.OrdersService;
import com.mycompany.biller.utils.ResponseMessages;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @author ismail
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessages> add(@RequestBody OrdersResources ordersResources) {
        Orders orders = ordersService.create(ordersResources.toOrder());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم إضافة الطلب بنجاح", "Order has been created successfully", false, orders), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessages> upadte(@RequestBody OrdersResources ordersResources) {

        Orders orders = ordersService.update(ordersResources.toOrder());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تعديل الطلب بنجاح", "Order has been updated successfully", false, orders), HttpStatus.OK);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessages> delete(@RequestParam(value = "id") Long id) {
        ordersService.delete(id);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم حذف الطلب بنجاح", "Order has been deleted successfully", false, null), HttpStatus.OK);

    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessages> changeStatus(@RequestParam(value = "orderId") Long orderId, @RequestParam(value = "status") String status) {
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تسليم الطلب بنجاح", "Order has been delivered successfully", false, ordersService.changeStatus(orderId, status)), HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> listAll() {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, ordersService.listAll()), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessages> findByPage(@RequestParam(value = "page") int page,@RequestParam(value = "size") int size) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, ordersService.listAll(page,size)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findById(@RequestParam(value = "id") Long id) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, ordersService.findById(id)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByFromPartyId", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findByFromPartyId(@RequestParam(value = "fromPartyId") Long fromPartyId) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, ordersService.findByFromPartyId(fromPartyId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByToPartyId", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findByToPartyId(@RequestParam(value = "toPartyId") Long toPartyId) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, ordersService.findByToPartyId(toPartyId)), HttpStatus.OK);

    }


    @RequestMapping(value = "/findFrom", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findFrom(@RequestParam(value = "toPartyId") Long toPartyId) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, ordersService.findFrom(toPartyId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findTo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findTo(@RequestParam(value = "fromPartyId") Long fromPartyId) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, ordersService.findFrom(fromPartyId)), HttpStatus.OK);

    }


    @RequestMapping(value = "/addOrderInvoice", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessages> add(@RequestParam(value = "orderId") Long orderId) {
        Long invoiceId = ordersService.createOrderInvoice(orderId);
        Map data = new HashMap();
        data.put("invoiceId", invoiceId);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم أنشاء الفاتوره بنجاح", "Invoice has been created successfully", false, data), HttpStatus.OK);
    }


    @RequestMapping(value = "/findByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<Orders> findByCriteria(@RequestParam(value = "orderId", required = false) Long orderId,
                                       @RequestParam(value = "orderCode", required = false) String orderCode,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "description", required = false) String description,
                                       @RequestParam(value = "globalItemId", required = false) String globalItemId,
                                       @RequestParam(value = "orderDateLess", required = false) Date orderDateLess,
                                       @RequestParam(value = "orderDateGreater", required = false) Date orderDateGreater,
                                       @RequestParam(value = "toPartyId", required = false) Long toPartyId,
                                       @RequestParam(value = "fromPartyId", required = false) Long fromPartyId) {

        Party toPartyObj = null;
        if (toPartyId != null) {
            toPartyObj = new Party();
            toPartyObj.setPartyId(toPartyId);
        }
        Party fromPartyObj = null;
        if (fromPartyId != null) {
            fromPartyObj = new Party();
            fromPartyObj.setPartyId(fromPartyId);
        }
        GlobalItem globalItem = null;
        if (globalItemId != null) {
            globalItem = new GlobalItem();
            globalItem.setGlobalItemId(globalItemId);
        }
        return ordersService.findByCriteria(orderId, orderCode, name, description, globalItem, orderDateLess, orderDateGreater, toPartyObj, fromPartyObj);
    }

}
