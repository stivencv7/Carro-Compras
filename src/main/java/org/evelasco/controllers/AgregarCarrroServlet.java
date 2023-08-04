package org.evelasco.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.evelasco.model.Carro;
import org.evelasco.model.ItemsCarro;
import org.evelasco.model.entity.Producto;
import org.evelasco.service.ServiceProducto;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarrroServlet extends HttpServlet {

    @Inject
    private Carro carro;
    @Inject
    private ServiceProducto service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id=Long.parseLong(req.getParameter("id"));
        Optional<Producto>producto=service.optionlPorId(id);

        if(producto.isPresent()){
            ItemsCarro item=new ItemsCarro(1,producto.get());
            carro.addItem(item);


        }
        resp.sendRedirect(req.getContextPath()+"/carro-ver");
    }
}
