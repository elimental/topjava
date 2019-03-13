package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDAO {

    Meal add(Meal meal);

    Meal get(Integer id);

    List<Meal> getAll();

    void delete(Integer id);

    void update(Meal meal);
}
