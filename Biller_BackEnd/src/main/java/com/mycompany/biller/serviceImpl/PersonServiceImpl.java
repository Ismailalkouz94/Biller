/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.serviceImpl;

import com.mycompany.biller.dao.PartyDAO;
import com.mycompany.biller.dao.PersonDAO;
import com.mycompany.biller.dto.Person;
import com.mycompany.biller.exception.NotFoundException;
import com.mycompany.biller.service.PersonService;
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
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Override
    public void addPerson(Person person) {
        if (partyDAO.findById(person.getParty().getPartyId()) == null) {
            throw new NotFoundException("Party Information Not Exist");
        }
        personDAO.addPerson(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDAO.updatePerson(person);
    }

    @Override
    public void deletePerson(Long id) {
        Person person = personDAO.findById(id);
        if (person == null) {
            throw new NotFoundException("Person Information Not Exist");
        }
        personDAO.deletePerson(person);
    }

    @Override
    public List<Person> listAllPerson() {
        return personDAO.listAllPerson();
    }

    @Override
    public Person findById(Long id) {
        return personDAO.findById(id);
    }

}
