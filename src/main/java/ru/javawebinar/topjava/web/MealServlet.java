package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.impl.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExcess;

@WebServlet(value = "/meals")
public class MealServlet extends HttpServlet {

    private MealDAO mealDAO = MealDAOImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Ideally max calories value must be constant or come as parameter */
        List<MealTo> meals = getFilteredWithExcess(mealDAO.getAll(), 2000);
        req.setAttribute("meals", meals);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer mealId = Integer.parseInt(req.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("date_time"));
        String description = req.getParameter("description");
        Integer calories = Integer.parseInt(req.getParameter("calories"));
        mealDAO.update(new Meal(mealId, dateTime, description, calories));
        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}
