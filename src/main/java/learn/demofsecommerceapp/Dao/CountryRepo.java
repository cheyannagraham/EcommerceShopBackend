package learn.demofsecommerceapp.Dao;

import learn.demofsecommerceapp.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("${app.frontUrl}")
public interface CountryRepo extends JpaRepository<Country, Long> {

    @Transactional(readOnly = true)
    List<Country> findAll();
}
