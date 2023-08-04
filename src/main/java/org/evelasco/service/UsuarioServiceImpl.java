package org.evelasco.service;

import jakarta.inject.Inject;
import org.evelasco.configs.Service;
import org.evelasco.interceptors.TransctionalJpa;
import org.evelasco.model.entity.Usuario;
import org.evelasco.repository.RepositoryJpa;
import org.evelasco.repository.UsuarioRepository;
import java.util.List;

@Service
@TransctionalJpa
public class UsuarioServiceImpl implements ServiceUsuario{

    @Inject
    @RepositoryJpa
    private UsuarioRepository repository;


    @Override
    public List<Usuario> listar(){
        return null;
    }

    @Override
    public Usuario porId(Long id) {
        return null;
    }

    @Override
    public void guardar(Usuario usuario) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Usuario> porNombre(String username) {
        try {
            return repository.porNombre(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
