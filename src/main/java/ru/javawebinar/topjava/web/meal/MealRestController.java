package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExcess;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        return MealsUtil.getWithExcess(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public void delete(int mealId) {
        service.delete(mealId, authUserId());
    }

    public Meal get(int id) {
        return service.get(id, authUserId());
    }

    public Meal save(Meal meal) {
        return service.createUpdate(meal);
    }

    public List<MealTo> getFiltered(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        return getFilteredWithExcess(service.getFilteredByDate(authUserId(), fromDate, toDate),
                authUserCaloriesPerDay(), fromTime, toTime);
    }
}