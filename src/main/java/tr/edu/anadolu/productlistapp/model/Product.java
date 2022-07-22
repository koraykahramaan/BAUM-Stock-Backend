package tr.edu.anadolu.productlistapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {

    @Id
    private String productId;
    private String productName;
    private double productPrice;
    private String productType;
    private String productImage;
    private boolean availability;

}
