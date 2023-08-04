package org.evelasco.service;

import org.evelasco.model.entity.Usuario;
import java.sql.SQLException;
import java.util.List;


public interface ServiceUsuario{
    List<Usuario> listar() ;
    Usuario porId(Long id) ;
    void guardar(Usuario usuario) ;
    void eliminar(Long id);
    List<Usuario> porNombre(String username);
}
