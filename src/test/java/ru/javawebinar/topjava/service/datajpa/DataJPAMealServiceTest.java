package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJPAMealServiceTest extends AbstractMealServiceTest {

    @Test
    public void getWithUser() {
        Meal actualMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        User actualUser = actualMeal.getUser();
        assertMatch(actualMeal, ADMIN_MEAL1);
        UserTestData.assertMatch(actualUser, ADMIN);
    }

    @Test
    public void getWithUserNotFound() {
        thrown.expect(NotFoundException.class);
        service.getWithUser(ADMIN_MEAL_ID, USER_ID);
    }
}
