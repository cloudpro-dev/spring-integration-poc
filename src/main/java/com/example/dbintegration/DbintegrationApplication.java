package com.example.dbintegration;

import com.example.dbintegration.domain.Gender;
import com.example.dbintegration.domain.Item;
import com.example.dbintegration.domain.Person;
import com.example.dbintegration.service.ItemService;
import com.example.dbintegration.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;


import java.util.Calendar;
import java.util.List;

@SpringBootApplication
@ImportResource("classpath:application-context.xml")
public class DbintegrationApplication {

    private static Logger LOGGER = LoggerFactory
            .getLogger(DbintegrationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DbintegrationApplication.class, args);
    }

    // @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            final PersonService personService = ctx.getBean(PersonService.class);
            final ItemService itemService = ctx.getBean(ItemService.class);

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
        };
    }

}
