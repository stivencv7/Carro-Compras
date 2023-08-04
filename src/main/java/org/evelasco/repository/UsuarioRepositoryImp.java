package org.evelasco.repository;

import jakarta.inject.Inject;
import org.evelasco.configs.MysqlConn;
import org.evelasco.configs.Repositorios;
import org.evelasco.model.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repositorios
@RepositoryJdbc
public class UsuarioRepositoryImp implements UsuarioRepository {

    @Inject
    @MysqlConn
    private Connection conn;


    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from usuarios")) {
            while (rs.next()) {
                Usuario u = getUsuario(rs);
                usuarios.add(u);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from usuarios where id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0) {
            sql = "update usuario set username=?,password=?,email=? where id=?";

        } else {
            sql = "insert into usuario (username,password,email)values(?,?,?)";
        }

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getEmail());

            if (usuario.getId() != null && usuario.getId() > 0) {
                statement.setLong(4, usuario.getId());
            }

            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from usuarios where id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setEmail(rs.getString("email"));
        return u;
    }

    @Override
    public List<Usuario> porNombre(String username) {

        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("select * from usuarios where username=?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getLong("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setEmail(rs.getString("email"));
                    usuarios.add(u);
                }
            }
            return usuarios;
        } catch (Exception e) {
            System.out.println("erro");
            throw new RuntimeException(e);
        }

    }
}

