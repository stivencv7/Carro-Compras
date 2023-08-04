package org.evelasco.util;

import jakarta.enterprise.inject.Alternative;
import jakarta.security.enterprise.credential.Password;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Alternative
public class ConexionBD {
    private static final String URL="jdbc:mysql//localhost:3306/java_curso";
    private static final String USER="root";
    private static final String PASSWORD="ermen12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER, PASSWORD);
    }
}
