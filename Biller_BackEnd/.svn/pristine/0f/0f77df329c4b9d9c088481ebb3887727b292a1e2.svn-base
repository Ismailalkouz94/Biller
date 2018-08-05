/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceDetails;
import com.mycompany.biller.dto.InvoiceItem;
import com.mycompany.biller.dto.OrderItems;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.PartyTemplateRep;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.resources.ReportResources;
import com.mycompany.biller.service.InvoiceDetailsService;
import com.mycompany.biller.service.InvoiceItemService;
import com.mycompany.biller.service.InvoiceService;
import com.mycompany.biller.service.OrderItemsService;
import com.mycompany.biller.service.OrdersService;
import com.mycompany.biller.service.PartyTemplateRepService;
import com.mycompany.biller.service.ReportService;
import com.mycompany.biller.utils.ResponseMessages;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
 *
 * @author Abu Ajram
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/report")
public class ReportController {

    //,
    //        produces = "application/json; charset=utf-8"
    @Autowired
    private PartyTemplateRepService partyTemplateRepService;

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ReportService reportService;

    //, produces = "text/plain;charset=UTF-8"
    @RequestMapping(value = "/reportSwitch", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> reportSwitch(@RequestBody ReportResources reportResources) {

        if (reportResources.getPartyId() == null) {
            throw new NotFoundException("PartyId Not Found");
        }
        if (reportResources.getTemplateId() == null) {
            throw new NotFoundException("TemplateId Not Found");
        }

        switch (reportResources.getTemplateId()) {
            case "CUSTOM":
                if (reportResources.getInvoiceId() == null) {
                    throw new NotFoundException("Can Not Execute Report InvoiceId Not Pass");
                }
                System.out.println("CUSTOM : " + reportResources.getTemplateId());
                return reportService.reportInvoiceDetails(reportResources);
            case "INVOICE_TOTAL":
            case "INVOICE_DETAILS":
                if (reportResources.getInvoiceId() == null) {
                    throw new NotFoundException("Can Not Execute Report InvoiceId Not Pass");
                }
                System.out.println("INVOICE : " + reportResources.getTemplateId());
                return reportService.reportInvoiceItem(reportResources);
            case "ORDER_TOTAL":
            case "ORDER_DETAILS":
            case "DELIVARY_NOTE":
                if (reportResources.getOrderId() == null) {
                    throw new NotFoundException("Can Not Execute Report OrderId Not Pass");
                }
                System.out.println("ORDER : " + reportResources.getTemplateId());
                return reportService.reportOrderItem(reportResources);
        }
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", true, ""), HttpStatus.OK);
    }

    @RequestMapping(value = "/reportDashboard", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> reportDashboard(@RequestParam(value = "partyId") Long partyId) {
        int invoicePaid = 0, invoiceNotPaid = 0, invoiceCancelled = 0, isPartially = 0;
        int ordersBilling = 0, ordersNotBilling = 0, ordersCanceled = 0, ordersCreated = 0;
        Double invoicePaidAmount = 0.0, invoiceNotPaidAmount = 0.0, invoiceCancelledAmount = 0.0, isPartiallyAmount = 0.0;
        Double ordersBillingAmount = 0.0, ordersNotBillingAmount = 0.0, ordersCanceledAmount = 0.0, ordersCreatedAmount = 0.0;

        Map<String, Object> data = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> dataSub = new HashMap<String, Object>();
        List orderNumList = new ArrayList();

        List<Invoice> invoicesList = invoiceService.findAllByFromPartyId(partyId);
        for (Invoice row : invoicesList) {
            List<InvoiceItem> invoiceItems = row.getInvoiceItem();
            if (row.getStatus().getGlobalItemId().equals("INVOICE_PAID")) {
                invoicePaid++;
                for (InvoiceItem rowInvoiceItem : invoiceItems) {
                    invoicePaidAmount = invoicePaidAmount + (rowInvoiceItem.getUnitPrice() * rowInvoiceItem.getQuantity());
                }
            }
            if (row.getStatus().getGlobalItemId().equals("INVOICE_UNPAID") || row.getStatus().getGlobalItemId().equals("INVOICE_CREATED")) {
                invoiceNotPaid++;
                for (InvoiceItem rowInvoiceItem : invoiceItems) {
                    invoiceNotPaidAmount = invoiceNotPaidAmount + (rowInvoiceItem.getUnitPrice() * rowInvoiceItem.getQuantity());
                }
            }
            if (row.getStatus().getGlobalItemId().equals("INVOICE_CANCELLED")) {
                invoiceCancelled++;
                for (InvoiceItem rowInvoiceItem : invoiceItems) {
                    invoiceCancelledAmount = invoiceCancelledAmount + (rowInvoiceItem.getUnitPrice() * rowInvoiceItem.getQuantity());
                }
            }
            if ('Y' == row.getIsPartially()) {
                isPartially++;
                for (InvoiceItem rowInvoiceItem : invoiceItems) {
                    isPartiallyAmount = isPartiallyAmount + (rowInvoiceItem.getUnitPrice() * rowInvoiceItem.getQuantity());
                }
            }
            if (row.getOrder() != null) {
                ordersBilling++;
                orderNumList.add(row.getOrder().getOrderId());
                List<OrderItems> orderItemses = row.getOrder().getOrderItems();
                for (OrderItems rowOrderItems : orderItemses) {
                    ordersBillingAmount = ordersBillingAmount + (rowOrderItems.getUnitPrice() * rowOrderItems.getQuantity());
                }
            }
        }
        data.put("invoicePaid", invoicePaid);
        data.put("invoiceNotPaid", invoiceNotPaid);

        dataSub = new HashMap<String, Object>();
        dataSub.put("name", "الفواتير المدفوعة");//Invoice Paid
        dataSub.put("value", invoicePaidAmount );
        list.add(dataSub);

        dataSub = new HashMap<String, Object>();
        dataSub.put("name", "الفواتير غير مدفوعة");//Invoice Not Paid
        dataSub.put("value", invoiceNotPaidAmount);
        list.add(dataSub);

        dataSub = new HashMap<String, Object>();
        dataSub.put("name", "الفواتير الملغى");//Invoice Cancelled
        dataSub.put("value", invoiceCancelledAmount);
        list.add(dataSub);

        dataSub = new HashMap<String, Object>();
        dataSub.put("name", "الفواتير المدفوعة جزئيا");//Invoice Paid Partially
        dataSub.put("value", isPartiallyAmount);
        list.add(dataSub);

        data.put("invoiceDashboard", list);

        list = new ArrayList<Map<String, Object>>();
        List<Orders> ordersList = ordersService.findByFromPartyId(partyId);
        for (Orders row : ordersList) {
            List<OrderItems> orderItemses = row.getOrderItems();
            if (!orderNumList.contains(row.getOrderId())) {
                ordersNotBilling++;
                for (OrderItems rowOrderItems : orderItemses) {
                    ordersNotBillingAmount = ordersNotBillingAmount + (rowOrderItems.getUnitPrice() * rowOrderItems.getQuantity());
                }
            }
            if (row.getGlobalItem().getGlobalItemId().equals("CANCELED")) {
                ordersCanceled++;
                for (OrderItems rowOrderItems : orderItemses) {
                    ordersCanceledAmount = ordersCanceledAmount + (rowOrderItems.getUnitPrice() * rowOrderItems.getQuantity());
                }
            }
            if (row.getGlobalItem().getGlobalItemId().equals("CREATED")) {
                ordersCreated++;
                for (OrderItems rowOrderItems : orderItemses) {
                    ordersCreatedAmount = ordersCreatedAmount + (rowOrderItems.getUnitPrice() * rowOrderItems.getQuantity());
                }
            }
        }

        data.put("ordersBilling", ordersBilling);
        data.put("ordersNotBilling", ordersNotBilling);

        dataSub = new HashMap<String, Object>();
        dataSub.put("name", "الطلبات المفوترة");//Orders Billing
        dataSub.put("value", ordersBillingAmount);
        list.add(dataSub);

        dataSub = new HashMap<String, Object>();
        dataSub.put("name", "الطلبات الغير مفوترة");//Orders Not Billing
        dataSub.put("value", ordersNotBillingAmount);
        list.add(dataSub);

        dataSub = new HashMap<String, Object>();
        dataSub.put("name", "الطلبات الملغى");//Orders Canceled
        dataSub.put("value", ordersCanceledAmount);
        list.add(dataSub);

        dataSub = new HashMap<String, Object>();
        dataSub.put("name", "الطلبات المنشأة");//Orders Created
        dataSub.put("value", ordersCreatedAmount);
        list.add(dataSub);

        data.put("ordersDashboard", list);

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, data), HttpStatus.OK);
    }
}
