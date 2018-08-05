/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Party;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class CategoryResources extends ResourceSupport {

    private Long categoryId;
    private Long parentCategoryId;
    private String description;
    private Long partyId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Category toCategory() {
        Party party = new Party();
        party.setPartyId(partyId);

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setParentCategoryId(parentCategoryId);
        category.setDescription(description);
        category.setParty(party);

        return category;
    }
}
