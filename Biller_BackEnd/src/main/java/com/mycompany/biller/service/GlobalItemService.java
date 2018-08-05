/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.GlobalItem;
import com.mycompany.biller.dto.GlobalType;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface GlobalItemService {

    public void addGlobalItem(GlobalItem globalItem);

    public void updateGlobalItem(GlobalItem globalItem);

    public void deleteGlobalItem(String globalItemId);

    public List<GlobalItem> listAllGlobalItem();

    public GlobalItem findGlobalItemById(String globalItemId);

    public List<GlobalItem> findByCriteria(String globalItemId, String description, String globalItemCode, GlobalType globalType);

}
