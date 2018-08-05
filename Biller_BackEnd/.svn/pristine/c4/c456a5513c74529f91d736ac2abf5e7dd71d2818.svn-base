/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.InvoiceItem;
import com.mycompany.biller.resources.InvoiceItemResources;
import com.mycompany.biller.service.InvoiceItemService;
import com.mycompany.biller.service.InvoiceService;
import com.mycompany.biller.service.PartyGroupService;
import com.mycompany.biller.utils.ResponseMessages;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
 * @author Rbab3a
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/invoiceItem")
public class InvoiceItemController {

    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private PartyGroupService partyGroupService;

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseMessages> add(@RequestBody InvoiceItemResources invoiceItemResources) {
        InvoiceItem invoiceItem = invoiceItemService.create(invoiceItemResources.toInvoiceItem());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم إضافة المنتج بنجاح", "Item has been created successfully", false, invoiceItem), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessages> upadte(@RequestBody InvoiceItemResources invoiceItemResources) {

        InvoiceItem invoiceItem = invoiceItemService.update(invoiceItemResources.toInvoiceItem());
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم تعديل المنتج بنجاح", "Item has been updated successfully", false, invoiceItem), HttpStatus.OK);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessages> delete(@RequestParam(value = "id") Long id) {
        invoiceItemService.delete(id);
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم حذف  المنتج بنجاح", "Item has been deleted successfully", false, null), HttpStatus.OK);

    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> listAll() {

        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceItemService.listAll()), HttpStatus.OK);

    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceItemService.findById(id)), HttpStatus.OK);

    }

    @RequestMapping(value = "/findByInvoiceId", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseMessages> findByInvoiceId(@RequestParam(value = "invoiceId") Long invoiceId) {
        
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("", "", false, invoiceItemService.listInvoiceItems(invoiceId)), HttpStatus.OK);

    }

    @RequestMapping(value = "/invoiceDetails.pdf", method = RequestMethod.GET)
    @ResponseBody
    public String invoiceDetailsPdf(@RequestParam(value = "invoiceId") Long invoiceId,
            HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        String jrxmlFile = "report/invoiceDetails.jrxml";
//        String jrxmlFile = "E:\\Biller_Master\\Biller\\src\\main\\resources\\report\\invoiceDetails.jrxml";
        InputStream input = null;
        Long partyIdTo = 0L, partyIdFrom = 0L;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
//            input = new FileInputStream(new File(jrxmlFile));
            input = new FileInputStream(classLoader.getResource(jrxmlFile).getFile());

            JasperReport jasperReport = JasperCompileManager.compileReport(input);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", "ahmad");

            List<InvoiceItem> invoiceDetails = invoiceItemService.listInvoiceItems(invoiceId);
            partyIdFrom = invoiceDetails.get(0).getInvoice().getFromParty().getPartyId();
            partyIdTo = invoiceDetails.get(0).getInvoice().getToParty().getPartyId();
            if (partyIdTo != 0 || partyIdTo != null) {
                System.out.println("** partyIdTo **");
                params.put("partyIdToName", partyGroupService.findByPartyId(partyIdTo).getPartyGroupName());
                params.put("partyIdFromName", partyGroupService.findByPartyId(partyIdFrom).getPartyGroupName());
            }
            params.put("partyIdFromName", partyGroupService.findByPartyId(partyIdFrom).getPartyGroupName());
            params.put("partyIdTo", partyIdTo);

//            HashMap[] reportRows = null;
//            reportRows = new HashMap[invoiceDetails.size()];
//            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(reportRows);
            params.put("invoice", invoiceService.findById(invoiceId).getInvoiceId().toString());
            System.out.println("** invoiceId " + invoiceService.findById(invoiceId).getInvoiceId().toString());

            JRBeanCollectionDataSource ds1 = new JRBeanCollectionDataSource(invoiceDetails);

            params.put("invoiceDetails", ds1);
            System.out.println("** ds " + ds1);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ds1);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InvoiceItemController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(InvoiceItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "ok";
    }

}
