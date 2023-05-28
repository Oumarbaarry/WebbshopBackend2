package com.example.itemservice.controllers;


import com.example.itemservice.model.Items;
import com.example.itemservice.repository.ItemRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemRepo itemRepo;


    public ItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;

    }
    @GetMapping
    public List<Items> getAllItems() {
        return itemRepo.findAll();
    }

    @GetMapping("/{id}")
    public Items getItemById(@PathVariable Long id) {
        return itemRepo.findById(id).orElseThrow();
    }

    //curl -X POST -H "Content-Type: application/json" -d '{"name": "Canada Goose", "pris":"12000"}' http://localhost:8080/items
    @PostMapping()
    public Items addItem(@RequestBody Items newItem) {
        return itemRepo.save(newItem);
    }
}

