package com.epam.mentoring.routes.processors.product;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

public class SaveProductProcessor implements Processor{

    private ProductService productService;

    public SaveProductProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        ProductForm productForm = (ProductForm) exchange.getIn().getBody();
        Integer id = productService.saveProduct(productForm);
        HashMap<String, Integer> idMap = new HashMap<>();
        idMap.put("id", id);
        exchange.getIn().setBody(idMap);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.CREATED);
    }
}
