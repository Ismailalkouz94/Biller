/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.service;

import com.mycompany.biller.resources.ToBiller;
import com.mycompany.biller.resources.ToPerson;
import java.util.List;

/**
 *
 * @author Abu3jram
 */
public interface PushNotificationsCenterService {

    public List<ToBiller> findSubscribers(ToBiller toBiller);//-- YES     

    public List<ToBiller> findAll(ToBiller toBiller);

    public List<ToBiller> findNonSubscribers(ToBiller toBiller);

    public List<ToPerson> findAllPerson(ToPerson toPerson);
}
