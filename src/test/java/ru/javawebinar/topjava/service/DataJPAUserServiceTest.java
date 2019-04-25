package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles({"postgres", "datajpa"})
public class DataJPAUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getWithMeal() {
        User actualUser = service.getWithMeal(USER_ID);
        List<Meal> actualMeal = actualUser.getMeals();
        assertMatch(actualUser, USER);
        MealTestData.assertMatch(actualMeal, MealTestData.MEALS);
    }
}
