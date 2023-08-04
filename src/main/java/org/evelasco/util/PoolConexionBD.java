package org.evelasco.util;

import jakarta.enterprise.inject.Alternative;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Alternative
public class PoolConexionBD {

    public static Connection getConnection() throws SQLException, NamingException {
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/ConexionBD");
        return ds.getConnection();
    }
}
