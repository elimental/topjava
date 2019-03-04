package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetween;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    private static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList,
                                                           LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dayCalories = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            dayCalories.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(),
                    (value, newValue) -> value + newValue);
        }
        List<UserMealWithExceed> userMealsWithExceed = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            LocalDateTime mealDateTime = userMeal.getDateTime();
            if (isBetween(mealDateTime.toLocalTime(), startTime, endTime)
                    && dayCalories.get(mealDateTime.toLocalDate()) > caloriesPerDay) {
                userMealsWithExceed.add(makeExceedMeal(userMeal));
            }
        }
        return userMealsWithExceed;
    }

    // HW0 Optional (Java 8 Sream API)
    private static List<UserMealWithExceed> getFilteredWithExceeded2(List<UserMeal> mealList,
                                                           LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dayCalories = new HashMap<>();
        mealList.forEach(userMeal -> dayCalories.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(),
                (value, newValue) -> value + newValue));
        return mealList.stream()
                .filter(userMeal -> isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .filter(userMeal -> dayCalories.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay)
                .map(UserMealsUtil::makeExceedMeal)
                .collect(Collectors.toList());
    }

    private static UserMealWithExceed makeExceedMeal(UserMeal userMeal) {
        return new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true);
    }
}
