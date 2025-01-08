package learn.demofsecommerceapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Customer extends JpaRepository<Long,Customer> {
}
