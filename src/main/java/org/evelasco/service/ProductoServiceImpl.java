package org.evelasco.service;

import jakarta.inject.Inject;
import org.evelasco.configs.Service;
import org.evelasco.interceptors.TransctionalJpa;
import org.evelasco.model.entity.Categoria;
import org.evelasco.model.entity.Producto;
import org.evelasco.repository.Repository;
import org.evelasco.repository.RepositoryJpa;

import java.util.List;
import java.util.Optional;


@Service
@TransctionalJpa
public class ProductoServiceImpl implements ServiceProducto {

    @Inject
    @RepositoryJpa
    private Repository<Producto> productoRepository;

    @Inject
    @RepositoryJpa
    private Repository<Categoria> repository;

    @Override
    public List<Producto> listar() {
        try {
            return productoRepository.listar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Producto porId(Long id) {
        try {
            return productoRepository.porId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            productoRepository.guardar(producto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(Long id) {
        try {
            productoRepository.eliminar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Producto> optionlPorId(Long id) {
        if (id == null) {
            id = 0L;
        }
        try {
            return Optional.ofNullable(productoRepository.porId(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repository.listar();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

}


