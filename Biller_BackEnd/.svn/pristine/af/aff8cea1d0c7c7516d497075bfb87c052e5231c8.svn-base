/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceDetails;
import com.mycompany.biller.dto.InvoiceItem;
import com.mycompany.biller.dto.OrderItems;
import com.mycompany.biller.dto.Orders;
import com.mycompany.biller.dto.PartyTemplateRep;
import com.mycompany.biller.resources.ReportResources;
import com.mycompany.biller.service.InvoiceDetailsService;
import com.mycompany.biller.service.InvoiceService;
import com.mycompany.biller.service.OrdersService;
import com.mycompany.biller.service.PartyTemplateRepService;
import com.mycompany.biller.service.ReportService;
import com.mycompany.biller.utils.ResponseMessages;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Abu Ajram
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @Autowired
    private PartyTemplateRepService partyTemplateRepService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OrdersService ordersService;

    @Override
    public ResponseEntity<ResponseMessages> reportInvoiceDetails(ReportResources reportResources) {

        List<InvoiceDetails> invoiceDetails = invoiceDetailsService.findByInvoiceIdAndFromPartyId(reportResources.getInvoiceId(), reportResources.getPartyId());
        if (invoiceDetails.isEmpty()) {
            invoiceDetails = invoiceDetailsService.findByInvoiceIdAndToPartyId(reportResources.getInvoiceId(), reportResources.getPartyId());
        }
        if (invoiceDetails.isEmpty()) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لا يوجد فاتورة", "Invoice Not Found", true, ""), HttpStatus.OK);
        }

        String invoiceTypeId = invoiceDetails.get(0).getInvoice().getInvoiceTypeId().getGlobalItemId();
        if (!invoiceTypeId.equals("INVOICE_DETAILS")) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("ليس من نوع تفاصيل الفاتورة", "Not Type of Invoice Details", true, ""), HttpStatus.OK);
        }

        Long partyIdFrom = invoiceDetails.get(0).getInvoice().getFromParty().getPartyId();
        PartyTemplateRep partyTemplateRep = partyTemplateRepService.findByTemplateIdAndPartyId(reportResources.getTemplateId(), partyIdFrom);
        if (partyTemplateRep == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }
//        String newBill = partyTemplateRep.getTemplateValue();
        String newBill = partyTemplateRep.getTemplateValueNtv();
        if (newBill == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }

        for (InvoiceDetails row : invoiceDetails) {
            if (row.getDataFieldTypeValue() == null) {
                newBill = newBill.replace(row.getDataFieldType().getDataFieldTypeKey(), "");
            } else {
                newBill = newBill.replace(row.getDataFieldType().getDataFieldTypeKey(), row.getDataFieldTypeValue());
            }
        }
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("عرض الفاتورة بنجاح", "Invoice View successfully", false, newBill), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessages> reportInvoiceItem(ReportResources reportResources) {

        Invoice invoice = invoiceService.findByInvoiceIdAndFromPartyId(reportResources.getInvoiceId(), reportResources.getPartyId());
        if (invoice == null) {
            invoice = invoiceService.findByInvoiceIdAndToPartyId(reportResources.getInvoiceId(), reportResources.getPartyId());
        }
        if (invoice == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لا يوجد فاتورة", "Invoice Not Found", true, ""), HttpStatus.OK);
        }

        String invoiceTypeId = invoice.getInvoiceTypeId().getGlobalItemId();
        if (!invoiceTypeId.equals("INVOICE_ITEM")) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("ليس من نوع العنصر الفاتورة", "Not Type of Invoice Item", true, ""), HttpStatus.OK);
        }

        Long partyIdFrom = invoice.getFromParty().getPartyId();
        PartyTemplateRep partyTemplateRep = partyTemplateRepService.findByTemplateIdAndPartyId(reportResources.getTemplateId(), partyIdFrom);
        if (partyTemplateRep == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }
