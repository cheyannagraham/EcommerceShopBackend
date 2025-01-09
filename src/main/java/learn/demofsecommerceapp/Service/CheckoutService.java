package learn.demofsecommerceapp.Service;

import learn.demofsecommerceapp.Dao.CustomerRepo;
import learn.demofsecommerceapp.Dto.PurchaseDto;
import learn.demofsecommerceapp.Dto.PurchaseResponseDto;
import learn.demofsecommerceapp.Entity.Customer;
import learn.demofsecommerceapp.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    CustomerRepo customerRepo;

    @Autowired
    public CheckoutService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public PurchaseResponseDto checkout(PurchaseDto purchaseDto) {
        System.out.println("PurchaseDto: " + purchaseDto);
        Order order = new Order();
        order.setCustomer(purchaseDto.getCustomer());
        order.setOrderItems(purchaseDto.getOrderItems());
        order.setShippingAddress(purchaseDto.getShippingAddress());
        order.setBillingAddress(purchaseDto.getBillingAddress());
        Customer customer = order.getCustomer();
        customer.addOrder(order);

        customerRepo.save(customer);
        return null;
    }
}
