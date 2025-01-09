package learn.demofsecommerceapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.demofsecommerceapp.Dto.PurchaseDto;
import learn.demofsecommerceapp.Service.CheckoutService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoFSecommerceappApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoFSecommerceappApplication.class, args);
        String purchaseData = """
                {
                   "customer":{
                      "firstName":"afasa",
                      "lastName":"afasa",
                      "email":"afasa@test.com"
                   },
                   "shippingAddress":{
                      "street":"afasa",
                      "city":"afasa",
                      "state":"Alberta",
                      "country":"Canada",
                      "zipCode":"afasa"
                   },
                   "billingAddress":{
                      "street":"fsfsf",
                      "city":"sfdsf",
                      "state":"Acre",
                      "country":"Brazil",
                      "zipCode":"19111"
                   },
                   "order":{
                      "totalPrice":36.98,
                      "totalQuantity":2
                   },
                   "orderItems":[
                      {
                         "imageUrl":"assets/images/products/coffeemugs/coffeemug-luv2code-1000.png",
                         "quantity":1,
                         "unitPrice":18.99,
                         "productId":26
                      },
                      {
                         "imageUrl":"assets/images/products/mousepads/mousepad-luv2code-1000.png",
                         "quantity":1,
                         "unitPrice":17.99,
                         "productId":51
                      }
                   ]
                }
                """;

        CheckoutService checkoutService = context.getBean(CheckoutService.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PurchaseDto purchaseDto = objectMapper.readValue(purchaseData, PurchaseDto.class);
            checkoutService.checkout(purchaseDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

