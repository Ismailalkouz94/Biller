/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.resources;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Items;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author DELL
 */
public class ItemsResources extends ResourceSupport {

    private Long itemId;
    private String description;
    private String brandName;
    private Long categoryId;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Items toItems() {
        Category category = new Category();
        category.setCategoryId(categoryId);
        
        Items items = new Items();
        items.setItemId(itemId);
        items.setBrandName(brandName);
        items.setDescription(description);
        items.setCategory(category);

        return items;
    }
}
