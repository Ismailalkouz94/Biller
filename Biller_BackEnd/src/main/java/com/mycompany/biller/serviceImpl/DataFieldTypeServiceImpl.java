/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.DataFieldTypeDAO;
import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dto.DataFieldType;
import com.mycompany.biller.dto.Party;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.DataFieldTypeService;
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
public class DataFieldTypeServiceImpl implements DataFieldTypeService {

    @Autowired
    private DataFieldTypeDAO dataFieldTypeDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Override
    public DataFieldType create(DataFieldType dataFieldType) {
        return dataFieldTypeDAO.create(dataFieldType);
    }

    @Override
    public DataFieldType update(DataFieldType dataFieldType) {
        return dataFieldTypeDAO.update(dataFieldType);
    }

    @Override
    public void delete(Long dataFieldTypeId) {
        DataFieldType dataFieldType = dataFieldTypeDAO.findById(dataFieldTypeId);
        if (dataFieldType == null) {
            throw new NotFoundException("DataFieldType Information Not Exist");
        }
        dataFieldTypeDAO.delete(dataFieldType);
    }

    @Override
    public List<DataFieldType> listAll() {
        return dataFieldTypeDAO.listAll();
    }

    @Override
    public DataFieldType findById(Long dataFieldTypeId) {
        return dataFieldTypeDAO.findById(dataFieldTypeId);
    }

    @Override
    public List<DataFieldType> findByPartyId(Long partyId) {
        Party party = partyDAO.findById(partyId);
        if (party == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        return dataFieldTypeDAO.findByPartyId(party);
    }

}
