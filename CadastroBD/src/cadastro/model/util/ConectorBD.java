/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author leosc
 */
public class ConectorBD {
    private String url;
    private String usuario;
    private String senha;

    public ConectorBD(String url, String usuario, String senha) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    public PreparedStatement getPrepared(String sql) throws SQLException {
        Connection conn = getConnection();
        return conn.prepareStatement(sql);
    }

    public ResultSet getSelect(String sql) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }
    
    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Connection conn, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(conn);
    }  
}
