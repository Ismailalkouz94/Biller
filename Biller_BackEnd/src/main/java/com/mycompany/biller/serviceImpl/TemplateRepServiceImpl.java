/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.TemplateRepDAO;
import com.mycompany.biller.dto.TemplateRep;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.TemplateRepService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class TemplateRepServiceImpl implements TemplateRepService {

    @Autowired
    private TemplateRepDAO templateRepDAO;

    @Override
    public TemplateRep create(TemplateRep templateRep) {
        return templateRepDAO.create(templateRep);
    }

    @Override
    public TemplateRep update(TemplateRep templateRep) {
        return templateRepDAO.update(templateRep);
    }

    @Override
    public void delete(String templateId) {
        TemplateRep templateRep = templateRepDAO.findById(templateId);
        if (templateRep == null) {
            throw new NotFoundException("TemplateRep Information Not Exist");
        }
        templateRepDAO.delete(templateRep);
    }

    @Override
    public List<TemplateRep> listAll() {
        return templateRepDAO.listAll();
    }

    @Override
    public TemplateRep findById(String templateId) {
        return templateRepDAO.findById(templateId);
    }

}
