/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceItem;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.dto.UserLogin;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rbab3a
 */
public interface InvoiceItemDAO {
    public InvoiceItem create(InvoiceItem invoiceItem);

    public InvoiceItem update(InvoiceItem invoiceItem);

    public void delete(InvoiceItem invoiceItem);

    public List<InvoiceItem> listAll();

    public InvoiceItem findById(Long invoiceItemId);

    public List<InvoiceItem> findByCriteria(Long invoiceItemId, double unitPrice, int quantity, Items itemId, Units unitId, String description, Invoice invoiceId, UserLogin createdBy);

      public List<InvoiceItem> listInvoiceItems(Long invoiceId);
    
}
