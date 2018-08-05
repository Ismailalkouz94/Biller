/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.Country;
import java.util.List;

/**
 *
 * @author Rbab3a
 */
public interface CountryDAO {

    public Country create(Country category);

    public Country update(Country country);

    public void delete(Country country);

    public List<Country> listAll();

    public Country findById(Long countryId);

}
