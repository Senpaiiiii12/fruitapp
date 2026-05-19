package com.example.fruitapp.service;

import com.example.fruitapp.entity.Fruit;
import java.util.List;

public interface FruitService {

    List<Fruit> getAll();

    Fruit getById(Integer id);

    Fruit create(Fruit fruit);

    Fruit update(Integer id, Fruit fruit);

    void delete(Integer id);
}