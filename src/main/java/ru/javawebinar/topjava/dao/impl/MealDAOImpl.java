package ru.javawebinar.topjava.dao.impl;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAOImpl implements MealDAO {

    private static MealDAOImpl ourInstance = new MealDAOImpl();
    private CopyOnWriteArrayList<Meal> db;
    private volatile AtomicInteger id;

    private MealDAOImpl() {
        db = new CopyOnWriteArrayList<>();
        db.add(new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        db.add(new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        db.add(new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        db.add(new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        db.add(new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        db.add(new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        id = new AtomicInteger(6);
    }

    public static MealDAOImpl getInstance() {
        return ourInstance;
    }

    @Override
    public void add(Meal meal) {
        meal.setId(id.incrementAndGet());
        db.add(meal);
    }

    @Override
    public Meal getById(Integer id) {
        Meal result = null;
        for (Meal meal : db) {
            if (meal.getId().equals(id)) {
                result = meal;
            }
        }
        return result;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(db);
    }

    @Override
    public void delete(Integer id) {
        for (Meal meal : db) {
            if (meal.getId().equals(id)) {
                db.remove(meal);
                break;
            }
        }
    }

    @Override
    public void update(Meal meal) {
        boolean updated = false;
        for (Meal mealInDB : db) {
            if (mealInDB.getId().equals(meal.getId())) {
                db.remove(mealInDB);
                db.add(meal);
                updated = true;
                break;
            }
        }
        if (!updated) {
            db.add(meal);
        }
    }
}
