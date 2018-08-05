/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.resources.ItemsResources;
import com.mycompany.biller.service.ItemsService;
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
@RequestMapping(value = "/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Items> add(@RequestBody ItemsResources itemsResources) {
        Items items = itemsService.create(itemsResources.toItems());
        return new ResponseEntity<Items>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Items> upadte(@RequestBody ItemsResources itemsResources) {
        Items items = itemsService.update(itemsResources.toItems());
        return new ResponseEntity<Items>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "id") Long id) {
        itemsService.delete(id);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Items> listAll() {
        return itemsService.listAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Items findById(@RequestParam(value = "id") Long id) {
        return itemsService.findById(id);
    }

    @RequestMapping(value = "/findByCatId", method = RequestMethod.GET)
    @ResponseBody
    public List<Items> findByCatId(@RequestParam(value = "catId") Long categoryId) {
        return itemsService.findByCatId(categoryId);
    }

    @RequestMapping(value = "/findByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<Items> findByCriteria(@RequestParam(value = "itemId", required = false) Long itemId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "brandName", required = false) String brandName,
            @RequestParam(value = "categoryId", required = false) Long categoryId) {

        Category category = new Category();
        category.setCategoryId(categoryId);

        return itemsService.findByCriteria(itemId, description, brandName, category);
    }

}
