package com.epam.mentoring.approutes.processors.supplier;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.dto.mapstruct.SupplierMapper;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.core.Response;

public class GetSupplierByIdProcessor implements Processor {

    private SupplierMapper supplierMapper = Mappers.getMapper(SupplierMapper.class);

    private SupplierService supplierService;

    public GetSupplierByIdProcessor(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Supplier supplier = supplierService.getSupplierById(id);
        exchange.getIn().setBody(supplierMapper.supplierToSupplierView(supplier));
    }
}
