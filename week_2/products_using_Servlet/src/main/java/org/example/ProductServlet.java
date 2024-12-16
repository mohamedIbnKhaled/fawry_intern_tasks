package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.PorductManager;
import product.Product;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class ProductServlet extends HttpServlet {
    private PorductManager porductManager ;
    @Override
    public void init() throws ServletException{
        porductManager = new PorductManager();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<b>hello world</b>");
        String action = request.getParameter("action");
        Product product;
        if("search".equals(action)){
            String searchName = request.getParameter("searchName");
           product = porductManager.searchProduct(searchName);
           if(product !=null){
               out.print("Product name = " + product.getName() + ", Product price = " + product.getPrice());
           }
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        String action = request.getParameter("action");
        if("add".equals(action)){
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            Product product = new Product(name,price);
            porductManager.addProduct(product);
        }else if("search".equals(action)){
            String searchName = request.getParameter("searchName");
            porductManager.searchProduct(searchName);
        }else if("update".equals(action)){
            String name = request.getParameter("name");
            double newPrice = Double.parseDouble(request.getParameter("New price"));
            String newName = request.getParameter("New name");
            porductManager.updateProduct(name,newName,newPrice);
        }
    }
    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
        String action = request.getParameter("action");
        if("delete".equals(action)){
            String name = request.getParameter("name");
            Product product = porductManager.searchProduct(name);
            porductManager.deleteProduct(product);
        }
    }



}
