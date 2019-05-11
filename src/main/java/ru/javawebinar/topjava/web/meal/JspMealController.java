package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @Autowired
    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping
    public String meals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping(value = "/create")
    public String showMealCreateForm(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @GetMapping(value = "/update/{id}")
    public String showMealUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("meal", get(id));
        return "mealForm";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteMeal(@PathVariable int id) {
        delete(id);
        return "redirect:/meals";
    }

    @PostMapping
    public String createUpdateMeal(@ModelAttribute Meal meal) {
        if (meal.getId() == null) {
            create(meal);
        } else {
            update(meal, meal.getId());
        }
        return "redirect:meals";
    }

    @PostMapping(value = "/filter")
    public String showFilteredMeal(@RequestParam("startDate") LocalDate startDate,
                                   @RequestParam("endDate") LocalDate endDate,
                                   @RequestParam("startTime") LocalTime  startTime,
                                   @RequestParam("endTime") LocalTime endTime,
                                   Model model) {
        model.addAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }
}
