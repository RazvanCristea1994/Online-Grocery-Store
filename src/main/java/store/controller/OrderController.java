package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import store.data.order.OrderData;
import store.facade.order.OrderFacade;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @GetMapping
    public String getOrdersByUserEmail(Authentication authentication, Model model) {
        List<OrderData> orders = orderFacade.getOrdersByUserEmail(authentication.getName());
        model.addAttribute("ordersHistory", orders);
        return "order/ordersHistory";
    }

    @PostMapping
    public String placeOrder(Authentication authentication, Model model, RedirectAttributes redirectAttributes) {

        try {
            OrderData orderData = orderFacade.placeOrder(authentication.getName());
            model.addAttribute("order", orderData);
            model.addAttribute("status", "");
            return "order/order-confirmation";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("status", e.getMessage());
            return "redirect:/shopping-cart-items";
        }
    }
}
