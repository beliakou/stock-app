package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.Paths;
import io.swagger.annotations.*;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Api(value = "Product service", description = "Operations on products")
@Path(Paths.PRODUCT_URI)
public class
ProductRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve list of all products")
    @ApiResponses(
            @ApiResponse(code = 200, message = "List of products returned")
    )
    public Response getAllProducts() {
        return null;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @ApiOperation(value = "Retrieve product by id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Product found")
    )
    public Response getProductById(
            @ApiParam(value = "Product's id", required = true)
            @PathParam("id") Integer id) {
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/quant")
    public Response getAllProductsWithQuantities(){
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Saves product")
    @ApiResponses(
            @ApiResponse(code = 201, message = "Product saved")
    )
    public Response postProduct(@ApiParam(value = "Product representation e.g.: {'name':'testProduct','price':100,'productTypeId':1}") String body) throws Exception {
        return null;
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Deletes product by id")
    @ApiResponses(
            @ApiResponse(code = 200, message = "Product found")
    )
    public Response deleteProduct(
            @ApiParam(value = "Product's id", required = true)
            @PathParam("id") Integer id) {
        return null;
    }


}