//        String newBill = partyTemplateRep.getTemplateValue();
        String newBill = partyTemplateRep.getTemplateValueNtv();
        if (newBill == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        String orderId = "    ";
        if (invoice.getOrder() != null) {
            orderId = String.valueOf(invoice.getOrder().getOrderId());
        }

        String referenceNumber = "   ";
        if (invoice.getReferenceNumber() != null) {
            referenceNumber = String.valueOf(invoice.getReferenceNumber());
        }
        String toParty = "    ";
        if (invoice.getToParty() != null) {
            toParty = invoice.getToParty().getPartyGroup().getPartyGroupName();
        } else if (invoice.getToName() != null) {
            toParty = invoice.getToName();
        }
        String status = invoice.getStatus().getDescription();

        String address = "";
        if (invoice.getFromParty().getPartyGroup().getAddress() != null) {
            address = invoice.getFromParty().getPartyGroup().getAddress();
        }
        String pobox = "";
        if (invoice.getFromParty().getPartyGroup().getPobox() != null) {
            pobox = invoice.getFromParty().getPartyGroup().getPobox();
        }
        String phoneNumber = "";
        if (invoice.getFromParty().getPartyGroup().getPhone() != null) {
            phoneNumber = invoice.getFromParty().getPartyGroup().getPhone();
        }

        newBill = newBill.replace("INVOICE_ID", String.valueOf(reportResources.getInvoiceId()));
        newBill = newBill.replace("INVOICE_DATE", String.valueOf(invoice.getInvoiceDate()));
        newBill = newBill.replace("SYS_DATE", String.valueOf(dtf.format(localDate)));
        newBill = newBill.replace("ORDER_ID", orderId);
        newBill = newBill.replace("REFERENCE_NUMBER", referenceNumber);
        newBill = newBill.replace("STATUS_VALUE", status);
        newBill = newBill.replace("FROM_PARTY", invoice.getFromParty().getPartyGroup().getPartyGroupName());
        newBill = newBill.replace("TO_PARTY", toParty);
        newBill = newBill.replace("ADDRESS", address);
        newBill = newBill.replace("POBOX", pobox);
        newBill = newBill.replace("PHONE_NUMBER", phoneNumber);

        Double price, totals = 0.0;
        Long quantity;
        String tbodyFromJava = "";
        List<InvoiceItem> invoiceItems = invoice.getInvoiceItem();

        String brandName = "", description = "";
        if (!invoiceItems.isEmpty()) {
            for (InvoiceItem row : invoiceItems) {
                if (row.getItem() != null) {
                    brandName = row.getItem().getBrandName();
                }
                if (row.getDescription() == null) {
                    return new ResponseEntity<ResponseMessages>(new ResponseMessages(row.getInvoiceItemId() + "لم يتم العثور على الوصف لل عنصر رقم ", "Description of Row Item " + row.getInvoiceItemId() + " Not Found", true, ""), HttpStatus.OK);
                }
                if (row.getQuantity() == null) {
                    return new ResponseEntity<ResponseMessages>(new ResponseMessages(row.getInvoiceItemId() + "لم يتم العثور على كمية لل عنصر رقم ", "Quantity of Row Item " + row.getInvoiceItemId() + " Not Found", true, ""), HttpStatus.OK);
                }
                if (row.getUnitPrice() == null) {
                    return new ResponseEntity<ResponseMessages>(new ResponseMessages(row.getInvoiceItemId() + "لم يتم العثور على سعر لل عنصر رقم ", "UnitPrice of Row Item " + row.getInvoiceItemId() + " Not Found", true, ""), HttpStatus.OK);
                }

                tbodyFromJava = tbodyFromJava + "<tr>";

                price = row.getUnitPrice();
                quantity = row.getQuantity();
                totals = totals + (price * quantity);

                tbodyFromJava = tbodyFromJava + "<td>" + brandName + "</td>";
                tbodyFromJava = tbodyFromJava + "<td width=\"40%\">" + row.getDescription() + "</td>";
                tbodyFromJava = tbodyFromJava + "<td>" + row.getQuantity() + "</td>";
                tbodyFromJava = tbodyFromJava + "<td>" + row.getUnitPrice() + "</td>";
                tbodyFromJava = tbodyFromJava + "<td>" + price * quantity + "</td>";

                tbodyFromJava = tbodyFromJava + "</tr>";
            }
            newBill = newBill.replace("TBODY_FROM_JAVA", tbodyFromJava);
            newBill = newBill.replace("TOTALS", String.valueOf(totals));
        } else {
            newBill = newBill.replace("TBODY_FROM_JAVA", "   ");
            newBill = newBill.replace("TOTALS", "   ");
        }
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("عرض الفاتورة بنجاح", "Invoice View successfully", false, newBill), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessages> reportInvoiceTotal(ReportResources reportResources) {

        Invoice invoice = invoiceService.findByInvoiceIdAndFromPartyId(reportResources.getInvoiceId(), reportResources.getPartyId());
        if (invoice == null) {
            invoice = invoiceService.findByInvoiceIdAndToPartyId(reportResources.getInvoiceId(), reportResources.getPartyId());
            System.out.println("*2* invoice " + invoice);
        }
        if (invoice == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لا يوجد فاتورة", "Invoice Not Found", true, ""), HttpStatus.OK);
        }

        String invoiceTypeId = invoice.getInvoiceTypeId().getGlobalItemId();
        if (!invoiceTypeId.equals("INVOICE_ITEM")) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("ليس من نوع العنصر الفاتورة", "Not Type of Invoice Item", true, ""), HttpStatus.OK);
        }

        Long partyIdFrom = invoice.getFromParty().getPartyId();
        PartyTemplateRep partyTemplateRep = partyTemplateRepService.findByTemplateIdAndPartyId(reportResources.getTemplateId(), partyIdFrom);
        if (partyTemplateRep == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }
