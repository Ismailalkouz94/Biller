/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.DataFieldType;
import com.mycompany.biller.dto.Party;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface DataFieldTypeDAO {

    public DataFieldType create(DataFieldType dataFieldType);

    public DataFieldType update(DataFieldType dataFieldType);

    public void delete(DataFieldType dataFieldType);

    public List<DataFieldType> listAll();

    public DataFieldType findById(Long dataFieldTypeId);
    
    public List<DataFieldType> findByPartyId(Party party);

}
