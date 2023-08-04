package org.evelasco.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.evelasco.service.LoginServiceImpl;
import org.evelasco.service.ServiceLogin;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/carro-ver")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServiceLogin serviceLogin =new LoginServiceImpl();
        Optional<String>username=serviceLogin.username((HttpServletRequest) request);
        if(username.isPresent()){
            chain.doFilter(request,response);
        }else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no esta autorizado por favor inicie sesión");
        }
    }
}