//        String newBill = partyTemplateRep.getTemplateValue();
        String newBill = partyTemplateRep.getTemplateValueNtv();
        if (newBill == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        String orderId = "    ";
        if (invoice.getOrder() != null) {
            orderId = String.valueOf(invoice.getOrder().getOrderId());
        }

        String paidNumber = "   ";
        if (invoice.getPaidNumber() != null) {
            paidNumber = String.valueOf(invoice.getPaidNumber());
        }
        String description = "    ";
        if (invoice.getDescription() != null) {
            description = invoice.getDescription();
        }
        String toParty = "    ";
        if (invoice.getToParty() != null) {
            toParty = invoice.getToParty().getPartyGroup().getPartyGroupName();
        } else if (invoice.getToName() != null) {
            toParty = invoice.getToName();
        }
        String status = invoice.getStatus().getDescription();

        newBill = newBill.replace("INVOICE_ID", String.valueOf(reportResources.getInvoiceId()));
        newBill = newBill.replace("INVOICE_DATE", String.valueOf(invoice.getInvoiceDate()));
        newBill = newBill.replace("SYS_DATE", String.valueOf(dtf.format(localDate)));
        newBill = newBill.replace("ORDER_ID", orderId);
        newBill = newBill.replace("PAID_NUMBER", paidNumber);
        newBill = newBill.replace("DESCRIPTION_VALUE", description);
        newBill = newBill.replace("STATUS_VALUE", status);
        newBill = newBill.replace("FROM_PARTY", invoice.getFromParty().getPartyGroup().getPartyGroupName());
        newBill = newBill.replace("TO_PARTY", toParty);

        Double price, totals = 0.0;
        Long quantity;
        List<InvoiceItem> invoiceItems = invoice.getInvoiceItem();

        if (!invoiceItems.isEmpty()) {
            for (InvoiceItem row : invoiceItems) {
                price = row.getUnitPrice();
                quantity = row.getQuantity();
                totals = totals + (price * quantity);
            }
            newBill = newBill.replace("TOTALS", String.valueOf(totals));
        } else {
            newBill = newBill.replace("TOTALS", "   ");
        }
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("عرض الفاتورة بنجاح", "Invoice View successfully", false, newBill), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessages> reportOrderTotal(ReportResources reportResources) {
        Orders orders = ordersService.findByOrderIdAndFromPartyId(reportResources.getOrderId(), reportResources.getPartyId());
        if (orders == null) {
            orders = ordersService.findByOrderIdAndToPartyId(reportResources.getOrderId(), reportResources.getPartyId());
            System.out.println("*2* orders " + orders);
        }
        if (orders == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لا يوجد طلب", "Order Not Found", true, ""), HttpStatus.OK);
        }

        Long partyIdFrom = orders.getFromParty().getPartyId();
        PartyTemplateRep partyTemplateRep = partyTemplateRepService.findByTemplateIdAndPartyId(reportResources.getTemplateId(), partyIdFrom);
        if (partyTemplateRep == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }
//        String newBill = partyTemplateRep.getTemplateValue();
        String newBill = partyTemplateRep.getTemplateValueNtv();
        if (newBill == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        String orderId = String.valueOf(orders.getOrderId());

        String description = "    ";
        if (orders.getDescription() != null) {
            description = orders.getDescription();
        }
        String toParty = orders.getToParty().getPartyGroup().getPartyGroupName();

        String status = orders.getGlobalItem().getDescription();

        newBill = newBill.replace("ORDER_ID", orderId);
        newBill = newBill.replace("ORDER_DATE", String.valueOf(orders.getOrderDate()));
        newBill = newBill.replace("SYS_DATE", String.valueOf(dtf.format(localDate)));
        newBill = newBill.replace("ORDER_NAME", orders.getName());
        newBill = newBill.replace("DESCRIPTION_VALUE", description);
        newBill = newBill.replace("STATUS_VALUE", status);
        newBill = newBill.replace("FROM_PARTY", orders.getFromParty().getPartyGroup().getPartyGroupName());
        newBill = newBill.replace("TO_PARTY", toParty);

        Double price, totals = 0.0;
        Integer quantity;
        List<OrderItems> orderItemses = orders.getOrderItems();

        if (!orderItemses.isEmpty()) {
            for (OrderItems row : orderItemses) {
                price = row.getUnitPrice();
                quantity = row.getQuantity();
                totals = totals + (price * quantity);
            }
            newBill = newBill.replace("TOTALS", String.valueOf(totals));
        } else {
            newBill = newBill.replace("TOTALS", "   ");
        }

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("عرض الفاتورة بنجاح", "Invoice View successfully", false, newBill), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessages> reportOrderItem(ReportResources reportResources) {
        Orders orders = ordersService.findByOrderIdAndFromPartyId(reportResources.getOrderId(), reportResources.getPartyId());
        if (orders == null) {
            orders = ordersService.findByOrderIdAndToPartyId(reportResources.getOrderId(), reportResources.getPartyId());
            System.out.println("*2* orders " + orders);
        }
        if (orders == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لا يوجد طلب", "Order Not Found", true, ""), HttpStatus.OK);
        }

        Long partyIdFrom = orders.getFromParty().getPartyId();
        PartyTemplateRep partyTemplateRep = partyTemplateRepService.findByTemplateIdAndPartyId(reportResources.getTemplateId(), partyIdFrom);
        if (partyTemplateRep == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }
//        String newBill = partyTemplateRep.getTemplateValue();
        String newBill = partyTemplateRep.getTemplateValueNtv();
        if (newBill == null) {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم يتم العثور على نموذج", "Template Not Found", true, ""), HttpStatus.OK);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        String orderId = String.valueOf(orders.getOrderId());

//        String description = "    ";
//        if (orders.getDescription() != null) {
//            description = orders.getDescription();
//        }
        String toParty = orders.getToParty().getPartyGroup().getPartyGroupName();
        String status = orders.getGlobalItem().getDescription();

        String address = "";
        if (orders.getFromParty().getPartyGroup().getAddress() != null) {
            address = orders.getFromParty().getPartyGroup().getAddress();
        }
        String pobox = "";
        if (orders.getFromParty().getPartyGroup().getPobox() != null) {
            pobox = orders.getFromParty().getPartyGroup().getPobox();
        }
        String phoneNumber = "";
        if (orders.getFromParty().getPartyGroup().getPhone() != null) {
            phoneNumber = orders.getFromParty().getPartyGroup().getPhone();
        }

        newBill = newBill.replace("ORDER_ID", orderId);
        newBill = newBill.replace("ORDER_DATE", String.valueOf(orders.getOrderDate()));
        newBill = newBill.replace("SYS_DATE", String.valueOf(dtf.format(localDate)));
        newBill = newBill.replace("ORDER_NAME", orders.getName());
//        newBill = newBill.replace("DESCRIPTION_VALUE", description);
        newBill = newBill.replace("STATUS_VALUE", status);
        newBill = newBill.replace("FROM_PARTY", orders.getFromParty().getPartyGroup().getPartyGroupName());
        newBill = newBill.replace("TO_PARTY", toParty);
        newBill = newBill.replace("ADDRESS", address);
        newBill = newBill.replace("POBOX", pobox);
        newBill = newBill.replace("PHONE_NUMBER", phoneNumber);

        Double price, totals = 0.0;
        Integer quantity;
        String tbodyFromJava = "";
        List<OrderItems> orderItemses = orders.getOrderItems();

        String brandName = "", description = "";
        if (!orderItemses.isEmpty()) {
            for (OrderItems row : orderItemses) {
                if (row.getItem() != null) {
                    brandName = row.getItem().getBrandName();
                }
//                if (row.getDescription() == null) {
//                    if (row.getDescription().isEmpty()) {
//                        return new ResponseEntity<ResponseMessages>(new ResponseMessages(row.getOrderItemId() + "لم يتم العثور على الوصف لل عنصر رقم ", "Description of Row Item " + row.getOrderItemId() + " Not Found", true, ""), HttpStatus.OK);
//                    }
//                }
                if (row.getQuantity() == null) {
                    return new ResponseEntity<ResponseMessages>(new ResponseMessages(row.getOrderItemId() + "لم يتم العثور على كمية لل عنصر رقم ", "Quantity of Row Item " + row.getOrderItemId() + " Not Found", true, ""), HttpStatus.OK);
                }
                if (row.getUnitPrice() == null) {
                    return new ResponseEntity<ResponseMessages>(new ResponseMessages(row.getOrderItemId() + "لم يتم العثور على سعر لل عنصر رقم ", "UnitPrice of Row Item " + row.getOrderItemId() + " Not Found", true, ""), HttpStatus.OK);
                }
                tbodyFromJava = tbodyFromJava + "<tr>";

                price = row.getUnitPrice();
                quantity = row.getQuantity();
                totals = totals + (price * quantity);

                tbodyFromJava = tbodyFromJava + "<td>" + brandName + "</td>";
                tbodyFromJava = tbodyFromJava + "<td width=\"40%\">" + row.getDescription() + "</td>";
                tbodyFromJava = tbodyFromJava + "<td>" + row.getQuantity() + "</td>";
                tbodyFromJava = tbodyFromJava + "<td>" + row.getUnitPrice() + "</td>";
                tbodyFromJava = tbodyFromJava + "<td>" + price * quantity + "</td>";

                tbodyFromJava = tbodyFromJava + "</tr>";
            }
            newBill = newBill.replace("TBODY_FROM_JAVA", tbodyFromJava);
            newBill = newBill.replace("TOTALS", String.valueOf(totals));
        } else {
            newBill = newBill.replace("TOTALS", "   ");
        }
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("عرض الطلب بنجاح", "Order View successfully", false, newBill), HttpStatus.OK);
    }
}
