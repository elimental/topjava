package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.impl.MealDAOInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExcess;

@WebServlet(value = "/meals")
public class MealServlet extends HttpServlet {

    private MealDAO mealDAO = MealDAOInMemoryImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        if (parameters.containsKey("update")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Meal meal = mealDAO.getById(id);
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("/updateMealForm.jsp").forward(req, resp);
        } else if (parameters.containsKey("add")) {
            req.getRequestDispatcher("/updateMealForm.jsp").forward(req, resp);
        } else if (parameters.containsKey("remove")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            mealDAO.delete(id);
            showMeals(req, resp);
        } else {
            showMeals(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer mealId = null;
        String stringId = req.getParameter("id");
        if (!"".equals(stringId)) {
            mealId = Integer.parseInt(stringId);
        }
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("date_time"));
        String description = req.getParameter("description");
        Integer calories = Integer.parseInt(req.getParameter("calories"));
        if (mealId == null) {
            mealDAO.add(new Meal(dateTime, description, calories));
        } else {
            mealDAO.update(new Meal(mealId, dateTime, description, calories));
        }
        resp.sendRedirect(req.getContextPath() + "/meals");
    }

    private void showMeals(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealTo> meals = getFilteredWithExcess(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("meals", meals);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
