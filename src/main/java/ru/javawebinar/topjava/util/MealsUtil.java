package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {

    public static List<MealTo> getFilteredWithExcess(List<Meal> meals, LocalTime startTime,
                                                     LocalTime endTime, int caloriesPerDay) {
        Collection<List<Meal>> list = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate)).values();
        return list.stream().flatMap(dayMeals -> {
            boolean excess = dayMeals.stream().mapToInt(Meal::getCalories).sum() > caloriesPerDay;
            return dayMeals.stream().filter(meal ->
                    TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                    .map(meal -> createWithExcess(meal, excess));
        }).collect(toList());
    }

    private static MealTo createWithExcess(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}