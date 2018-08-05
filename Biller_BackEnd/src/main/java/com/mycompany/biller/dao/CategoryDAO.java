/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.resources.CategoryTree;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface CategoryDAO {

    public Category create(Category category);

    public Category update(Category category);

    public void delete(Category category);

    public List<Category> listAll();

    public List<Category> findByPartyId(Long partyId);

    public Category findById(Long categoryId);

    public Category findById(Long categoryId, Long partyId);

    public List<CategoryTree> categoryTree(Long partyId);

    public List<Category> findByCriteria(Long categoryId, String description, Long parentCategoryId, Party party);
}
