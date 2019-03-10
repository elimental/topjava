package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDAO {

    void add(Meal meal);

    Meal getById(Integer id);

    List<Meal> getAll();

    void delete(Integer id);

    void update(Meal meal);
}
