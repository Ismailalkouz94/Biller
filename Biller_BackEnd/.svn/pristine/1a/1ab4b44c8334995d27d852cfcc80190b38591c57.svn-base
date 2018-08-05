/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.UploadBillsDAO;
import com.mycompany.biller.dto.UploadBills;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.UploadBillsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ismail
 */
@Transactional
@Service
public class UploadBillsServiceImpl implements UploadBillsService {

    @Autowired
    private UploadBillsDAO uploadBillsDAO;

    @Override
    public UploadBills addUploadBills(UploadBills uploadBills) {
        uploadBillsDAO.addUploadBills(uploadBills);
        return uploadBills;
    }

    @Override
    public UploadBills updateUploadBills(UploadBills uploadBills) {
        return uploadBillsDAO.updateUploadBills(uploadBills);
    }

    @Override
    public void deleteUploadBills(Long id) {
        UploadBills uploadBills = uploadBillsDAO.findUploadBillsById(id);
        if (uploadBills == null) {
            throw new NotFoundException("Upload Bills Information Not Exist");
        }
        uploadBillsDAO.deleteUploadBills(uploadBills);
    }

    @Override
    public List<UploadBills> listAllUploadBills() {
        return uploadBillsDAO.listAllUploadBills();
    }

    @Override
    public UploadBills findUploadBillsById(Long id) {
        return uploadBillsDAO.findUploadBillsById(id);
    }

    @Override
    public  UploadBills updateUploadBillsStatus(UploadBills uploadBills) {
        return uploadBillsDAO.updateUploadBillsStatus(uploadBills);
    }
}
