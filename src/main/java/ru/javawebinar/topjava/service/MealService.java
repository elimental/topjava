package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealService {

    Meal createUpdate(Meal meal);

    void delete(Integer mealId, Integer userId);

    Meal get(Integer mealId, Integer userId);

    List<Meal> getAll(Integer userId);

    List<Meal> getFilteredByDate(Integer userId, LocalDate fromDate, LocalDate toDate);
}