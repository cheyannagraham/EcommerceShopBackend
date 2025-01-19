package learn.demofsecommerceapp.Dao;

import learn.demofsecommerceapp.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public interface StateRepo extends JpaRepository<State, Long> {

    @Transactional(readOnly = true)
    List<State> findAllByCountryId(@Param("countryId") Long countryId); // do I need Long countryId? not using it // yes, needed for rest binding (spring magic)

}
