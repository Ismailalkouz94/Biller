/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.resources.ReportResources;
import com.mycompany.biller.utils.ResponseMessages;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Abu Ajram
 */
public interface ReportService {

    public ResponseEntity<ResponseMessages> reportInvoiceDetails(ReportResources reportResources);

    public ResponseEntity<ResponseMessages> reportInvoiceItem(ReportResources reportResources);

    public ResponseEntity<ResponseMessages> reportInvoiceTotal(ReportResources reportResources);

    public ResponseEntity<ResponseMessages> reportOrderItem(ReportResources reportResources);

    public ResponseEntity<ResponseMessages> reportOrderTotal(ReportResources reportResources);

}
