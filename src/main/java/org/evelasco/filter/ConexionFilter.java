package org.evelasco.filter;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.evelasco.configs.MysqlConn;
import org.evelasco.interceptors.TransctionalJbdc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebFilter("/*")
public class ConexionFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /*try {
            Connection connection=conn;
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }*/
        try {
            //request.setAttribute("conn", connection);
            chain.doFilter(request, response);
            //connection.commit();
        } catch (RuntimeException e) {
           // connection.rollback();
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            e.printStackTrace();
        }

       /* } catch (SQLException e) {
            System.out.println("erro-filter.ul");
            e.printStackTrace();
        }*/
    }

}
