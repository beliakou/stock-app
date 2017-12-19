package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.routes.constants.RouteNames;
import com.epam.mentoring.service.SupplierService;
import com.epam.mentoring.test.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SupplierRouteTest {

    @Autowired
    ModelCamelContext context;

    @Autowired
    ProducerTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SupplierService supplierServiceMock;

    ArgumentCaptor<SupplierForm> supplierFormArgumentCaptor;

    @Before
    public void setUp() {
        supplierFormArgumentCaptor = ArgumentCaptor.forClass(SupplierForm.class);
    }

    @Test
    public void getAllSuppliersTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.GET_ALL);
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.SUPPLIER_ROUTE, exchange);
        System.out.println(response.getIn().getBody());
        assertEquals(objectMapper.writeValueAsString(TestData.suppliers()), response.getIn().getBody());
    }

    @Test
    public void getSupplierByIdTest() throws JsonProcessingException {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.GET_BY_ID);
        in.setHeader(Headers.ID, Integer.valueOf(42));
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.SUPPLIER_ROUTE, exchange);
        assertEquals(objectMapper.writeValueAsString(TestData.suppliers().get(0)), response.getIn().getBody());
    }

    @Test
    public void saveSupplierTest() {
        Exchange exchange = new DefaultExchange(context);
        Message in = new DefaultMessage();
        in.setHeader(Headers.METHOD, Headers.POST);
        in.setBody("{\"name\":\"testSupplierName\",\"details\":\"testSupplierDetails\"}");
        exchange.setIn(in);

        Exchange response = template.send(RouteNames.SUPPLIER_ROUTE, exchange);
        Mockito.verify(supplierServiceMock).saveSupplier(supplierFormArgumentCaptor.capture());
        assertEquals(supplierFormArgumentCaptor.getValue(), new SupplierForm("testSupplierName", "testSupplierDetails"));
        assertEquals("{\"id\":44}", response.getIn().getBody());
    }



}