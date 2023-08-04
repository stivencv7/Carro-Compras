package org.evelasco.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.evelasco.service.LoginServiceImpl;
import org.evelasco.service.ServiceLogin;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceLogin serviceLogin=new LoginServiceImpl();
        Optional<String>user=serviceLogin.username(req);
        if(user.isPresent()){
            req.getSession().invalidate();
        }
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }
}
