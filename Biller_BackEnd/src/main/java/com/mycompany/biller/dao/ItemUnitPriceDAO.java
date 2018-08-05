/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.ItemUnitPrice;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Units;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface ItemUnitPriceDAO {

    public ItemUnitPrice create(ItemUnitPrice itemUnitPrice);

    public ItemUnitPrice update(ItemUnitPrice itemUnitPrice);

    public void delete(ItemUnitPrice itemUnitPrice);

    public List<ItemUnitPrice> listAll();

    public ItemUnitPrice findById(Long itemUnitPriceId);

    public ItemUnitPrice findByUnique(Long itemId, Long unitId);

    public List<ItemUnitPrice> findByCriteria(Long itemUnitPriceId, Items items, Units units,Double price);
}
