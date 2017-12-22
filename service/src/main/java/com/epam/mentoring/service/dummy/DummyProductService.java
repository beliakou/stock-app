package com.epam.mentoring.service.dummy;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.data.model.dto.ProductView;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DummyProductService implements ProductService{
    @Override
    public Product findProductById(Integer id) throws DataAccessException {
        return new Product(0, "Hello", BigDecimal.ZERO, null);
    }

    @Override
    public Integer saveProduct(Product product) throws DataAccessException {
        return 12;
    }

    @Override
    public Integer saveProduct(ProductForm productForm) throws DataAccessException {
        return null;
    }

    @Override
    public void updateProduct(Product product) throws DataAccessException {

    }

    @Override
    public void updateProduct(ProductForm product) throws DataAccessException {

    }

    @Override
    public List<Product> getAllProducts() throws DataAccessException {

        return new ArrayList<Product>(){
            {
                add(new Product(0, "Hello", BigDecimal.ZERO, null));
            }
        };
    }

    @Override
    public Map<Product, Integer> getAllProductsWithQuantities() throws DataAccessException {
        return null;
    }

    @Override
    public void deleteProductById(Integer id) throws DataAccessException {

    }

    @Override
    public List<ProductWithQuantityView> getAllProductsWithQuantitiesViews() {
        return null;
    }
}
