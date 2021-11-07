package com.example.dbintegration;

import com.example.dbintegration.domain.Gender;
import com.example.dbintegration.domain.Item;
import com.example.dbintegration.domain.Person;
import com.example.dbintegration.service.ItemService;
import com.example.dbintegration.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JdbcMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(JdbcMessageHandler.class);

    private final ItemService itemService;
    private final PersonService personService;

    @Autowired
    public JdbcMessageHandler(ItemService itemService, PersonService personService) {
        this.itemService = itemService;
        this.personService = personService;
    }

    public void handleJdbcMessage(List<Map> message) {
        logger.info("handleJdbcMessage");

        for (Map resultMap: message) {
            logger.info("Row " + resultMap.get("ITEM_ID"));

            // create an Item which matches the API domain
            // TODO read row from Item table directly based on supplied ID from outbox table
            Item item = new Item("200", "Test Item 1", 0);

            // send to the REST service to create the Item
            Item newItem = itemService.createItem(item);
            logger.info("Created new Item " + newItem);

            // transform to a Person object which matches database domain
            Person person = new Person();
            person.setGender(Gender.MALE);
            person.setName(String.valueOf(resultMap.get("DESCRIPTION")));
            person.setDateOfBirth(new Date());

            // save to database
            Person newPerson = personService.createPerson(person);
            logger.info("Created new Person " + newPerson);
        }
    }

}
