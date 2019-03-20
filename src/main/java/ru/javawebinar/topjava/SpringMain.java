package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDateTime;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            SecurityUtil.setLoggedUserId(1);
            MealRestController controller = appCtx.getBean(MealRestController.class);
            //controller.delete(5);
            //controller.get(5);
            controller.update(new Meal(5, LocalDateTime.now(), "description", SecurityUtil.authUserCaloriesPerDay()));

        }
    }
}
