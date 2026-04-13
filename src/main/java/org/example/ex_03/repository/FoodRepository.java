package org.example.ex_03.repository;

import org.example.ex_03.model.Food;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FoodRepository {

    private static final List<Food> foodList = new ArrayList<>();

    public void save(Food food) {
        foodList.add(food);
    }

    public int count() {
        return foodList.size();
    }

    public List<Food> findAll() {
        return foodList;
    }
}

