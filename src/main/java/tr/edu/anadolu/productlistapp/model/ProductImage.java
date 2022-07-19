package tr.edu.anadolu.productlistapp.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ProductImage {
    private Product product;
    private byte[] image;

}
