package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.data.product.ProductData;
import store.facade.product.ProductFacade;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @GetMapping("/add")
    public String showAdd(Model model) {

        model.addAttribute("product", new ProductData());
        return "product/add-product";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("product") ProductData productData, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", errorField.getDefaultMessage()));
            return "product/add-product";
        } else {
            try {
                productFacade.save(productData);
                model.addAttribute("status", "The product has been added");
                return "redirect:/products";
            } catch (IllegalArgumentException exception) {
                model.addAttribute("product");
                model.addAttribute("status", "The product cannot be added");
                return "product/add-product";
            }
        }
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
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", errorField.getDefaultMessage()));
            return "product/update-product";
        } else {
            try {
                productFacade.update(productData);
                model.addAttribute("status", "The update was done successfully");
                return "redirect:/products";
            } catch (IllegalArgumentException exception) {
                model.addAttribute("product");
                model.addAttribute("status", "The product cannot be updated");
                return "product/update-product";
            }
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {

        try {
            productFacade.delete(id);
            model.addAttribute("status", "Product has been deleted");
        } catch (Exception exception){
            model.addAttribute("status", "Product could not be deleted");
        }

        return "redirect:/products";
    }
}
