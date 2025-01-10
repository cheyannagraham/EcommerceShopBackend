package learn.demofsecommerceapp.Controller;

import learn.demofsecommerceapp.Dto.PurchaseDto;
import learn.demofsecommerceapp.Service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CheckoutController {

    CheckoutService checkoutService;
    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/checkout")
    @ResponseBody
    public String checkout(@RequestBody PurchaseDto purchase) {
        System.out.println("-".repeat(20));
        System.out.println("Saving Purchase");
        return this.checkoutService.checkout(purchase);
    }
}
