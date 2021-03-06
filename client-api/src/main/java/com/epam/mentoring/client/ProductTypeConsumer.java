package com.epam.mentoring.client;

import com.epam.mentoring.client.exception.ServerDataAccessException;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;

import java.util.List;

public interface ProductTypeConsumer {

    Integer saveProductType(ProductType productType) throws ServerDataAccessException;

    Integer saveProductType(ProductTypeForm productTypeForm) throws ServerDataAccessException;

    ProductType findProductType(Integer id) throws ServerDataAccessException;

    List<ProductType> findAll() throws ServerDataAccessException;
}
