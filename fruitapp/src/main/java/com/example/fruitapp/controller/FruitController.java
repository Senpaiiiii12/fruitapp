package com.example.fruitapp.controller;

import com.example.fruitapp.dto.FruitDTO;
import com.example.fruitapp.entity.Fruit;
import com.example.fruitapp.service.FruitService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fruits")
@CrossOrigin("*")
public class FruitController {

    private final FruitService service;

    public FruitController(FruitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Fruit> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Fruit getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public Fruit create(@Valid @RequestBody FruitDTO dto) {

        Fruit fruit = new Fruit();

        fruit.setName(dto.getName());
        fruit.setQuantity(dto.getQuantity());
        fruit.setPrice(dto.getPrice());
        fruit.setDescription(dto.getDescription());

        return service.create(fruit);
    }

    @PutMapping("/{id}")
    public Fruit update(
            @PathVariable Integer id,
            @Valid @RequestBody FruitDTO dto) {

        Fruit fruit = new Fruit();

        fruit.setName(dto.getName());
        fruit.setQuantity(dto.getQuantity());
        fruit.setPrice(dto.getPrice());
        fruit.setDescription(dto.getDescription());

        return service.update(id, fruit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}