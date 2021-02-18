package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController extends UserController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserConverter userConverter;

    @GetMapping("/add-admin")
    public String showAddAdmin(Model model) {

        model.addAttribute("userData", new UserData());
        return "admin/add-account";
    }

    @PostMapping("/add-admin")
    public String addAdmin(@Valid @ModelAttribute("userData") UserData userData, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", userValidator.getErrorMessage(errorField)));
        } else {
            try {
                userFacade.insertUser(userData, UserRole.ADMIN);
                model.addAttribute("userData", userData);
                model.addAttribute("status", "The account has been created");
            } catch (DataIntegrityViolationException exception) {
                model.addAttribute("status", exception.getMessage());
            }
        }

        return "admin/add-account";
    }

    @GetMapping
    public String getAll(Model model) {

        model.addAttribute("admins", userFacade.getByRole(UserRole.ADMIN));
        model.addAttribute("users", userFacade.getByRole(UserRole.CUSTOMER));
        return "admin/all-users";
    }

    @GetMapping("/update-user/{email:.+}")
    public String updateUser(@PathVariable("email") String email, Model model) {

        Optional<User> optionalUser = userFacade.getByEmail(email);
        if (optionalUser.isEmpty()) {
            return "redirect:/user/login/?message=Error";
        }
        model.addAttribute("email", email);
        model.addAttribute("user", userConverter.convert(optionalUser.get()));

        return "admin/update-user";
    }

    @PutMapping("/update-user/{email:.+}")
    public String updateUser(@PathVariable("email") String email,
                         @Valid @ModelAttribute("customer") UserViewData userViewData,
                         BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(errorField ->
                    model.addAttribute(errorField.getField() + "_error", userValidator.getErrorMessage(errorField)));
        } else {
            try {
                userFacade.updateUserAccount(email, userViewData);
                model.addAttribute("status", "The update was done successfully");
                return "redirect:";
            } catch (UsernameNotFoundException | IllegalArgumentException exception) {
                model.addAttribute("status", exception.getMessage());
            }
        }

        return "admin/update-user";
    }

    @GetMapping("/delete-account/{email:.+}")
    public String deleteUser(@PathVariable("email") String email, Model model) {
        Optional<User> optionalUser = userFacade.getByEmail(email);
        if (optionalUser.isEmpty()) {
            return "redirect:/admin/?message=Error";
        }
        model.addAttribute("email", email);
        return "admin/delete-admin";
    }

    @DeleteMapping("/delete-account/{email:.+}")
    public String deleteUser(@PathVariable("email") String email, Principal principal, HttpServletRequest request, Model model){

        try {
            userFacade.delete(email);
        } catch (NoSuchElementException exception){
            model.addAttribute("status", exception.getMessage());
        }
        if (email.equals(principal.getName())){
            HttpSession session = request.getSession(false);
            SecurityContextHolder.clearContext();
            if(session != null){
                session.invalidate();
            }
            return "redirect:/products";
        }
        return "redirect:/admin";
    }
}
