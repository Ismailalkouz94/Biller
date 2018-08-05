/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.ItemUnitPriceDAO;
import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.ItemUnitPrice;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.resources.CategoryTree;
import com.mycompany.biller.service.ItemUnitPriceService;
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
public class ItemUnitPriceServiceImpl implements ItemUnitPriceService {

    @Autowired
    private ItemUnitPriceDAO itemUnitPriceDAO;

    @Override
    public ItemUnitPrice create(ItemUnitPrice itemUnitPrice) {
        return itemUnitPriceDAO.create(itemUnitPrice);
    }

    @Override
    public ItemUnitPrice update(ItemUnitPrice itemUnitPrice) {
        return itemUnitPriceDAO.update(itemUnitPrice);
    }

    @Override
    public void delete(Long itemUnitPriceId) {
        ItemUnitPrice itemUnitPrice = itemUnitPriceDAO.findById(itemUnitPriceId);
        if (itemUnitPrice == null) {
            throw new NotFoundException("Item Unit Price Information Not Exist");
        }
        itemUnitPriceDAO.delete(itemUnitPrice);
    }

    @Override
    public List<ItemUnitPrice> listAll() {
        return itemUnitPriceDAO.listAll();
    }

    @Override
    public ItemUnitPrice findById(Long itemUnitPriceId) {
        return itemUnitPriceDAO.findById(itemUnitPriceId);
    }

    public List<ItemUnitPrice> findByCriteria(Long itemUnitPriceId, Items items, Units units, Double price) {
        return itemUnitPriceDAO.findByCriteria(itemUnitPriceId, items, units, price);
    }

    @Override
    public ItemUnitPrice findByUnique(Long itemId, Long unitId) {
        return itemUnitPriceDAO.findByUnique(itemId, unitId);
    }

}
