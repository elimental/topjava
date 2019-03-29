package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static List<Meal> getTestMeals() {
        int startSeq = 100002;
        List<Meal> testMeals = new ArrayList<>();
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 9, 0), "Завтрак", 500));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 13, 0), "Обед", 1000));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 20, 0), "Ужин", 500));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 9, 0), "Завтрак", 501));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 13, 0), "Обед", 1000));
        testMeals.add(new Meal(startSeq++, LocalDateTime.of(2018, Month.MAY, 16, 20, 0), "Ужин", 500));
        return testMeals;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
