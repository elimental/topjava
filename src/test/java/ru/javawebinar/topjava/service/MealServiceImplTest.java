package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.topjava.MealTestData.testMeals;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceImplTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(100002, USER_ID);
        Meal expected = testMeals.get(0);
        assertEquals(actual, expected);
    }

    @Test(expected = NotFoundException.class)
    public void getNotOwnMeal() {
        Meal actual = service.get(100002, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(100002, USER_ID);
        List<Meal> actual = service.getAll(USER_ID);
        List<Meal> expected = new ArrayList<>();
        expected.add(testMeals.get(2));
        expected.add(testMeals.get(1));
        assertEquals(actual, expected);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotOwnMeal() {
        service.delete(100002, ADMIN_ID);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> actual = service.getBetweenDateTimes(LocalDateTime.of(2018, Month.MAY, 16, 9, 0),
                LocalDateTime.of(2018, Month.MAY, 16, 13, 0), USER_ID);
        List<Meal> expected = new ArrayList<>();
        expected.add(testMeals.get(1));
        expected.add(testMeals.get(0));
        assertEquals(actual, expected);
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        List<Meal> expected = new ArrayList<>();
        expected.add(testMeals.get(2));
        expected.add(testMeals.get(1));
        expected.add(testMeals.get(0));
        assertEquals(actual, expected);
    }

    @Test
    public void update() {
        Meal mealForUpdate = service.get(100002, USER_ID);
        mealForUpdate.setCalories(502);
        service.update(mealForUpdate, USER_ID);
        Meal actual = service.get(100002, USER_ID);
        Meal expected = testMeals.get(0);
        expected.setCalories(502);
        MealTestData.assertMatch(actual, expected);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotOwnMeal() {
        service.update(service.get(100002, USER_ID), ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.of(2018, Month.MAY, 17, 8, 0), "Завтрак", 500);
        service.create(newMeal, USER_ID);
        Meal actual = service.get(100008, USER_ID);
        Meal expected = newMeal;
        newMeal.setId(100008);
        assertEquals(actual, expected);
    }
}