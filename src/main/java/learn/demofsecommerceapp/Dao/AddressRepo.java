package learn.demofsecommerceapp.Dao;

import learn.demofsecommerceapp.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
