package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@WebServlet(value = "/meals")
public class MealServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private MealRestController mealController;

    @Override
    public void init() throws ServletException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealController = ctx.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "filter":
                String fromDateParam = request.getParameter("from_date");
                String toDateParam = request.getParameter("to_date");
                String fromTimeParam = request.getParameter("from_time");
                String toTimeParam = request.getParameter("to_time");
                LocalDate fromDate = fromDateParam.isEmpty() ? LocalDate.MIN : LocalDate.parse(fromDateParam);
                LocalDate toDate = toDateParam.isEmpty() ? LocalDate.MAX : LocalDate.parse(toDateParam);
                LocalTime fromTime = fromTimeParam.isEmpty() ? LocalTime.MIN : LocalTime.parse(fromTimeParam);
                LocalTime toTime = toTimeParam.isEmpty() ? LocalTime.MAX : LocalTime.parse(toTimeParam);
                List<MealTo> meals = mealController.getFiltered(fromDate, toDate, fromTime, toTime);
                request.setAttribute("meals", meals);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "all":
                String id = request.getParameter("id");
                Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                        LocalDateTime.parse(request.getParameter("dateTime")),
                        request.getParameter("description"),
                        Integer.parseInt(request.getParameter("calories")), authUserId());
                log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
                mealController.save(meal);
                response.sendRedirect("meals");
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                mealController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, authUserId()) :
                        mealController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals", mealController.getAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
