/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.CategoryDAO;
import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.resources.CategoryTree;
import com.mycompany.biller.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Category create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public Category update(Category category) {
        return categoryDAO.update(category);
    }

    @Override
    public void delete(Long categoryId) {
        Category category = categoryDAO.findById(categoryId);
        if (category == null) {
            throw new NotFoundException("Category Information Not Exist");
        }
        categoryDAO.delete(category);
    }

    @Override
    public List<Category> listAll() {
        return categoryDAO.listAll();
    }

    @Override
    public List<Category> findByPartyId(Long partyId) {
        return categoryDAO.findByPartyId(partyId);
    }

    @Override
    public Category findById(Long categoryId, Long partyId) {
        return categoryDAO.findById(categoryId, partyId);
    }

    @Override
    public List<CategoryTree> categoryTree(Long partyId) {
        return categoryDAO.categoryTree(partyId);
    }

    public List<Category> findByCriteria(Long categoryId, String description, Long parentCategoryId, Party party) {
        return categoryDAO.findByCriteria(categoryId, description, parentCategoryId, party);
    }

    

}
