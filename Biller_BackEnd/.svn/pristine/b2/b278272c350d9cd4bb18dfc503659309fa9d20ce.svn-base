/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.Category;
import com.mycompany.biller.dto.Items;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ItemsService {

    public Items create(Items items);

    public Items update(Items items);

    public void delete(Long itemsId);

    public List<Items> listAll();

    public Items findById(Long itemsId);

    public List<Items> findByCatId(Long categoryId);

    public List<Items> findByCriteria(Long itemId, String description, String brandName, Category category);
}
