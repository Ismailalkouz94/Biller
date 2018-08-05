/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.ItemsDAO;
import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Items;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.ItemsService;
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
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDAO itemsDAO;

    @Override
    public Items create(Items items) {
        return itemsDAO.create(items);
    }

    @Override
    public Items update(Items items) {
        return itemsDAO.update(items);
    }

    @Override
    public void delete(Long itemsId) {
        Items items = itemsDAO.findById(itemsId);
        if (items == null) {
            throw new NotFoundException("Items Information Not Exist");
        }
        itemsDAO.delete(items);
    }

    @Override
    public List<Items> listAll() {
        return itemsDAO.listAll();
    }

    @Override
    public Items findById(Long itemsId) {
        return itemsDAO.findById(itemsId);
    }

    @Override
    public List<Items> findByCriteria(Long itemId, String description, String brandName, Category category) {
        return itemsDAO.findByCriteria(itemId, description, brandName, category);
    }

    @Override
    public List<Items> findByCatId(Long categoryId) {
        return itemsDAO.findByCatId(categoryId);
    }

}
