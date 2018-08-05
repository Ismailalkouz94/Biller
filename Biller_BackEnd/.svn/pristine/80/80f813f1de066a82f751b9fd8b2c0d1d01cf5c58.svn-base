/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.ItemUnitPrice;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.resources.CategoryResources;
import com.mycompany.biller.resources.CategoryTree;
import com.mycompany.biller.service.ItemUnitPriceService;
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
@RequestMapping(value = "/itemUnitPrice")
public class ItemUnitPriceController {

    @Autowired
    private ItemUnitPriceService itemUnitPriceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<ItemUnitPrice> add(@RequestParam(value = "itemId") Long itemId,
            @RequestParam(value = "unitId") Long unitId,
            @RequestParam(value = "price") Double price) {
        Items items = new Items();
        items.setItemId(itemId);

        Units units = new Units();
        units.setUnitId(unitId);

        ItemUnitPrice itemUnitPrice = new ItemUnitPrice();
        itemUnitPrice.setItems(items);
        itemUnitPrice.setUnits(units);
        itemUnitPrice.setPrice(price);

        itemUnitPriceService.create(itemUnitPrice);

        return new ResponseEntity<ItemUnitPrice>(itemUnitPrice, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<ItemUnitPrice> upadte(@RequestParam(value = "itemUnitPriceId") Long itemUnitPriceId,
            @RequestParam(value = "itemId") Long itemId,
            @RequestParam(value = "unitId") Long unitId,
            @RequestParam(value = "price") Double price) {
        Items items = new Items();
        items.setItemId(itemId);

        Units units = new Units();
        units.setUnitId(unitId);

        ItemUnitPrice itemUnitPrice = new ItemUnitPrice();
        itemUnitPrice.setItemUnitPriceId(itemUnitPriceId);
        itemUnitPrice.setItems(items);
        itemUnitPrice.setUnits(units);
        itemUnitPrice.setPrice(price);

        itemUnitPriceService.update(itemUnitPrice);
        return new ResponseEntity<ItemUnitPrice>(itemUnitPrice, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "itemUnitPriceId") Long itemUnitPriceId) {
        itemUnitPriceService.delete(itemUnitPriceId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemUnitPrice> listAll() {
        return itemUnitPriceService.listAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ItemUnitPrice findById(@RequestParam(value = "itemUnitPriceId") Long itemUnitPriceId) {
        return itemUnitPriceService.findById(itemUnitPriceId);
    }

    @RequestMapping(value = "/findByUnique", method = RequestMethod.GET)
    @ResponseBody
    public ItemUnitPrice findByUnique(@RequestParam(value = "itemId") Long itemId,
            @RequestParam(value = "unitId") Long unitId) {
        return itemUnitPriceService.findByUnique(itemId, unitId);
    }

    @RequestMapping(value = "/findByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemUnitPrice> findByCriteria(@RequestParam(value = "itemId", required = false) Long itemId,
            @RequestParam(value = "itemUnitPriceId", required = false) Long itemUnitPriceId,
            @RequestParam(value = "unitId", required = false) Long unitId,
            @RequestParam(value = "price", required = false) Double price) {
        Items items = new Items();
        items.setItemId(itemId);

        Units units = new Units();
        units.setUnitId(unitId);
        return itemUnitPriceService.findByCriteria(itemUnitPriceId, items, units, price);
    }

}
