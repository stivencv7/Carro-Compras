package org.evelasco.service;

import org.evelasco.model.entity.Categoria;
import org.evelasco.model.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface ServiceProducto{

    List<Producto> listar() ;
    Producto porId(Long id);
    void guardar(Producto producto) ;
    void eliminar(Long id) ;
    Optional<Producto>optionlPorId(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);

}
