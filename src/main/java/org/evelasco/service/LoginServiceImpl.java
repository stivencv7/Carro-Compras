package org.evelasco.service;
import jakarta.servlet.http.HttpServletRequest;
import org.evelasco.configs.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements ServiceLogin{
    @Override
    public Optional<String> username(HttpServletRequest request) {

        if(request.getSession().getAttribute("username")!=null){

            String user=(String) request.getSession().getAttribute("username");
           return Optional.of(user) ;
        }
        return Optional.empty();
    }
}
