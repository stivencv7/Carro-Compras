package org.evelasco.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.evelasco.model.entity.Categoria;
import org.evelasco.model.entity.Producto;
import org.evelasco.service.ServiceProducto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet({"/producto-form"})
public class ProductoFormServlet extends HttpServlet {

    @Inject
    private ServiceProducto serviceProducto;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try{
            id=Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id=0L;
        }
        Producto producto=new Producto();
        producto.setCategoria(new Categoria());


        if(id>0){
            Optional<Producto>proOptional=serviceProducto.optionlPorId(id);
            if (proOptional.isPresent()){
                producto=proOptional.get();
            }



        }


        req.setAttribute("categorias",serviceProducto.listar());
        req.setAttribute("producto",producto);

        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre = req.getParameter("nombre");

        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha");
        Long categoria_id;
        try {
            categoria_id = Long.parseLong(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoria_id = 0L;
        }
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "ingrese el nombre del producto");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "ingrese el código sku del producto");
        }
        if (precio.equals(0)) {
            errores.put("precio", "ingrese el precio del producto");
        }
        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha", "ingrese la fecha de registro del producto");
        }
        if (categoria_id.equals(0L)) {
            errores.put("categoria", "seleccione la categoría del producto");
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha = null;
        }
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = null;
        }
        Producto producto=new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setSku(sku);
        producto.setFechaRegistro(fecha);

        Categoria c=new Categoria();
        c.setId(categoria_id);
        producto.setCategoria(c);

        if(!errores.isEmpty()){
            req.setAttribute("errores",errores);
            req.setAttribute("categorias",serviceProducto.listarCategoria());
            req.setAttribute("producto",producto);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
        }else{
            serviceProducto.guardar(producto);
            resp.sendRedirect(req.getContextPath()+"/productos");
        }

    }
}
