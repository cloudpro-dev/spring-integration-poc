package com.example.dbintegration.service;

import com.example.dbintegration.domain.Item;
import com.example.dbintegration.domain.Person;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface PersonService {
    Person createPerson(Person person);
    List<Person> findPersonByName(String name);

    static ParameterizedTypeReference<List<Item>> itemParameterizedTypeReference() {
        return new ParameterizedTypeReference<List<Item>>() {};
    }
}
