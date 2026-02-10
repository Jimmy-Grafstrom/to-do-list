package se.jimmy.iths.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groceries")
public class GroceryListController {

    private final GroceryListService groceryListService;

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    @GetMapping
    public String groceries(Model model) {
        model.addAttribute("items", service.getAllGroceries());
        return "groceries";
    }

    @GetMapping("/new")
    public String showCreateForm() {
        return "create-grocery";
    }

    @PostMapping
    public String
}
