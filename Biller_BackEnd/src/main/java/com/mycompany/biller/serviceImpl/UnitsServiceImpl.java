/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.UnitsDAO;
import com.mycompany.biller.dto.Units;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.UnitsService;
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
public class UnitsServiceImpl implements UnitsService {

    @Autowired
    private UnitsDAO unitsDAO;

    @Override
    public Units create(Units units) {
        return unitsDAO.create(units);
    }

    @Override
    public Units update(Units units) {
        return unitsDAO.update(units);
    }

    @Override
    public void delete(Long unitId) {
        Units units = unitsDAO.findById(unitId);
        if (units == null) {
            throw new NotFoundException("Units Information Not Exist");
        }
        unitsDAO.delete(units);
    }

    @Override
    public List<Units> listAll(Long partyId) {
        return unitsDAO.listAll(partyId);
    }

    @Override
    public Units findById(Long unitId, Long partyId) {
        return unitsDAO.findById(unitId, partyId);
    }

    @Override
    public List<Units> findByCriteria(Long unitId, String name, Long partyId) {
        return unitsDAO.findByCriteria(unitId, name, partyId);
    }

}
