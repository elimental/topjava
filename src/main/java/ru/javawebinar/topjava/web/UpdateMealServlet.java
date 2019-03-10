package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.impl.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updateMeal")
public class UpdateMealServlet extends HttpServlet {

    private MealDAO mealDAO = MealDAOImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer mealId = Integer.parseInt(req.getParameter("id"));
        Meal meal = mealDAO.getById(mealId);
        req.setAttribute("meal", meal);
        req.getRequestDispatcher("/updateMealForm.jsp").forward(req, resp);
    }
}
