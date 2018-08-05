/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.City;
import java.util.List;

/**
 *
 * @author Rbab3a
 */
public interface CityService {
    public City create(City city);

    public City update(City city);

    public void delete(City city);

    public List<City> listAll();

    public City findById(Long cityId);

    public List<City> findByCountryId(Long countryId);
}
