package org.evelasco.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.evelasco.model.entity.Producto;
import org.evelasco.service.LoginServiceImpl;
import org.evelasco.service.ServiceLogin;
import org.evelasco.service.ServiceProducto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    @Inject
    private ServiceProducto serviceProducto;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Producto>productos=serviceProducto.listar();

        ServiceLogin serviceLogin=new LoginServiceImpl();

        Optional<String>username=serviceLogin.username(req);

        req.setAttribute("username",username);
        req.setAttribute("productos",productos);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);

    }
}
