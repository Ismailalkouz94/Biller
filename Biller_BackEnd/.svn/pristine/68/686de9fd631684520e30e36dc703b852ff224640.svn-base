/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.*;
import com.mycompany.biller.dto.*;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.pushnotifications.PushNotifications;
import com.mycompany.biller.service.DataFieldTypeService;
import com.mycompany.biller.service.InvoiceDetailsService;

import java.util.*;

import com.mycompany.biller.service.InvoiceService;
import com.mycompany.biller.utils.ResponseMessages;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

/**
 * @author Admin
 */
@Service
@Transactional
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {

    @Autowired
    private InvoiceDetailsDAO invoiceDetailsDAO;

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Autowired
    private DataFieldTypeService dataFieldTypeService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @Autowired
    private PartyProfilePrefDAO partyProfilePrefDAO;


    @Override
    public InvoiceDetails create(InvoiceDetails invoiceDetails) {
        return invoiceDetailsDAO.create(invoiceDetails);
    }

    @Override
    public List<InvoiceDetails> createList(List<InvoiceDetails> invoiceDetailsList) {
        List<InvoiceDetails> result = new ArrayList<InvoiceDetails>();
        InvoiceDetails invoiceDetails = new InvoiceDetails();

        Invoice invoice = invoiceDAO.findById(invoiceDetailsList.get(0).getInvoice().getInvoiceId());
        if (invoice == null) {
            throw new NotFoundException("Invoice Information Not Exist");
        }
        for (InvoiceDetails row : invoiceDetailsList) {
            invoiceDetails = invoiceDetailsDAO.create(row);
            result.add(invoiceDetails);
        }
        return result;
    }


    @Override
    public InvoiceDetails update(InvoiceDetails invoiceDetails) {
        return invoiceDetailsDAO.update(invoiceDetails);
    }

    @Override
    public void delete(Long invoiceDetailsId) {
        InvoiceDetails invoiceDetails = invoiceDetailsDAO.findById(invoiceDetailsId);
        if (invoiceDetails == null) {
            throw new NotFoundException("InvoiceDetails Information Not Exist");
        }
        invoiceDetailsDAO.delete(invoiceDetails);
    }

    @Override
    public void deleteInvoiceAndDetails(Long invoiceId) {
        Invoice invoice = invoiceDAO.findById(invoiceId);
        if (invoice == null) {
            throw new NotFoundException("Invoice Information Not Exist");
        }
        invoiceDetailsDAO.deleteByInvoiceId(invoiceId);
        invoiceDAO.delete(invoice);
    }

    @Override
    public List<InvoiceDetails> listAll() {
        return invoiceDetailsDAO.listAll();
    }

    @Override
    public InvoiceDetails findById(Long invoiceDetailsId) {
        return invoiceDetailsDAO.findById(invoiceDetailsId);
    }

    @Override
    public List<InvoiceDetails> findByInvoiceId(Long invoiceId) {
        Invoice invoice = invoiceDAO.findById(invoiceId);
        System.out.println("*" + invoiceId + "*");
        if (invoice == null) {
            throw new NotFoundException("Invoice Information Not Exist");
        }
        return invoiceDetailsDAO.findByInvoiceId(invoice);
    }

    @Override
    public List<InvoiceDetails> findByInvoiceIdAndToPartyId(Long invoiceId, Long toPartyId) {
        Invoice invoice = invoiceDAO.findById(invoiceId);
        Party toParty = partyDAO.findById(toPartyId);
        System.out.println("*" + invoiceId + "*");
        if (invoice == null) {
            throw new NotFoundException("Invoice Information Not Exist");
        }
        if (toParty == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        return invoiceDetailsDAO.findByInvoiceIdAndToPartyId(invoice, toParty);
    }

    @Override
    public List<InvoiceDetails> findByInvoiceIdAndFromPartyId(Long invoiceId, Long fromPartyId) {
        Invoice invoice = invoiceDAO.findById(invoiceId);
        Party fromParty = partyDAO.findById(fromPartyId);
        System.out.println("*" + invoiceId + "*");
        if (invoice == null) {
            throw new NotFoundException("Invoice Information Not Exist");
        }
        if (fromParty == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        return invoiceDetailsDAO.findByInvoiceIdAndFromPartyId(invoice, fromParty);
    }


    // service to create invoice and invoice details from excel sheet
    @Override
    //this annotation to make this method non transactional
//    @Transactional(propagation = NOT_SUPPORTED)
    public void createFromFile(Workbook workBook, Long partyId, Long userLoginId) {
        Row rowObj = null;
        Cell cell = null;
        List<DataFieldType> dataFieldTypeList = dataFieldTypeService.findByPartyId(partyId);

        Party party = partyDAO.findById(partyId);
        if (party == null) {
            throw new NotFoundException("Party Not Found");
        }
        try {
            int intSheetsNumber = workBook.getNumberOfSheets();
            Map<String, Integer> excelColumns = new HashMap<String, Integer>();
            for (int sheetIndex = 0; sheetIndex < intSheetsNumber; sheetIndex++) {
                Sheet sheet = workBook.getSheetAt(sheetIndex);
                int intNumberOfRows = sheet.getPhysicalNumberOfRows();

                //to get columns name from excel and store it in map
                int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
                for (int colCount = 0; colCount < noOfColumns; colCount++) {
                    excelColumns.put(sheet.getRow(0).getCell(colCount).getStringCellValue().trim(), colCount);
                }

                for (int row = 1; row < intNumberOfRows; row++) {
                    rowObj = sheet.getRow(row);

                    //to create invoice header
                    Invoice invoice = new Invoice();

                    // to check total REFERENCE_NUMBER columns(title) is found in excel
                    if (!excelColumns.containsKey("REFERENCE_NUMBER")) {
                        throw new NotFoundException("Reference number Field Not Found In File");
                    }

                    //to check field value is found (Not Blank)
                    if (rowObj.getCell(excelColumns.get("REFERENCE_NUMBER")).getCellType() == Cell.CELL_TYPE_BLANK) {
                        throw new NotFoundException("Reference number Value Not Found In File");
                    }

                    //to check field values are number or string (0 -> numeric , 1 -> string , 2 -> Formula ,  3 -> CELL_TYPE_BLANK)
                    Long referenceNo = null;
                    switch (rowObj.getCell(excelColumns.get("REFERENCE_NUMBER")).getCellType()) {
                        case 0:
                            referenceNo = (long) rowObj.getCell(excelColumns.get("REFERENCE_NUMBER")).getNumericCellValue();
                            break;
                        case 1:
                            referenceNo = Long.parseLong(rowObj.getCell(excelColumns.get("REFERENCE_NUMBER")).getStringCellValue());
                            break;
                        case 2:
                            break;
                    }
                    invoice.setReferenceNumber(referenceNo);

                    // to check  DESCRIPTION columns(title) is found in excel
                    if (!excelColumns.containsKey("DESCRIPTION")) {
                        throw new NotFoundException("Invoice Description Field Not Found In File");
                    }
                    invoice.setDescription(rowObj.getCell(excelColumns.get("DESCRIPTION")).getStringCellValue());

                    // to check TOTAL_AMOUNT amount columns(title) is found in excel
                    if (!excelColumns.containsKey("TOTAL_AMOUNT")) {
                        throw new NotFoundException("Total Amount Field Not Found In File");
                    }

                    // to check total INVOICE_DATE columns(title) is found in excel
                    if (!excelColumns.containsKey("INVOICE_DATE")) {
                        throw new NotFoundException("Invoice Date Field Not Found In File");
                    }

                    //to check field value is found (Not Blank)
                    if (rowObj.getCell(excelColumns.get("INVOICE_DATE")).getCellType() == Cell.CELL_TYPE_BLANK) {
                        throw new NotFoundException("Invoice Date Value Not Found In File");
                    }

                    // to check Date format
                    if (HSSFDateUtil.isCellDateFormatted(rowObj.getCell(excelColumns.get("INVOICE_DATE")))) {
                        if ((rowObj.getCell(excelColumns.get("INVOICE_DATE")).getDateCellValue()) == null) {
                            throw new NotFoundException("Invoice Date Value Not Found In File");
                        }
                        Date invDate = (rowObj.getCell(excelColumns.get("INVOICE_DATE")).getDateCellValue());
                        invoice.setInvoiceDate(invDate);
                    } else {
                        throw new NotFoundException("Invoice Date Invalid");
                    }

                    invoice.setIsPartially('N');
                    invoice.setPaidNumber(referenceNo);

                    invoice.setFromParty(party);

                    GlobalItem invoiceType = new GlobalItem();
                    invoiceType.setGlobalItemId("INVOICE_DETAILS");
                    invoice.setInvoiceTypeId(invoiceType);

                    GlobalItem invoiceStatus = new GlobalItem();
                    invoiceStatus.setGlobalItemId("INVOICE_CREATED");
                    invoice.setStatus(invoiceStatus);

                    UserLogin createdBy = new UserLogin();
                    createdBy.setUserLoginId(userLoginId);
                    invoice.setCreatedBy(createdBy);

                    invoice = invoiceService.create(invoice);

                    //create invoice details
                    for (DataFieldType dataFieldType : dataFieldTypeList) {
                        int cellIndex = 0;

                        cellIndex = excelColumns.get(dataFieldType.getDataFieldTypeKey());
                        InvoiceDetails invoiceDetails = new InvoiceDetails();
                        invoiceDetails.setInvoice(invoice);
                        invoiceDetails.setDataFieldType(dataFieldType);

                        if (excelColumns.containsKey(dataFieldType.getDataFieldTypeKey())) {
                            if (rowObj.getCell(cellIndex).getCellType() == Cell.CELL_TYPE_BLANK) {
                                invoiceDetails.setDataFieldTypeValue(null);
                            } else {
                                //to check field values are number or string (0 -> numeric , 1 -> string)
                                switch (rowObj.getCell(cellIndex).getCellType()) {
                                    case 0:
                                        invoiceDetails.setDataFieldTypeValue(String.valueOf((int) rowObj.getCell(cellIndex).getNumericCellValue()));
                                        break;
                                    case 1:
                                        invoiceDetails.setDataFieldTypeValue(rowObj.getCell(cellIndex).getStringCellValue());
                                        break;
                                    case 2:
                                        break;
                                }
                            }
                        } else {
                            invoiceDetails.setDataFieldTypeValue(null);
                        }
                        invoiceDetailsService.create(invoiceDetails);
                    }

                    //to get userLoginId's by subscriptionNo(referenceNo)
                    ArrayList userLoginList = new ArrayList();
                    List<PartyProfilePref> partyProfilePref = partyProfilePrefDAO.findBySubscriptionNo(invoice.getReferenceNumber());
                    for (PartyProfilePref profilePref : partyProfilePref) {
                        userLoginList.add(profilePref.getUserLoginId().getUserLoginId());
                    }

                    // to send push notification
                    StringBuffer message = new StringBuffer();
                    message.append("تم اصدار فاتوره جديده");
                    message.append(" ");
                    message.append(rowObj.getCell(excelColumns.get("DESCRIPTION")).getStringCellValue());
                    message.append(" ");
                    message.append("من");
                    message.append(" ");
                    message.append(party.getPartyGroup().getPartyGroupName());
                    message.append(" ");
                    message.append("بقيمة ");
                    message.append(rowObj.getCell(excelColumns.get("TOTAL_AMOUNT")).getNumericCellValue());
                    message.append(" ");
                    message.append("دينار اردني");
                    PushNotifications pushNotifications = new PushNotifications(message.toString(), invoice.getDescription(), invoice.getInvoiceId(), "invoiceId", partyId, "CUSTOM", userLoginList);
                    pushNotifications.sendMessage();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw new NotFoundException(e);
        }
    }


}
