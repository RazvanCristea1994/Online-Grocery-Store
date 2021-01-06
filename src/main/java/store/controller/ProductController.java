package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.data.product.ProductData;
import store.facade.product.ProductFacade;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @GetMapping("/add")
    public String showAdd(Model model) {

        model.addAttribute("productData", new ProductData());
        return "product/add-product";
    }

    @PostMapping("/add")
    public String add(@Valid ProductData productData, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("status", bindingResult.getFieldError().getField());
            return bindingResult.getFieldError().getField();
        } else {
            try {
                productFacade.save(productData);
            } catch (IllegalArgumentException exception) {
                model.addAttribute("status", exception.getMessage());
            }
        }

        return "redirect:/products";
    }

    @GetMapping
    public String getAll(Model model){

        model.addAttribute("products", productFacade.getAll());
        return "product/all-products";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") Long id, Model model){

        model.addAttribute("product", productFacade.getById(id));
        return "product/update-product";
    }

    @PutMapping("/update/{id}")
    public String update(@Validated @ModelAttribute("product") ProductData productData,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("status", bindingResult.getFieldError().getField());
            return "error";
        } else {
            try {
                productFacade.update(productData);
            } catch (IllegalArgumentException exception) {
                model.addAttribute("status", exception.getMessage());
            }
        }

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {

        try {
            productFacade.delete(id);
            model.addAttribute("id", id);
            return "redirect:/products";
        } catch (Exception exception){
            model.addAttribute("status", exception.getMessage());
        }

        return "redirect:/products";
    }
}
