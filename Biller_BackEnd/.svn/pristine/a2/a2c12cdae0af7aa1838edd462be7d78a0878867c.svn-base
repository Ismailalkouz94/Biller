/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.controller;

import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.pushnotifications.PushNotifications;
import com.mycompany.biller.resources.ToBiller;
import com.mycompany.biller.resources.ToPerson;
import com.mycompany.biller.service.PushNotificationsCenterService;
import com.mycompany.biller.utils.ResponseMessages;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abu3jram
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/pushNotificationsCenter",
        produces = "application/json; charset=utf-8")
public class PushNotificationsCenterController {

    @Autowired
    private PushNotificationsCenterService pushNotificationsCenterService;

    @RequestMapping(value = "/findSubscribers", method = RequestMethod.POST)
    @ResponseBody
    public List<ToBiller> findSubscribers(@RequestBody ToBiller toBiller) {
        String partyId = toBiller.getPartyId() + "";
        if (partyId == null || partyId.isEmpty()) {
            throw new NotFoundException("PartyId Not Found");
        }
        if (toBiller.getCompanyTypeList() == null || toBiller.getCompanyTypeList().isEmpty()) {
            throw new NotFoundException("CompanyTypeList Not Found");
        }
        if (toBiller.getCityIdList() == null || toBiller.getCityIdList().isEmpty()) {
            throw new NotFoundException("CityList Not Found");
        }

        return pushNotificationsCenterService.findSubscribers(toBiller);
    }

    @RequestMapping(value = "/findAllSubscribers", method = RequestMethod.POST)
    @ResponseBody
    public List<ToBiller> findAllSubscribers(@RequestBody ToBiller toBiller) {
        if (toBiller.getCompanyTypeList() == null || toBiller.getCompanyTypeList().isEmpty()) {
            throw new NotFoundException("CompanyTypeList Not Found");
        }
        if (toBiller.getCityIdList() == null || toBiller.getCityIdList().isEmpty()) {
            throw new NotFoundException("CityList Not Found");
        }

        return pushNotificationsCenterService.findAll(toBiller);
    }

    @RequestMapping(value = "/findNonSubscribers", method = RequestMethod.POST)
    @ResponseBody
    public List<ToBiller> findNonSubscribers(@RequestBody ToBiller toBiller) {
        if (toBiller.getCompanyTypeList() == null || toBiller.getCompanyTypeList().isEmpty()) {
            throw new NotFoundException("CompanyTypeList Not Found");
        }
        if (toBiller.getCityIdList() == null || toBiller.getCityIdList().isEmpty()) {
            throw new NotFoundException("CityList Not Found");
        }

        return pushNotificationsCenterService.findNonSubscribers(toBiller);
    }

    @RequestMapping(value = "/findAllPerson", method = RequestMethod.POST)
    @ResponseBody
    public List<ToPerson> findAllPerson(@RequestBody ToPerson toPerson) {
        if (toPerson.getCityIdList() == null || toPerson.getCityIdList().isEmpty()) {
            throw new NotFoundException("CityList Not Found");
        }

        return pushNotificationsCenterService.findAllPerson(toPerson);
    }

    @RequestMapping(value = "/sendNotificationsGroups", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessages> sendNotifications(@RequestParam(value = "userLoginList", required = true) ArrayList userLoginList,
            @RequestParam(value = "message", required = true) String message,
             @RequestParam(value = "partyName", required = true) String partyName) {
//       StringBuffer message = new StringBuffer();
//            message.append("مشتركينا الأعزاء أصبح  الان  بامكانكم  دفع فواتيركم عن طريق");
//            message.append(" تطبيق");
//            message.append("BussinessEdge ");

        PushNotifications pushNotifications = new PushNotifications(message, partyName, userLoginList);
        pushNotifications.sendNotificatins();
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم الإرسال   بنجاح", "Sent successfully", false, null), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/sendNotificationsSingleUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessages> sendNotificationsSingleUser(@RequestParam(value = "userLoginId", required = true) Long userLoginId,
            @RequestParam(value = "message", required = true) String message,
             @RequestParam(value = "partyName", required = true) String partyName) {
ArrayList userLoginList = new ArrayList();
userLoginList.add(userLoginId);
        PushNotifications pushNotifications = new PushNotifications(message, partyName, userLoginList);
        pushNotifications.sendNotificatins();
        return new ResponseEntity<ResponseMessages>(new ResponseMessages("تم الإرسال بنجاح", "Sent successfully", false, null), HttpStatus.OK);
    }
    

}
