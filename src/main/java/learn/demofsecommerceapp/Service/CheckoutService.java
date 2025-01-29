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
        Address shipppingAddress = this.addressExists(purchaseDto.getShippingAddress()).orElse(purchaseDto.getShippingAddress());
        Address billingAddress;
        if (purchaseDto.getShippingAddress().equals(purchaseDto.getBillingAddress())) {
            System.out.println("Addresses are the same");
            billingAddress = shipppingAddress;
        } else
            billingAddress = this.addressExists(purchaseDto.getBillingAddress()).orElse(purchaseDto.getBillingAddress());

        order.setCustomer(customer);

        order.setShippingAddress(shipppingAddress);
        order.setBillingAddress(billingAddress);

        purchaseDto.getOrderItems().forEach(order::addOrderItem);

        customer.addOrder(order);
        order.setStatus("completed");
        customerRepo.save(customer);

        return new PurchaseResponseDto(order.getOrderTrackingNumber());
    }


    Optional<Address> addressExists(Address address) {
        // Set matcher configs
        ExampleMatcher addressMatcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("id")
                .withIgnoreCase();

        // Build matcher examples
        Example<Address> addressExample = Example.of(address, addressMatcher);

        // Return record if exists
        return this.addressRepo.findOne(addressExample);
    }
}
