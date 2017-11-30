package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.service.ISupplierService;
import com.epam.mentoring.test.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SupplierControllerTest {

    MockMvc mockMvc;

    @Autowired
    ISupplierService supplierServiceMock;

    ArgumentCaptor<Supplier> supplierArgumentCaptor;
    ArgumentCaptor<SupplierForm> supplierFormArgumentCaptor;

    @Autowired
    WebApplicationContext webApplicationContext;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.supplierArgumentCaptor = ArgumentCaptor.forClass(Supplier.class);
        this.supplierFormArgumentCaptor = ArgumentCaptor.forClass(SupplierForm.class);
    }

    @Test
    public void getAllSuppliersTest() throws Exception {
        List<Supplier> expectedSuppliers = TestData.suppliers();
        mockMvc.perform(get("/supplier"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(expectedSuppliers.size())))
                .andExpect(jsonPath("$[0].name", is(expectedSuppliers.get(0).getName())));
        verify(supplierServiceMock, times(1)).getAllSuppliers();
    }

    @Test
    public void saveSupplierFormTest() throws Exception {
        SupplierForm supplierForm = new SupplierForm("testSupplierName", "testSupplierDetails");
        mockMvc.perform(post("/supplier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplierForm)))
                .andExpect(status().isCreated());
        verify(supplierServiceMock, times(1)).saveSupplier(Matchers.any(SupplierForm.class));
        verify(supplierServiceMock).saveSupplier(supplierFormArgumentCaptor.capture());
        assertEquals(supplierForm, supplierFormArgumentCaptor.getValue());
    }

    @Test
    public void saveSupplierFormNullFieldTest() throws Exception {
        SupplierForm supplierForm = new SupplierForm(null, "testSupplierDetails");
        mockMvc.perform(post("/supplier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplierForm)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['error']", CoreMatchers.is("Object validation failed")));
    }

}