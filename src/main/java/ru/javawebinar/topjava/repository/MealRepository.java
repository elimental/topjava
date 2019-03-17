package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal);

    boolean delete(Integer mealId, Integer userId);

    Meal get(Integer mealId, Integer userId);

    List<Meal> getAll(Integer userId);

    List<Meal> getFilteredByDate(Integer userId, LocalDate fromDate, LocalDate toDate);
}
