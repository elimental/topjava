package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal createUpdate(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(Integer mealId, Integer userId) {
        repository.delete(mealId, userId);
    }

    @Override
    public Meal get(Integer mealId, Integer userId) {
        Meal meal = repository.get(mealId, userId);
        if (meal == null) {
            throw new NotFoundException(String.format("Wrong mealId %s for userId %s", mealId, userId));
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Meal> getFilteredByDate(Integer userId, LocalDate fromDate, LocalDate toDate) {
        return repository.getFilteredByDate(userId, fromDate, toDate);
    }
}