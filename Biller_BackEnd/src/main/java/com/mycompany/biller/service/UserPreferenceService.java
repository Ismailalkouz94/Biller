/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.dto.UserPreference;
import java.util.List;

/**
 *
 * @author ismail
 */
public interface UserPreferenceService {

    public void add(UserPreference userPreference);

    public void update(UserPreference userPreference);

    public void delete(Long id);

    public List<UserPreference> findByUserLoginId(Long userLoginId);

    public UserPreference findByKey(Long userLoginId, String key);

    public UserPreference findById(Long id);

}
