/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.TemplateRep;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class TemplateRepResources extends ResourceSupport {

    private String templateId;
    private String description;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TemplateRep toTemplateRep() {
        
        TemplateRep templateRep = new TemplateRep();
        templateRep.setTemplateId(templateId);
        templateRep.setDescription(description);
       
        return templateRep;
    }
}
