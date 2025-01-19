package learn.demofsecommerceapp.Dao;

import learn.demofsecommerceapp.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "product-category", path = "product-category")
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
}
