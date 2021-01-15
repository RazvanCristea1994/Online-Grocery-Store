package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.data.product.CategoryData;
import store.facade.category.CategoryFacade;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping("/add")
    public String showAdd(Model model){

        model.addAttribute("category", new CategoryData());
        return "category/add-category";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") CategoryData categoryData,
                              BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", errorField.getDefaultMessage()));
            return "category/add-category";
        } else {
            try {
                categoryFacade.save(categoryData);
                model.addAttribute("status", "The category has been added");
                return "redirect:/categories";
            } catch (IllegalArgumentException exception) {
                model.addAttribute("category");
                model.addAttribute("status", "The category cannot be added");
                return "category/add-category";
            }
        }
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

    @PostMapping("/update/{id}")
    public String update(@Validated @ModelAttribute("category") CategoryData categoryData,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", errorField.getDefaultMessage()));
            return "category/update-category";
        } else {
            try {
                categoryFacade.update(categoryData);
                model.addAttribute("status", "The category has been updated");
                return "redirect:/categories";
            } catch (IllegalArgumentException exception) {
                model.addAttribute("category");
                model.addAttribute("status", "The category cannot be updated");
                return "category/update-category";
            }
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {

        try {
            categoryFacade.delete(id);
            model.addAttribute("status", "Category has been deleted");
        } catch (Exception exception){
            model.addAttribute("status", "Category could not be deleted");
        }

        return "redirect:/categories";
    }
}
