package org.evelasco.repository;

import jakarta.inject.Inject;
import org.evelasco.configs.MysqlConn;
import org.evelasco.configs.Repositorios;
import org.evelasco.model.entity.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repositorios
@RepositoryJdbc
public class CategoriaRepositoryImpl implements Repository<Categoria>{

    @Inject
    @MysqlConn
    private Connection conn;


    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria>categorias=new ArrayList<>();
        try(Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from categorias")){
            while (rs.next()){
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }

        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria=null;
        try(PreparedStatement stmt=conn.prepareStatement("select * from categorias where id=?")){
            stmt.setLong(1,id);
            try(ResultSet rs=stmt.executeQuery()) {
                if(rs.next()){
                    categoria=getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;
        if(categoria.getId()!=null && categoria.getId()>0){
            sql="update categorias set nombre=? where id=?";

        }else {
            sql="insert into productos (nombre)values(?)";
        }
        try(PreparedStatement statement=conn.prepareStatement(sql)) {
            statement.setString(1,categoria.getNombre());

            if(categoria.getId()!=null && categoria.getId()>0){
                statement.setLong(2,categoria.getId());
            }
            statement.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql="delete from categorias where id=?";
        try(PreparedStatement statement=conn.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria =new Categoria();
        categoria.setId(rs.getLong("id"));
        categoria.setNombre(rs.getString("nombre"));
        return categoria;
    }
}
