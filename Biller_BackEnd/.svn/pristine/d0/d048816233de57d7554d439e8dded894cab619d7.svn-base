/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.UserPreferenceDAO;
import com.mycompany.biller.dto.UserPreference;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.UserPreferenceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ismail
 */
@Service
@Transactional
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    private UserPreferenceDAO userPreferenceDAO;

    @Override
    public void add(UserPreference userPreference) {
        if (userPreferenceDAO.findByKey(userPreference.getUserLogin().getUserLoginId(), userPreference.getKey()) != null) {
            userPreferenceDAO.update(userPreference.getUserLogin().getUserLoginId(),userPreference.getKey(),userPreference.getValue());
        } else {
            userPreferenceDAO.add(userPreference);
        }
    }

    @Override
    public void update(UserPreference userPreference) {
        userPreferenceDAO.update(userPreference);
    }

    @Override
    public void delete(Long id) {
        UserPreference userPreference = userPreferenceDAO.findById(id);
        if (userPreference == null) {
            throw new NotFoundException("User Preference Information Not Exist");
        }
        userPreferenceDAO.delete(userPreference);
    }

    @Override
    public List<UserPreference> findByUserLoginId(Long userLoginId) {
        return userPreferenceDAO.findByUserLoginId(userLoginId);
    }

    @Override
    public UserPreference findByKey(Long userLoginId, String key) {
        return userPreferenceDAO.findByKey(userLoginId, key);
    }

    @Override
    public UserPreference findById(Long id) {
        return userPreferenceDAO.findById(id);
    }

}
