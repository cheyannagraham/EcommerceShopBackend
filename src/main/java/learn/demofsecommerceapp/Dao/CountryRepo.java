package learn.demofsecommerceapp.Dao;

import learn.demofsecommerceapp.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestResource
public interface CountryRepo extends JpaRepository<Country, Long> {

    @Transactional(readOnly = true)
    List<Country> findAll();
}
