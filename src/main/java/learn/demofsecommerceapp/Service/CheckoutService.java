package learn.demofsecommerceapp.Service;

import learn.demofsecommerceapp.Dao.AddressRepo;
import learn.demofsecommerceapp.Dao.CustomerRepo;
import learn.demofsecommerceapp.Dto.PurchaseDto;
import learn.demofsecommerceapp.Dto.PurchaseResponseDto;
import learn.demofsecommerceapp.Entity.Address;
import learn.demofsecommerceapp.Entity.Customer;
import learn.demofsecommerceapp.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CheckoutService {
    private final CustomerRepo customerRepo;
    private final AddressRepo addressRepo;

    @Autowired
    public CheckoutService(CustomerRepo customerRepo, AddressRepo addressRepo) {
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
    }

    public PurchaseResponseDto checkout(PurchaseDto purchaseDto) {
        Order order = purchaseDto.getOrder();
        order.setOrderTrackingNumber(UUID.randomUUID().toString());

        //Find customer
        Customer customer = this.customerRepo.findByEmail(purchaseDto.getCustomer().getEmail()).orElse(purchaseDto.getCustomer());

        //Find address

        // Set matcher configs
        ExampleMatcher addressMatcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("id")
                .withIgnoreCase();

        // Build matcher examples
        Example<Address> billingExample = Example.of(purchaseDto.getBillingAddress(), addressMatcher);
        Example<Address> shippingExample = Example.of(purchaseDto.getShippingAddress(), addressMatcher);

        // Determine if records exists
        Address bAdress = this.addressRepo.findOne(billingExample).orElse(purchaseDto.getBillingAddress());
        Address sAdress = this.addressRepo.findOne(shippingExample).orElse(purchaseDto.getShippingAddress());

        order.setCustomer(customer);
        order.setShippingAddress(sAdress);
        order.setBillingAddress(bAdress);
        purchaseDto.getOrderItems().forEach(order::addOrderItem);

        customer.addOrder(order);
        order.setStatus("completed");
        customerRepo.save(customer);

        return new PurchaseResponseDto(order.getOrderTrackingNumber());
    }
}
