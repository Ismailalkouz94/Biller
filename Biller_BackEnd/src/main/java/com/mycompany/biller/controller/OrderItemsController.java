/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.OrderItems;
import com.mycompany.biller.resources.OrderItemsResources;
import com.mycompany.biller.service.OrderItemsService;
import com.mycompany.biller.utils.ResponseMessages;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
@RequestMapping(value = "/orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessages> add(@RequestBody OrderItemsResources orderItemsResources) {
        OrderItems orderItems = orderItemsService.create(orderItemsResources.toOrderItems());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم إضافة المنتج بنجاح", "Item has been created successfully", false, orderItems), HttpStatus.OK);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessages> update(@RequestBody OrderItemsResources orderItemsResources) {

        OrderItems orderItems = orderItemsService.update(orderItemsResources.toOrderItems());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تعديل المنتج بنجاح", "Item has been updated successfully", false, orderItems), HttpStatus.OK);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessages> delete(@RequestParam(value = "id") Long id) {
        orderItemsService.delete(id);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم حذف  المنتج بنجاح", "Item has been deleted successfully", false, null), HttpStatus.OK);

    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> listAll() {
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, orderItemsService.listAll()), HttpStatus.OK);

    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findById(@RequestParam(value = "id") Long id) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, orderItemsService.findById(id)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findByOrderId(@RequestParam(value = "orderId") Long orderId) {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, orderItemsService.findByOrderId(orderId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/listOrders", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderItems> listOrders(@RequestParam(value = "orderId") Long orderId,
            HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
        response.setContentType("application/pdf");

//        String jrxmlFile = "applications\\reports\\accounting\\CostCentersReport.jrxml";
        String jrxmlFile = "E:\\Biller_Master\\Biller\\src\\main\\resources\\report\\testReport.jrxml";

        InputStream input = new FileInputStream(new File(jrxmlFile));
        JasperReport jasperReport = JasperCompileManager.compileReport(input);
//
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "ahmad");

        List<OrderItems> list = orderItemsService.findByOrderId(orderId);
        List<OrderItems> list1 = new ArrayList<OrderItems>();
        HashMap[] reportRows = null;
        reportRows = new HashMap[list.size()];
        HashMap row = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            row = new HashMap();
            row.put("orderItemId", list.get(i).getOrderItemId());
            reportRows[i] = row;
            OrderItems obj = new OrderItems();
            obj.setOrderItemId(orderId);
            list1.add(obj);
        }

        System.out.println("** listOrders **");
        System.out.println(reportRows);
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(reportRows);
//        net.sf.jasperreports.engine.data.JRBeanArrayDataSource
//        params.put("ItemData", ds);
        JRBeanCollectionDataSource ds1 = new JRBeanCollectionDataSource(list);
//        net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
        params.put("ItemData", ds1);
        System.out.println("** " + list.get(0).getItem());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ds);
//        
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return orderItemsService.findByOrderId(orderId);
    }

}
