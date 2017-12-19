package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.routes.constants.RouteNames;
import com.epam.mentoring.routes.processors.supplier.GetAllSuppliersProcessor;
import com.epam.mentoring.routes.processors.supplier.GetSupplierByIdProcessor;
import com.epam.mentoring.routes.processors.supplier.SaveSupplierProcessor;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierRoute extends RouteBuilder {

    @Autowired
    SupplierService supplierService;

    @Override
    public void configure() throws Exception {
        from(RouteNames.SUPPLIER_ROUTE).routeId(RouteNames.SUPPLIER_ROUTE_ID)
                .choice()
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_ALL))
                    .process(new GetAllSuppliersProcessor(supplierService))
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_BY_ID))
                    .process(new GetSupplierByIdProcessor(supplierService))
                .when(header(Headers.METHOD).isEqualTo(Headers.POST))
                    .unmarshal().json(JsonLibrary.Jackson, SupplierForm.class)
                    .process(new SaveSupplierProcessor(supplierService))
                .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .marshal().json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .end();
    }
}
