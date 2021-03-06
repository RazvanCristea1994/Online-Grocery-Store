package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
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
import store.model.UserRole;
import store.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserConverter userConverter;

    @GetMapping("/login")
    public String login(Authentication authentication){
        return "user/login";
    }

    @GetMapping("/create-customer-account")
    public String showCreateAccount(Model model, HttpServletRequest request){

        model.addAttribute("userData", new UserData());
        if(request.isUserInRole(String.valueOf(UserRole.ADMIN))){
            return "admin/add-account";
        }
        return "user/create-customer-account";
    }

    @PostMapping("/create-my-customer-account")
    public String createAccount(@Valid @ModelAttribute("userData") UserData userData, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", userValidator.getErrorMessage(errorField)));
        } else {
            try {
                userFacade.insertUser(userData, UserRole.CUSTOMER);
                model.addAttribute("userData", userData);
                model.addAttribute("status", "The account has been created");
            } catch (DataIntegrityViolationException exception) {
                model.addAttribute("status", exception.getMessage());
            }
        }

        return "user/create-customer-account";
    }

    @GetMapping("/update-my-account/")
    public String update(Principal principal, Model model) {

        Optional<User> optionalUser = userFacade.getByEmail(principal.getName());
        if (optionalUser.isEmpty()) {
            return "redirect:/user/login/?message=Error";
        }
        model.addAttribute("email", principal.getName());
        model.addAttribute("customer", userConverter.convert(optionalUser.get()));

        return "user/update-my-account";
    }

    @PutMapping("/update-my-account/")
    public String update(Principal principal,
                         @Valid @ModelAttribute("customer") UserViewData userViewData,
                         BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", userValidator.getErrorMessage(errorField)));
        } else {
            try {
                userFacade.updateUserAccount(principal.getName(), userViewData);
                model.addAttribute("status", "The update was done successfully");
            } catch (UsernameNotFoundException | IllegalArgumentException exception) {
                model.addAttribute("status", exception.getMessage());
            }
        }

        return "user/update-my-account";
    }

    @GetMapping("/delete")
    public String showDeleteUser() {

        return "user/delete-my-account";
    }

    @DeleteMapping("/delete-my-account")
    public String deleteMyAccount(Principal principal, HttpServletRequest request, Model model){

        try {
            userFacade.delete(principal.getName());
        } catch (NoSuchElementException exception){
            model.addAttribute("status", exception.getMessage());
        }
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null){
            session.invalidate();
        }
        return "redirect:/products";
    }
}
