package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import store.data.user.UserData;
import store.data.user.UserViewData;
import store.facade.converter.user.UserConverter;
import store.facade.user.UserFacade;
import store.model.User;
import store.validator.UserValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserConverter userConverter;

    @GetMapping("/add-admin")
    public String showAddAdmin(Model model) {

        model.addAttribute("userData", new UserData());
        return "user/add-admin";
    }

    @PostMapping("/add-admin")
    public String addAdmin(@Valid @ModelAttribute("userData") UserData userData, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", userValidator.getErrorMessage(errorField)));
        } else {
            try {
                userFacade.insertAdmin(userData);
                model.addAttribute("userData", new UserData());
                model.addAttribute("status", "The account has been created");
            } catch (DataIntegrityViolationException exception) {
                model.addAttribute("status", "Invalid email or phone number");
            }
        }

        return "user/add-admin";
    }

    @GetMapping("/add-customer")
    public String showAddCustomer(Model model){

        model.addAttribute("userData", new UserData());
        return "user/add-customer";
    }

    @PostMapping("/add-customer")
    public String addCustomer(@Valid @ModelAttribute("userData") UserData userData, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", userValidator.getErrorMessage(errorField)));
        } else {
            try {
                userFacade.insertCustomer(userData);
                model.addAttribute("userData", new UserData());
                model.addAttribute("status", "The account has been created");
            } catch (DataIntegrityViolationException exception) {
                model.addAttribute("status", "Invalid email or phone number");
            }
        }

        return "user/add-customer";
    }

    @GetMapping
    public String getAll(Model model) {

        model.addAttribute("users", userFacade.getAll());
        return "user/all-users";
    }

    @GetMapping("/update-account")
    public String updateCustomer(Authentication authentication, Model model) {

        Optional<User> optionalUser = userFacade.getByEmail(authentication.getName());
        if (optionalUser.isEmpty()) {
            return "error";
        }
        model.addAttribute("user", userConverter.convert(optionalUser.get()));

        return "update-customer";
    }

    @PutMapping("/update-account")
    public String updateCustomer(Authentication authentication,
                                 @Valid @ModelAttribute("userViewData") UserViewData userViewData,
                                 BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", userValidator.getErrorMessage(errorField)));
        } else {
            try {
                userFacade.updateUserAccount(authentication.getName(), userViewData);
                model.addAttribute("status", "The update was done successfully");
            } catch (UsernameNotFoundException | IllegalArgumentException exception) {
                model.addAttribute("status", "Invalid update");
            }
        }

        return "user/update-customer";
    }
}
