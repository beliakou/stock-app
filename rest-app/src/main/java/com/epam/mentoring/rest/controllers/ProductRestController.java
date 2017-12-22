package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Headers;
import com.epam.mentoring.rest.Paths;
import com.epam.mentoring.rest.RouteNames;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

//@Service
@Path(Paths.PRODUCT_URI)
public class
ProductRestController {

    static {
        org.slf4j.MDC.put("app.name","rest-app");
    }

    Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @EndpointInject(uri = RouteNames.PRODUCT_ROUTE)
    private ProducerTemplate mainRoute;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        log.debug("GET: {}", Paths.PRODUCT_URI);
        final Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_ALL);
        return handleRequest(null, headers);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getProduct(@PathParam("id") Integer id) {
        log.debug("GET: {}/{}", Paths.PRODUCT_URI, id);
        final Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.GET_BY_ID);
        headers.put(Headers.ID, id);
        return handleRequest(null, headers);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProduct(String body) throws Exception {
        log.debug("POST: {}", Paths.PRODUCT_URI);
        final Map<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.POST);
        return handleRequest(body, headers);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProductIncome(@PathParam("id") Integer id) {
        log.info("DELETE: {}/{}", Paths.PRODUCT_URI, id);
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(Headers.METHOD, Headers.DELETE);
        return handleRequest(null, headers);
    }

    private Response handleRequest(Object body, Map<String, Object> headers) {
        String answer = (String) mainRoute.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }

}
