package tr.edu.anadolu.productlistapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tr.edu.anadolu.productlistapp.model.Product;
import tr.edu.anadolu.productlistapp.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    static int id = 1;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<Product>> findAll(int pageNumber, int sizeNumber) {
        try {
            Pageable pageableRequest = PageRequest.of(pageNumber, sizeNumber);
            Page<Product> page = productRepository.findAll(pageableRequest);
            List<Product> products = page.getContent();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Product> createNewProduct(Product product) {

        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        for (Product prdct : products) {
            if (prdct.getProductName().equals(product.getProductName())) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        product.setProductId("URUN_" + id++);
        product.setAvailability(true);
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public Product getProduct(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(String productId, Product newProduct) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            foundProduct.setProductName(newProduct.getProductName());
            foundProduct.setProductPrice(newProduct.getProductPrice());
            foundProduct.setProductType(newProduct.getProductType());
            foundProduct.setProductImage(newProduct.getProductImage());
            return productRepository.save(foundProduct);
        }
        return null;
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public ResponseEntity<List<Product>> sortByNameAsc(int pageNumber, int sizeNumber) {

        Pageable pageableRequest = PageRequest.of(pageNumber, sizeNumber, Sort.by("productName"));
        Page<Product> page = productRepository.findAll(pageableRequest);
        List<Product> products = page.getContent();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> sortByPriceAsc(int pageNumber, int sizeNumber) {
        Pageable pageableRequest = PageRequest.of(pageNumber, sizeNumber, Sort.by("productPrice"));
        Page<Product> page = productRepository.findAll(pageableRequest);
        List<Product> products = page.getContent();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> sortByNameDesc(int pageNumber, int sizeNumber) {
        Pageable pageableRequest = PageRequest.of(pageNumber, sizeNumber, Sort.by("productName").descending());
        Page<Product> page = productRepository.findAll(pageableRequest);
        List<Product> products = page.getContent();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> sortByPriceDesc(int pageNumber, int sizeNumber) {
        Pageable pageableRequest = PageRequest.of(pageNumber, sizeNumber, Sort.by("productPrice").descending());
        Page<Product> page = productRepository.findAll(pageableRequest);
        List<Product> products = page.getContent();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> sortByType(int pageNumber, int sizeNumber, String usergivenType) {
        Pageable pageableRequest = PageRequest.of(pageNumber, sizeNumber);
        Page<Product> page = productRepository.findAll(pageableRequest);
        List<Product> products = new ArrayList<>(page.getContent());
        List<Product> productType = new ArrayList<>();
        for( Product product : products ) {
            if(product.getProductType().equals(usergivenType)) {
                productType.add(product);
            }
        }
        return new ResponseEntity<>(productType, HttpStatus.OK);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Product setAvailability(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            foundProduct.setAvailability(!foundProduct.isAvailability()); // this is the simplifed version of if-else statement below
//            if (foundProduct.isAvailability()) {
//                foundProduct.setAvailability(false);
//            } else {
//                foundProduct.setAvailability(true);
//            }
            return productRepository.save(foundProduct);
        }
        return null;
    }

    public ResponseEntity<List<Product>> findAvailableProducts(int page, int size) {
        Pageable pageableRequest = PageRequest.of(page, size, Sort.by("productId"));
        Page<Product> page1 = productRepository.findAll(pageableRequest);
        List<Product> products = page1.getContent();
        List<Product> availableProducts = new ArrayList<>();
        for( Product product : products ) {
            if(product.isAvailability()) {
                availableProducts.add(product);
            }
        }
        return new ResponseEntity<>(availableProducts, HttpStatus.OK);

    }

    public ResponseEntity<List<Product>> findNotAvailableProducts(int page, int size) {
        Pageable pageableRequest = PageRequest.of(page, size, Sort.by("productId"));
        Page<Product> page2 = productRepository.findAll(pageableRequest);
        List<Product> products = page2.getContent();
        List<Product> availableProducts = new ArrayList<>();
        for( Product product : products ) {
            if(!product.isAvailability()) {
                availableProducts.add(product);
            }
        }
        return new ResponseEntity<>(availableProducts, HttpStatus.OK);

    }
}
