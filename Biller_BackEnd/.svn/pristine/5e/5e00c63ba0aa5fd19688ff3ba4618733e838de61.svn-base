/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.biller.dto.*;
import com.mycompany.biller.service.DataFieldTypeService;
import com.mycompany.biller.service.InvoiceDetailsService;
import com.mycompany.biller.service.InvoiceService;
import com.mycompany.biller.utils.ResponseMessages;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFFont;

/**
 * @author Admin
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/importFiles")
public class ImportFilesController {

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @Autowired
    private DataFieldTypeService dataFieldTypeService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ResponseMessages> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("partyId") Long partyId, @RequestParam("userLoginId") Long userLoginId) {

        Workbook workBook;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

                // For .xlsx
                if (POIXMLDocument.hasOOXMLHeader(bais)) {
                    workBook = new XSSFWorkbook(bais);
                    invoiceDetailsService.createFromFile(workBook, partyId, userLoginId);
                    return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم الانشاء بنجاح", "has been created successfully", false, null), HttpStatus.OK);

                } else {
                    // For .xls
                    workBook = new HSSFWorkbook(bais);
                    invoiceDetailsService.createFromFile(workBook, partyId, userLoginId);
                    return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم الانشاء بنجاح", "has been created successfully", false, null), HttpStatus.OK);
                }

            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم تتم العمليه بنجاح", "created faild", true, e.getMessage()), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<ResponseMessages>(new ResponseMessages("لم تتم العمليه بنجاح", "created faild", true, "You failed to upload because the file was empty."), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/downloadTempl", method = RequestMethod.GET)
    public @ResponseBody
    void downloadTempl(@RequestParam("partyId") Long partyId, HttpServletResponse response) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);

            Font font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);

            List<DataFieldType> dataFieldTypeList = dataFieldTypeService.findByPartyId(partyId);

            XSSFSheet sheet = workbook.createSheet("Invoice Templ");
            Row rowTitle = sheet.createRow(0);
            Cell cellTitle = null;

            // for Invoice Header
            cellTitle = rowTitle.createCell(0);
            cellTitle.setCellValue((String) "REFERENCE_NUMBER");
            cellTitle.setCellStyle(style);
            sheet.autoSizeColumn(0);
            cellTitle = rowTitle.createCell(1);
            cellTitle.setCellValue((String) "DESCRIPTION");
            cellTitle.setCellStyle(style);
            sheet.autoSizeColumn(1);
            cellTitle = rowTitle.createCell(2);
            cellTitle.setCellValue((String) "INVOICE_DATE");
            cellTitle.setCellStyle(style);
            sheet.autoSizeColumn(2);

            // for Invoice Details
            int cellIndex = 3;
            for (DataFieldType dataFieldType : dataFieldTypeList) {
                cellTitle = rowTitle.createCell(cellIndex);
                cellTitle.setCellValue((String) dataFieldType.getDataFieldTypeKey());
                cellTitle.setCellStyle(style);
                sheet.autoSizeColumn(cellIndex);
                cellIndex++;
            }

            try {
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=InvoiceTempl-" + partyId + ".xlsx");
                OutputStream out = response.getOutputStream();
                workbook.write(out);
            } catch (IOException ex) {
                System.out.println(ex.getStackTrace());
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

}
