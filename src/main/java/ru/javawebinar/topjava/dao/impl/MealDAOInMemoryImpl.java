package ru.javawebinar.topjava.dao.impl;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAOInMemoryImpl implements MealDAO {

    private static MealDAOInMemoryImpl ourInstance = new MealDAOInMemoryImpl();
    private final ConcurrentMap<Integer, Meal> db;
    private final AtomicInteger id;

    private MealDAOInMemoryImpl() {
        db = new ConcurrentHashMap<>();
        db.put(1, new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        db.put(2, new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        db.put(3, new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        db.put(4, new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        db.put(5, new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        db.put(6, new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        id = new AtomicInteger(6);
    }

    public static MealDAOInMemoryImpl getInstance() {
        return ourInstance;
    }

    @Override
    public Meal add(Meal meal) {
        Integer id = this.id.incrementAndGet();
        meal.setId(id);
        db.put(id, meal);
        return meal;
    }

    @Override
    public Meal getById(Integer id) {
        return db.get(id);
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> meals = new ArrayList<>();
        db.forEach((integer, meal) -> meals.add(meal));
        return meals;
    }

    @Override
    public void delete(Integer id) {
        db.remove(id);
    }

    @Override
    public void update(Meal meal) {
        db.computeIfPresent(meal.getId(), (integer, value) -> meal);
    }
}
