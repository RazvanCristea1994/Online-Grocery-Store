package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.data.product.CategoryData;
import store.facade.category.CategoryFacade;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping("/add")
    public String showAdd(Model model){

        model.addAttribute("categoryData", new CategoryData());
        return "category/add-category";
    }

    @PostMapping("/add")
    public String add(@Valid CategoryData categoryData,
                              BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("status", bindingResult.getFieldError().getField());
            return bindingResult.getFieldError().getField();
        } else {
            try {
                categoryFacade.save(categoryData);
            } catch (IllegalArgumentException exception) {
                model.addAttribute("status", exception.getMessage());
            }
        }

        return "redirect:/categories";
    }

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("categories", categoryFacade.getAll());
        return "category/all-categories";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") Long id, Model model){

        model.addAttribute("category", categoryFacade.getById(id));
        return "category/update-category";
    }

    @PutMapping("/update/{id}")
    public String update(@Validated @ModelAttribute("categoryData") CategoryData categoryData,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("status", bindingResult.getFieldError().getField());
            return "error";
        } else {
            try {
                categoryFacade.update(categoryData);
            } catch (IllegalArgumentException exception) {
                model.addAttribute("status", exception.getMessage());
            }
        }

        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {

        try {
            categoryFacade.delete(id);
            model.addAttribute("id", id);
        } catch (Exception exception){
            model.addAttribute("status", exception.getMessage());
            return "error";
        }

        return "redirect:/categories";
    }
}
