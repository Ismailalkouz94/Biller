///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.biller.controller;
//
//import com.mycompany.fcmpushnotification.PushNotificationService;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author Admin
// */
//@CrossOrigin
//@RestController
//@RequestMapping(value = "/pushNotification")
//public class PushNotificationController {
//
//    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
//    public @ResponseBody
//    String sendMessage(@RequestParam(value = "title") String title, @RequestParam(value = "message") String message) {
//        FileInputStream serviceAccount = null;
//        try {
//            PushNotificationService pushNotificationService = new PushNotificationService();
//            ClassLoader classLoader = getClass().getClassLoader();
//            serviceAccount = new FileInputStream(classLoader.getResource("fcmfiles/pushtest-232ad-firebase-adminsdk-ujtij-b33273d849.json").getFile());
//            pushNotificationService.sendMessage(serviceAccount, title, message);
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(PushNotificationController.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                serviceAccount.close();
//            } catch (IOException ex) {
//                Logger.getLogger(PushNotificationController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return "OK";
//    }
//
//}
