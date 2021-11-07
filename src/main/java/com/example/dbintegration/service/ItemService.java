package com.example.dbintegration.service;

import com.example.dbintegration.domain.Item;
import com.example.dbintegration.domain.Person;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface ItemService {
    @Payload("''")
    List<Item> getItems();

    Item createItem(Item item);
}
