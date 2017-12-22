package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="category")
public class CategoryController {

    // This object will be the mechanism with which we interact with objects
    // stored in the database
    // Spring will create a class that implements CategoryDao
    // and put those objects in the categoryDao field when
    // the application starts up
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value="")
    public String index(Model model) {

        //  returns a collection (actually, an Iterable) of all Category objects managed
        // by categoryDao and pass the list into the view by adding it to model
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "My Categories");

        return "category/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("title", "Add a category");
        model.addAttribute(new Category());

        return "category/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Category category, Errors errors) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add a Category");
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";

    }

}
