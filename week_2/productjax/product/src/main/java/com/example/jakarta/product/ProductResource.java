package com.example.jakarta.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Map;


@Path("/product")
public class ProductResource {
    private final ProductManger productManger = new ProductManger();


    @POST
    public Response addProduct(Product product){
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("name") String name, Product product) {
        try {
            productManger.updateProduct(name, product.getName(), product.getPrice());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @DELETE
    @Path("/{name}")
    public Response deleteProduct(Product product) {
        productManger.deleteProduct(product);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchProducts(@QueryParam("name") String name) {
        Product products = productManger.searchProduct(name);
        return Response.status(Response.Status.OK).entity(products).build();
    }

}
