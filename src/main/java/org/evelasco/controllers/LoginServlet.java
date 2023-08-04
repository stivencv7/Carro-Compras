package org.evelasco.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.evelasco.model.entity.Usuario;
import org.evelasco.service.LoginServiceImpl;
import org.evelasco.service.ServiceLogin;
import org.evelasco.service.ServiceUsuario;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/login.html","/login"})
public class LoginServlet extends HttpServlet {

    @Inject
    private ServiceUsuario serviceUsuario;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceLogin serviceLogin=new LoginServiceImpl();
        Optional<String>username=serviceLogin.username(req);
        if(username.isPresent()){
            req.setAttribute("username",username.get());
            getServletContext().getRequestDispatcher("/vista.jsp").forward(req,resp);

        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username=req.getParameter("username");
        String password=req.getParameter("password");

        Optional<Usuario>usuario=serviceUsuario.porNombre(username)
                .stream()
                .filter(i->password.equals(i.getPassword()))
                .findAny();
        if(usuario.isPresent()){
            HttpSession session=req.getSession();
            session.setAttribute("username",username);
            resp.sendRedirect(req.getContextPath()+"/login.html");
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }


    }
}
