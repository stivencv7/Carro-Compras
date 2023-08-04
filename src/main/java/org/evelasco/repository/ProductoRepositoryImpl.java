package org.evelasco.repository;

import jakarta.inject.Inject;
import org.evelasco.configs.MysqlConn;
import org.evelasco.configs.Repositorios;
import org.evelasco.model.entity.Categoria;
import org.evelasco.model.entity.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



@Repositorios
@RepositoryJdbc
public class ProductoRepositoryImpl implements Repository<Producto> {

    @Inject
    @MysqlConn
    private Connection conn;




    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto>productos=new ArrayList<>();
        try(Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select p.*,c.nombre as categoria from productos as p " +
                    "inner join categorias as c on(p.categoria_id=c.id)")){
            while (rs.next()){
                Producto producto = getProducto(rs);
                productos.add(producto);
            }

        }
        return productos;
    }


    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto=null;
        try(PreparedStatement stmt=conn.prepareStatement("select p.*,c.nombre as categoria from productos as p " +
                "inner join categorias as c on(p.categoria_id=c.id) where p.id=?")){
            stmt.setLong(1,id);
            try(ResultSet rs=stmt.executeQuery()) {
                if(rs.next()){
                    producto=getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if(producto.getId()!=null && producto.getId()>0){
            sql="update productos set nombre=?,precio=?,sku=?,categoria_id=? where id=?";

        }else {
            sql="insert into productos (nombre,precio,sku,categoria_id,fecha_registro)values(?,?,?,?,?)";
        }
        try(PreparedStatement statement=conn.prepareStatement(sql)) {
            statement.setString(1,producto.getNombre());
            statement.setInt(2,producto.getPrecio());
            statement.setString(3,producto.getSku());
            statement.setLong(4,producto.getCategoria().getId());

            if(producto.getId()!=null && producto.getId()>0){
                statement.setLong(5,producto.getId());
            }else {
                statement.setDate(5,Date.valueOf(producto.getFechaRegistro()));
            }

            statement.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql="delete from productos where id=?";
        try(PreparedStatement statement=conn.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto producto=new Producto();
        producto.setId(rs.getLong("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getInt("precio"));
        Categoria c=new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        producto.setCategoria(c);
        producto.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        producto.setSku(rs.getString("sku"));
        return producto;
    }
}
