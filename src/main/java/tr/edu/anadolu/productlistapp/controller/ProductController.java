package tr.edu.anadolu.productlistapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.anadolu.productlistapp.model.Product;
import tr.edu.anadolu.productlistapp.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/Product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/sortByNameAsc")
    public ResponseEntity<List<Product>> sortByNameAsc(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        return productService.sortByNameAsc(page, size);
    }

    @GetMapping("/sortByNameDesc")
    public ResponseEntity<List<Product>> sortByNameDesc(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        return productService.sortByNameDesc(page, size);
    }

    @GetMapping("/sortByPriceAsc")
    public ResponseEntity<List<Product>> sortByPriceAsc(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        return productService.sortByPriceAsc(page, size);
    }

    @GetMapping("/sortByPriceDesc")
    public ResponseEntity<List<Product>> sortByPriceDesc(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        return productService.sortByPriceDesc(page, size);
    }

    @GetMapping("/sortByType/{productType}")
    public ResponseEntity<List<Product>> sortByType(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size, @PathVariable String productType) {
        return productService.sortByType(page, size, productType);
    }

    @GetMapping("/listProducts")
    public ResponseEntity<List<Product>> findAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        return productService.findAll(page, size);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return productService.createNewProduct(product);
    }

    @GetMapping("/{productId}")
    public Product getOneProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable String productId, @RequestBody Product newProduct) {
        return productService.updateProduct(productId, newProduct);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }

    // set true or false to availability of product
    @PutMapping("/{productId}/availability")
    public Product setAvailability(@PathVariable String productId) {
        return productService.setAvailability(productId);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

}
