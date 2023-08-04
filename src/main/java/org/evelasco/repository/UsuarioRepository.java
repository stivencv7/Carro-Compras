package org.evelasco.repository;

import org.evelasco.model.entity.Usuario;

import java.util.List;


public interface UsuarioRepository extends Repository<Usuario> {
    List<Usuario> porNombre(String username) throws Exception;

}
