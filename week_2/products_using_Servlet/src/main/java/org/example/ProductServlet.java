package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.ProductManager;
import product.Product;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class ProductServlet extends HttpServlet {
    private final ProductManager productManager = new ProductManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{

        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        Product product;
        if("search".equals(action)){
            String searchName = request.getParameter("searchName");
           product = productManager.searchProduct(searchName);
           if(product !=null){
               out.print("Product name = " + product.getName() + ", Product price = " + product.getPrice());
               response.setStatus(200);
           }
           else {
               out.println("no items");
               response.setStatus(400);
           }
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        if("add".equals(action)){
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            Product product = new Product(name,price);
            try {
                productManager.addProduct(product);
            } catch (IllegalStateException ex){
                out.println(ex.getMessage());
                response.setStatus(400);
            }
            out.println("we add product called  "+product.getName()+"  ");
        }
        else if("update".equals(action)){
            String name = request.getParameter("name");
            double newPrice = Double.parseDouble(request.getParameter("New price"));
            String newName = request.getParameter("New name");
            try {
                productManager.updateProduct(name,newName,newPrice);
            } catch (IllegalStateException e) {
                out.println(e.getMessage());
                response.setStatus(400);
            }
        }
    }
    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        if("delete".equals(action)){
            String name = request.getParameter("name");
            Product product = productManager.searchProduct(name);
            productManager.deleteProduct(product);
            out.println("the product deleted ");
        }
    }



}
