package com.example.dbintegration;

import com.example.dbintegration.domain.Gender;
import com.example.dbintegration.domain.Item;
import com.example.dbintegration.domain.Person;
import com.example.dbintegration.service.ItemService;
import com.example.dbintegration.service.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.List;

public class Main {
    private static final Log LOGGER = LogFactory.getLog(Main.class);

    private Main() { }

    /**
     * Load the Spring Integration Application Context
     *
     * @param args - command line arguments
     */
    public static void main(final String... args) {

        final AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:application-context.xml");

        context.registerShutdownHook();

        final PersonService personService = context.getBean(PersonService.class);
        final ItemService itemService = context.getBean(ItemService.class);

        Person person = new Person();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1980, 0, 1);
        person.setDateOfBirth(dateOfBirth.getTime());
        person.setName("Name Of The Person");
        person.setGender(Gender.MALE);
        person = personService.createPerson(person);
        LOGGER.info("\n\tGenerated person with id: " + person.getPersonId() + ", with name: " + person.getName());

        // list people
        final List<Person> personList = personService.findPersonByName("Name Of The Person");
        for(Person p : personList) {
            System.out.print(
                    String.format("Person found - Person Id: '%d', Person Name is: '%s',  Gender: '%s'",
                            p.getPersonId(),p.getName(), p.getGender()));
            System.out.println(String.format(", Date of birth: '%1$td/%1$tm/%1$tC%1$ty'", p.getDateOfBirth()));
        }

        List<Item> items = itemService.getItems();
        System.out.println("Items: " + items);

        Item newItem = new Item("100", "New Item", 0);
        System.out.println("New Item: " + itemService.createItem(newItem));
    }
}
