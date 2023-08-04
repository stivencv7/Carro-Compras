package org.evelasco.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.evelasco.model.Carro;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {
    @Inject
    private Carro carro;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        eliminarItem(req,carro);
        updateCantidad(req,carro);
        resp.sendRedirect(req.getContextPath()+"/carro-ver");


    }

    private void updateCantidad(HttpServletRequest req, Carro carro) {
        Enumeration<String>nombresParameters=req.getParameterNames();
        while (nombresParameters.hasMoreElements()){
            String nombreParameter=nombresParameters.nextElement();
            if(nombreParameter.startsWith("cant_")){
                String id=nombreParameter.substring(5);
                String cantidad=req.getParameter("cant_"+id);
                carro.updateCantidad(id,cantidad);
            }
        }
    }

    public void eliminarItem(HttpServletRequest req, Carro carro){
        String[]deleteItems=req.getParameterValues("deleItems");
        System.out.println("eliminarItem");
        if(deleteItems!=null && deleteItems.length>0){
            System.out.println("if-eliminarItem");
            List<String>ids= Arrays.asList(deleteItems);
            carro.removeItem(ids);
        }
    }

}
