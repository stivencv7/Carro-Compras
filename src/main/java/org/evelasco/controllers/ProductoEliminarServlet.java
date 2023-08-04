package org.evelasco.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.evelasco.model.entity.Producto;
import org.evelasco.service.ServiceProducto;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/producto/eliminar")
public class ProductoEliminarServlet extends HttpServlet {
    @Inject
    private ServiceProducto serviceProducto;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id=Long.parseLong(req.getParameter("id"));

        Optional<Producto>producto=serviceProducto.optionlPorId(id);
        if (producto.isPresent()){
            serviceProducto.eliminar(id);
            resp.sendRedirect(req.getContextPath()+"/productos");
        }
    }
}
