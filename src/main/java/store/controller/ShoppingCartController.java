package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import store.data.order.OrderLinesData;
import store.data.order.ShoppingCartItemDeleteResponseData;
import store.data.order.ShoppingCartItemRequestData;
import store.data.order.ShoppingCartItemResponseData;
import store.facade.order.ShoppingCartItemFacade;
import store.security.util.SecurityUtil;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart-items")
@SessionAttributes(SecurityUtil.SHOPPING_CART_SESSION_ATTRIBUTE_NAME)
public class ShoppingCartController {

    @Autowired
    private ShoppingCartItemFacade shoppingCartItemFacade;
    private ShoppingCartItemResponseData shoppingCartItemResponseData;

    @PostMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    public ResponseEntity<ShoppingCartItemResponseData> addShoppingCartItem(
            @Valid ShoppingCartItemRequestData shoppingCartItemRequestData,
            BindingResult bindingResult,
            Principal principal,
            @SessionAttribute(name = SecurityUtil.SHOPPING_CART_SESSION_ATTRIBUTE_NAME, required = false) List<ShoppingCartItemRequestData> shoppingCart,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid fields!");
        }
        try {
            if (principal != null) {
                ShoppingCartItemResponseData shoppingCartItemResponseData = shoppingCartItemFacade.addShoppingCartItem(shoppingCartItemRequestData, principal.getName());
                return ResponseEntity.ok(shoppingCartItemResponseData);
            }

            if (shoppingCart == null) {
                shoppingCart = new ArrayList<>();
            }

            ShoppingCartItemResponseData shoppingCartItemResponseData = shoppingCartItemFacade.addProductShoppingCartItem(shoppingCartItemRequestData, shoppingCart);
            model.addAttribute(SecurityUtil.SHOPPING_CART_SESSION_ATTRIBUTE_NAME, shoppingCart);
            return ResponseEntity.ok(shoppingCartItemResponseData);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PostMapping(
            value = "/delete",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    public ResponseEntity<ShoppingCartItemDeleteResponseData> deleteShoppingCartItemFromCart(
            Long id,
            Principal principal,
            @SessionAttribute(name = SecurityUtil.SHOPPING_CART_SESSION_ATTRIBUTE_NAME, required = false) List<ShoppingCartItemRequestData> shoppingCart,
            Model model
    ){

        if(principal!=null){
            model.addAttribute("status", "Delete from users cart successful");
            return ResponseEntity.ok(shoppingCartItemFacade.deleteShoppingCartItemByProductIdAndUserEmail(id, principal.getName()));
        }
        model.addAttribute("status", "Delete from anonymous cart successful");
        return ResponseEntity.ok(shoppingCartItemFacade.deleteShoppingCartItemFromAnonymousCart(id, shoppingCart));
    }

    @PostMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<ShoppingCartItemResponseData> updateShoppingCartItemQuantity(@Valid ShoppingCartItemRequestData shoppingCartItemRequestData, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input!");
        }
        try {
            String email = principal.getName();
            ShoppingCartItemResponseData shoppingCartItemResponseData = shoppingCartItemFacade.updateShoppingCartItem(shoppingCartItemRequestData, email);
            return ResponseEntity.ok(shoppingCartItemResponseData);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

    @GetMapping
    public String getAllOrderLines(
            @SessionAttribute(name = SecurityUtil.SHOPPING_CART_SESSION_ATTRIBUTE_NAME, required = false) List<ShoppingCartItemRequestData> shoppingCart,
            Model model,
            Principal principal,
            @ModelAttribute("status") String status) {

        OrderLinesData orderLinesData;

        if (principal != null) {
            orderLinesData = shoppingCartItemFacade.getAllOrderLinesByEmail(principal.getName());
        } else {
            orderLinesData = shoppingCartItemFacade.getAllOrderLinesForShoppingCart(shoppingCart);
        }

        model.addAttribute("data", orderLinesData);
        model.addAttribute("status", status);

        return "order/shopping-cart";
    }

    @ExceptionHandler({ResponseStatusException.class})
    @ResponseBody
    public ResponseEntity<String> handleException(ResponseStatusException exception) {

        return new ResponseEntity<>(exception.getReason(), exception.getStatus());
    }
}
