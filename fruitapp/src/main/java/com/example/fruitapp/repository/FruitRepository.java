package com.example.fruitapp.repository;

import com.example.fruitapp.entity.Fruit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Integer> {

    List<Fruit> findAllByOrderByIdAsc();

}