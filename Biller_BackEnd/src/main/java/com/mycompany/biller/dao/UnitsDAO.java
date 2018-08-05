/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.Units;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UnitsDAO {

    public Units create(Units units);

    public Units update(Units units);

    public void delete(Units units);

    public List<Units> listAll(Long partyId);

    public Units findById(Long unitId);

    public Units findById(Long unitId, Long partyId);

    public List<Units> findByCriteria(Long unitId, String name, Long partyId);

}
