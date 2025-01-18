package learn.demofsecommerceapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.demofsecommerceapp.Dto.PurchaseDto;
import learn.demofsecommerceapp.Service.CheckoutService;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoFSecommerceappApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoFSecommerceappApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory tomcat() {
        return new TomcatServletWebServerFactory(){
            @Override
            protected void customizeConnector(Connector connector){
                connector.setPort(Integer.parseInt(System.getenv("PORT")));
            }
        };
    }

}

