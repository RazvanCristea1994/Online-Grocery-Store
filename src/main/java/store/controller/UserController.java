package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import store.model.UserRole;
import store.validator.UserValidator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
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

    @GetMapping("/login")
    public String login(Authentication authentication){
        return "user/login";
    }

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

    @GetMapping("/update-account/{email}")
    public String updateCustomer(@PathVariable("email") String email, Authentication authentication, Model model) {

        if (authentication == null) {
            return "redirect:/users";
        }
        Optional<User> optionalUser = userFacade.getByEmail(authentication.getName());
        if (optionalUser.isEmpty()) {
            return "error";
        }
        model.addAttribute("user", userConverter.convert(optionalUser.get()));

        return "user/update-customer";
    }

    @PutMapping("/update-account/{email}")
    public String updateCustomer(@PathVariable("email") String email, Authentication authentication,
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

    @GetMapping("/delete/{email}")
    public String deleteUser(@PathVariable("email") String email, Principal principal, HttpServletRequest request){

        userFacade.delete(email);
        if (email.equals(principal.getName())){
            HttpSession session = request.getSession(false);
            SecurityContextHolder.clearContext();
            if(session != null){
                session.invalidate();
            }
            return "redirect:/";
        }
        return "redirect:/users";
    }

    @GetMapping("/delete-my-account")
    public String deleteMyAccount(Principal principal, HttpServletRequest request){

        userFacade.delete(principal.getName());
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null){
            session.invalidate();
        }
        return "redirect:/?message=Your account has been deleted!";
    }
}
