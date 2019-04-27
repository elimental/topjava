package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJPAUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getWithMeal() {
        User actualUser = service.getWithMeal(USER_ID);
        List<Meal> actualMeal = actualUser.getMeals();
        assertMatch(actualUser, USER);
        MealTestData.assertMatch(actualMeal, MealTestData.MEALS);
    }

    @Test
    public void getEmptyMealUser() {
        mealService.delete(ADMIN_MEAL_ID, ADMIN_ID);
        mealService.delete(ADMIN_MEAL_ID + 1, ADMIN_ID);
        User actualUser = service.getWithMeal(ADMIN_ID);
        assertMatch(actualUser, ADMIN);
    }

    @Test
    public void getWithMealNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(1);
    }

}
