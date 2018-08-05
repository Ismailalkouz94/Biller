/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.dao;

import com.mycompany.biller.dto.CompanyType;
import java.util.List;

/**
 *
 * @author Rbab3a
 */
public interface CompanyTypeDAO {
    public CompanyType create(CompanyType companyType);

    public CompanyType update(CompanyType companyType);

    public void delete(CompanyType companyType);

    public List<CompanyType> listAll();

    public CompanyType findById(Long id);
}
