package com.epam.mentoring.client.rest;

import com.epam.mentoring.client.ProductConsumer;
import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
public class RestProductConsumer implements ProductConsumer{

    private String STOCK_URI;
    private String PRODUCT_URI;

    private RestTemplate restTemplate;

    public RestProductConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        STOCK_URI = "http://localhost:8080/stock";
        PRODUCT_URI = "http://localhost:8080/product";
    }

    public RestProductConsumer(RestTemplate restTemplate, String stockUri, String productUri) {
        this.STOCK_URI = stockUri;
        this.PRODUCT_URI = productUri;
        this.restTemplate = restTemplate;
    }

    @Override
    public Product findProductById(Long id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Product findProductByName(String name) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<Product> findAllProductsByType(ProductType type) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveProduct(Product product) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Integer saveProduct(ProductForm productForm) throws ServerDataAccessException {
        log.debug("Saving product form: " + productForm.toString());
        URI uri = restTemplate.postForLocation(URI.create(PRODUCT_URI), productForm);
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<ProductView> getAllProductViews() throws ServerDataAccessException {
        log.debug("Getting all product views");
        try {
            // It appears this is incorrect way to get list of objects
            // see https://stackoverflow.com/questions/23674046/get-list-of-json-objects-with-spring-resttemplate
//            ArrayList<ProductView> productViews = restTemplate.getForObject("localhost:8080/product", ArrayList.class);
//            return productViews;
            // TODO: check other controllers which have getList methods

            ResponseEntity<List<ProductView>> response =
                    restTemplate.exchange(PRODUCT_URI,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductView>>() {
                            });
            List<ProductView> productViews = response.getBody();
            return productViews;
        } catch (RestClientException ex) {
            log.error("Can't get data from server: {} {}", PRODUCT_URI, ex);
            throw new ServerDataAccessException("Can't get data from server " + PRODUCT_URI, ex);
        }
    }

    @Override
    public Integer getProductQuantity(Long id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public Map<Product, Integer> getAllProductsWithQuantites() throws ServerDataAccessException {
        return null;
    }

    @Override
    public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() throws ServerDataAccessException {
        log.debug("Getting all products with quantities as views");
        ResponseEntity<List> productsWithQuantities;
        List<ProductWithQuantityView> body;
        try {
//            productsWithQuantities = restTemplate.getForEntity(STOCK_URI,
//                    List.class);
//            if (!productsWithQuantities.getStatusCode().equals(HttpStatus.OK)) {
//                throw new ServerDataAccessException("Request denied: "+
//                        productsWithQuantities.getStatusCode().toString());
//            }
//            body = productsWithQuantities.getBody();
            ResponseEntity<List<ProductWithQuantityView>> response =
                    restTemplate.exchange(STOCK_URI,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductWithQuantityView>>() {
                            });
            List<ProductWithQuantityView> productViews = response.getBody();
            return productViews;
        } catch (RestClientException ex) {
            log.error("Can't get data from server: {}", ex);
            throw new ServerDataAccessException("Can't get data from server " + STOCK_URI, ex);
        }
    }

    @Override
    public void deleteProductById(Long id) throws ServerDataAccessException {

    }
}
