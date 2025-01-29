package learn.demofsecommerceapp.Service;

import learn.demofsecommerceapp.Dao.CustomerRepo;
import learn.demofsecommerceapp.Dto.PurchaseDto;
import learn.demofsecommerceapp.Dto.PurchaseResponseDto;
import learn.demofsecommerceapp.Entity.Customer;
import learn.demofsecommerceapp.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
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
        Order order = purchaseDto.getOrder();
        order.setOrderTrackingNumber(UUID.randomUUID().toString());

        //Find customer
        Customer customer = this.customerRepo.findByEmail(purchaseDto.getCustomer().getEmail()).orElse(purchaseDto.getCustomer());
        System.out.println("PDTO customer " + purchaseDto.getCustomer());
        System.out.println("found customer " + customer);
        order.setCustomer(customer);
        order.setShippingAddress(purchaseDto.getShippingAddress());
        order.setBillingAddress(purchaseDto.getBillingAddress());
        purchaseDto.getOrderItems().forEach(order::addOrderItem);

        customer.addOrder(order);
        customerRepo.save(customer);

        return new PurchaseResponseDto(order.getOrderTrackingNumber());
    }
}
