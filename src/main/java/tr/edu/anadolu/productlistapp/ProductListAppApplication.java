package tr.edu.anadolu.productlistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProductListAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductListAppApplication.class, args);
    }

}
