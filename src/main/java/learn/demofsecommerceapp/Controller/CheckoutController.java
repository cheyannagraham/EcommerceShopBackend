package learn.demofsecommerceapp.Controller;

import learn.demofsecommerceapp.Dto.PurchaseDto;
import learn.demofsecommerceapp.Dto.PurchaseResponseDto;
import learn.demofsecommerceapp.Service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("${app.frontUrl}")
public class CheckoutController {

    CheckoutService checkoutService;
    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/checkout/purchase")
    @ResponseBody
    public PurchaseResponseDto checkout(@RequestBody PurchaseDto purchase) {
        System.out.println("-".repeat(20));
        System.out.println("Saving Purchase");
        return this.checkoutService.checkout(purchase);
    }
}
