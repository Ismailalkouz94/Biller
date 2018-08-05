/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.GlobalType;
import com.mycompany.biller.service.GlobalItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "/globalItem")
public class GlobalItemController {

    @Autowired
    private GlobalItemService globalItemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(@RequestParam(value = "globalItemId") String globalItemId,
            @RequestParam(value = "globalItemCode") String globalItemCode,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "globalTypeId") String globalTypeId) {

        GlobalType globalType = new GlobalType();
        globalType.setGlobalTypeId(globalTypeId);

        GlobalItem globalItem = new GlobalItem();
        globalItem.setGlobalItemId(globalItemId);
        globalItem.setGlobalItemCode(globalItemCode);
        globalItem.setDescription(description);
        globalItem.setGlobalType(globalType);
        globalItemService.addGlobalItem(globalItem);
        return "OK";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@RequestParam(value = "globalItemId") String globalItemId,
            @RequestParam(value = "globalItemCode") String globalItemCode,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "globalTypeId") String globalTypeId) {

        GlobalType globalType = new GlobalType();
        globalType.setGlobalTypeId(globalTypeId);

        GlobalItem globalItem = new GlobalItem();
        globalItem.setGlobalItemId(globalItemId);
        globalItem.setGlobalItemCode(globalItemCode);
        globalItem.setDescription(description);
        globalItem.setGlobalType(globalType);
        globalItemService.updateGlobalItem(globalItem);
        return "OK";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@RequestParam(value = "globalItemId") String globalItemId) {
        globalItemService.deleteGlobalItem(globalItemId);
        return "OK";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<GlobalItem> listAll() {
        return globalItemService.listAllGlobalItem();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public GlobalItem findById(@RequestParam(value = "globalItemId") String globalItemId) {
        return globalItemService.findGlobalItemById(globalItemId);

    }

    
     @RequestMapping(value = "/findByCriteria", method = RequestMethod.GET)
    @ResponseBody
    public List<GlobalItem> findByCriteria(@RequestParam(value = "globalItemId", required = false) String globalItemId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "globalItemCode", required = false) String globalItemCode,
            @RequestParam(value = "globalTypeId", required = false) String globalTypeId) {

        GlobalType globalType = new GlobalType();
         System.out.println("globalTypeId "+globalTypeId);
        globalType.setGlobalTypeId(globalTypeId);
return globalItemService.findByCriteria(globalItemId, description, globalItemCode, globalType);
     

    }
}
