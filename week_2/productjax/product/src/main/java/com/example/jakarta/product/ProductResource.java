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
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(@QueryParam("name") String name , @QueryParam("price")double price){
        Product product = new Product(name,price);
        try {
            productManger.addProduct(product);
        }catch (IllegalStateException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@QueryParam("newName") String newName, @QueryParam("name") String name, @QueryParam("price") double price)
    {

        try {
          Product product=  productManger.searchProduct(newName);
            productManger.updateProduct(name,newName,price);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @DELETE
    @Path("/delete")
    public Response deleteProduct(@QueryParam("name")String name) {
        Product product ;
        try {
          product=  productManger.searchProduct(name);
        }catch (IllegalStateException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        productManger.deleteProduct(product);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchProducts(@QueryParam("name") String name) {
        Product products = productManger.searchProduct(name);
        return Response.status(Response.Status.OK).entity(products).build();
    }

}
