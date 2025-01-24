package learn.demofsecommerceapp.Dao;

import learn.demofsecommerceapp.Entity.Product;
import learn.demofsecommerceapp.Entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@RepositoryRestResource(excerptProjection = CategoryIdProjection.class)
@RestResource
public interface ProductRepo extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
    Page<Product> findProductsByNameContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);
//    @Override
//    Page<Product> findAll(Pageable pageable); think sends page by default
}


//Sends ProductCategory Data along with other fields
//@Projection(name="categoryIdProjection", types = { Product.class })
//interface CategoryIdProjection {
//    Long getId();
//    ProductCategory getCategory();
//    String getSku();
//    String getName();
//    String getDescription();
//    BigDecimal getUnitPrice();
//    String getImageUrl();
//    Boolean getActive();
//    Integer getUnitsInStock();
//    Instant getDateCreated();
//    Instant getLastUpdated();
//}
