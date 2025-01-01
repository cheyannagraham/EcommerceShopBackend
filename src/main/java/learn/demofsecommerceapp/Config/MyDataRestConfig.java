package learn.demofsecommerceapp.Config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Type;
import learn.demofsecommerceapp.Entity.Product;
import learn.demofsecommerceapp.Entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.stream.Stream;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] unsupportedMethods = {HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH};

        config.getExposureConfiguration()
//                .forDomainType(Product.class) //Leaving this off does it for all domains
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedMethods))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedMethods)));


//        ---------------------------EXPOSING ID'S--------------------
        // Specific Categories
//        config.exposeIdsFor(Product.class, ProductCategory.class);

        // all entitys
        config.exposeIdsFor(entityManager.getMetamodel()
                .getEntities().stream().map(EntityType::getJavaType).toArray(Class[]::new));
    }
}
