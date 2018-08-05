/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.resources.CategoryResources;
import com.mycompany.biller.resources.CategoryTree;
import com.mycompany.biller.service.CategoryService;
import com.mycompany.biller.utils.Utils1;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ismail
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/category",
        produces = "application/json; charset=utf-8")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Category> add(@RequestBody CategoryResources categoryResources) {
        Category category = categoryService.create(categoryResources.toCategory());
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Category> upadte(@RequestBody CategoryResources categoryResources) {
        Category category = categoryService.update(categoryResources.toCategory());
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "id") Long id) {
        categoryService.delete(id);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> listAll() {
        return categoryService.listAll();
    }

    @RequestMapping(value = "/findByPartyId", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> findByPartyId(@RequestParam(value = "partyId") Long partyId) {
        return categoryService.findByPartyId(partyId);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Category findById(@RequestParam(value = "id") Long id, @RequestParam(value = "partyId") Long partyId) {
        return categoryService.findById(id, partyId);
    }

    @RequestMapping(value = "/categoryTree", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryTree> categoryTree(@RequestParam(value = "partyId") Long partyId) {
        return categoryService.categoryTree(partyId);
    }

    @RequestMapping(value = "/findByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> findByCriteria(@RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "parentCategoryId", required = false) Long parentCategoryId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "partyId", required = false) Long partyId) {
        Party party = new Party();
        party.setPartyId(partyId);
        return categoryService.findByCriteria(categoryId, description, parentCategoryId, party);
    }

    @RequestMapping(value = "/getCategoryTree", method = RequestMethod.GET)
    @ResponseBody
    public String getCategoryTree(@RequestParam(value = "partyId") Long partyId) {
        List<CategoryTree> categoryList = categoryService.categoryTree(partyId);

        return Utils1.parseTree(categoryList);
    }

    @RequestMapping(value = "/getCategoryDropDown", method = RequestMethod.GET)
    @ResponseBody
    public String getCategoryDropDown(@RequestParam(value = "partyId") Long partyId) {
        List<CategoryTree> categoryList = categoryService.categoryTree(partyId);
        System.out.println("** categoryList **");
        System.out.println(categoryList);
        return Utils1.parseTreeDropDown(categoryList);
    }

}
