package store.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import store.data.review.ReviewApproveData;
import store.data.review.ReviewData;
import store.data.review.ReviewResponseData;
import store.facade.review.ReviewFacade;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ReviewController {

    @Autowired
    private ReviewFacade reviewFacade;

    @PostMapping(
            value = "/add-review",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    public ReviewResponseData addReview(@ModelAttribute("review") ReviewData reviewData, Principal principal){
        try{
            return reviewFacade.save(reviewData, principal.getName());
        } catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Must be logged in to submit a review!");
        } catch (ConstraintViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rating cannot be empty!");
        }
    }

    @GetMapping("/get-all-reviews")
    public String getAllReviews(Model model){
            model.addAttribute("reviews",reviewFacade.getAllReview());
            return "user/all-reviews";
    }


    @PostMapping(
            value = "/approve-review",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String approveReview(@Valid ReviewApproveData reviewApproveData, Model model){
            reviewFacade.approve(reviewApproveData);
            return "redirect:/products/get-all-reviews";
    }



    @ExceptionHandler({ResponseStatusException.class})
    @ResponseBody
    public ResponseEntity<String> handleException(ResponseStatusException exception) {

        return new ResponseEntity<>(exception.getReason(), exception.getStatus());
    }
}
