package org.evelasco.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    List<T> listar() throws Exception;
    T porId(Long id) throws Exception;
    void guardar(T t) throws Exception;
    void eliminar(Long id) throws Exception;
}
