package com.example.fruitapp.service.impl;

import com.example.fruitapp.entity.Fruit;
import com.example.fruitapp.repository.FruitRepository;
import com.example.fruitapp.service.FruitService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository repo;

    public FruitServiceImpl(FruitRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Fruit> getAll() {
        return repo.findAllByOrderByIdAsc();
    }

    @Override
    public Fruit getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy id: " + id));
    }

    @Override
    public Fruit create(Fruit fruit) {
        return repo.save(fruit);
    }

    @Override
    public Fruit update(Integer id, Fruit fruit) {

        Fruit f = getById(id);

        f.setName(fruit.getName());
        f.setQuantity(fruit.getQuantity());
        f.setPrice(fruit.getPrice());
        f.setDescription(fruit.getDescription());

        return repo.save(f);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}