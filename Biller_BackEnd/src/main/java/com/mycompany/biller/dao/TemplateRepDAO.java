/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.TemplateRep;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface TemplateRepDAO {

    public TemplateRep create(TemplateRep templateRep);

    public TemplateRep update(TemplateRep templateRep);

    public void delete(TemplateRep templateRep);

    public List<TemplateRep> listAll();

    public TemplateRep findById(String templateId);


   
}
