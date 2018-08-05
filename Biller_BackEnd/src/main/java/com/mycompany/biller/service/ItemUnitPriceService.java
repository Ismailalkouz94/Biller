/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.ItemUnitPrice;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.resources.CategoryTree;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface ItemUnitPriceService {

    public ItemUnitPrice create(ItemUnitPrice itemUnitPrice);

    public ItemUnitPrice update(ItemUnitPrice itemUnitPrice);

    public void delete(Long itemUnitPriceId);

    public List<ItemUnitPrice> listAll();

    public ItemUnitPrice findById(Long itemUnitPriceId);

    public ItemUnitPrice findByUnique(Long itemId, Long unitId);

    public List<ItemUnitPrice> findByCriteria(Long itemUnitPriceId, Items items, Units units ,Double price);
}
