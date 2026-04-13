package org.example.ex_03.repository;

import org.example.ex_03.model.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodRepositoryTest {

    @Test
    void saveShouldIncreaseCount() {
        FoodRepository repository = new FoodRepository();
        int before = repository.count();

        Food food = new Food();
        food.setName("Pho bo");
        food.setCategory("Mon chinh");
        food.setPrice(50000.0);

        repository.save(food);

        assertEquals(before + 1, repository.count());
    }
}

