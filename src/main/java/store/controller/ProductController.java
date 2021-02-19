package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import store.data.product.ProductData;
import store.data.product.ProductDetailsData;
import store.data.review.ReviewData;
import store.facade.product.ProductFacade;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private static final int PRODUCTS_PER_PAGE = 4;

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

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model) {

        try {
            ProductDetailsData productDetailsData = productFacade.getProductDetailsDataById(id);
            model.addAttribute("product", productDetailsData);
            ReviewData reviewData = new ReviewData();
            model.addAttribute("review", reviewData);
        } catch (IllegalArgumentException exception) {
            model.addAttribute("error", exception.getMessage());
        }

        return "product/product-details";
    }

    @ExceptionHandler({ResponseStatusException.class})
    @ResponseBody
    public ResponseEntity<String> handleException(ResponseStatusException exception) {

        return new ResponseEntity<>(exception.getReason(), exception.getStatus());
    }

    @GetMapping("homepage/filter")
    public String getAvailableProductsByCategoryIds(@RequestParam("categoryIds") List<Long> categoryIds, Model model) {

        if (categoryIds.isEmpty()) {
            return "redirect:/products/homepage";
        }

        List<ProductData> productDataList = productFacade.getAvailableProductDatasByCategoryIds(categoryIds);
        model.addAttribute("products", productDataList);

        return "product/available-product";
    }

    @GetMapping("/homepage/{pageNumber}")
    public String getAvailableProducts(@PathVariable("pageNumber")int pageNumber, Model model) {
        List<ProductData> productDataList = productFacade.getAvailableProductDatas(PRODUCTS_PER_PAGE, pageNumber);

        model.addAttribute("noOfPages", productFacade.getNoOfPages(PRODUCTS_PER_PAGE));
        model.addAttribute("products", productDataList);
        model.addAttribute("status", "ok");

        return "product/available-product";
    }
}
