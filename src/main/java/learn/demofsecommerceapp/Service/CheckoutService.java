package learn.demofsecommerceapp.Service;

import learn.demofsecommerceapp.Dao.CustomerRepo;
import learn.demofsecommerceapp.Dto.PurchaseDto;
import learn.demofsecommerceapp.Dto.PurchaseResponseDto;
import learn.demofsecommerceapp.Entity.Customer;
import learn.demofsecommerceapp.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CheckoutService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CheckoutService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public PurchaseResponseDto checkout(PurchaseDto purchaseDto) {
        Order order = new Order();
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        order.setCustomer(purchaseDto.getCustomer());
        order.setShippingAddress(purchaseDto.getShippingAddress());
        order.setBillingAddress(purchaseDto.getBillingAddress());
        purchaseDto.getOrderItems().forEach(order::addOrderItem);

        Customer customer = order.getCustomer();
        customer.addOrder(order);

        customerRepo.save(customer);

        return new PurchaseResponseDto(order.getOrderTrackingNumber());
    }
}
