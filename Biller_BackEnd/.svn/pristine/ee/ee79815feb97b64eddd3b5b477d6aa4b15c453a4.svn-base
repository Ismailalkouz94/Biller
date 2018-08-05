/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.InvoiceItemDAO;
import com.mycompany.biller.dto.Invoice;
import com.mycompany.biller.dto.InvoiceItem;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.dto.UserLogin;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.InvoiceItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rbab3a
 */
@Service
@Transactional
public class InvoiceItemServiceImpl implements InvoiceItemService {

    @Autowired
    private InvoiceItemDAO invoiceItemDAO;

    @Override
    public InvoiceItem create(InvoiceItem invoiceItem) {
        return invoiceItemDAO.create(invoiceItem);

    }

    @Override
    public InvoiceItem update(InvoiceItem invoiceItem) {
        return invoiceItemDAO.update(invoiceItem);

    }

    @Override
    public void delete(Long invoiceItemId) {
        InvoiceItem invoiceItem = invoiceItemDAO.findById(invoiceItemId);
        if (invoiceItem == null) {
            throw new NotFoundException("invoiceItem information Not Exist");
        }
        invoiceItemDAO.delete(invoiceItem);
    }

    @Override
    public List<InvoiceItem> listAll() {
   return invoiceItemDAO.listAll();
    }

    @Override
    public InvoiceItem findById(Long invoiceItemId) {
        return invoiceItemDAO.findById(invoiceItemId);


    }

    @Override
    public List<InvoiceItem> findByCriteria(Long invoiceItemId, double unitPrice, int quantity, Items itemId, Units unitId, String description, Invoice invoiceId, UserLogin createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InvoiceItem> listInvoiceItems(Long invoiceId) {
return invoiceItemDAO.listInvoiceItems(invoiceId);

    }

}
