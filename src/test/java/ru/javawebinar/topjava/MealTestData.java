package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final List<Meal> testMeals = new ArrayList<>();
    private static int startSeq = 100002;

    static {
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 9, 0), "Завтрак", 500));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 13, 0), "Обед", 1000));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 20, 0), "Ужин", 500));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 9, 0), "Завтрак", 501));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 13, 0), "Обед", 1000));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 20, 0), "Ужин", 500));
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingOnlyGivenFields(expected, "calories");
    }
}
