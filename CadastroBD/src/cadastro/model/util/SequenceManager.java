/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leosc
 */
public class SequenceManager {
     private ConectorBD conector;

    public SequenceManager(ConectorBD conector) {
        this.conector = conector;
    }

    public int getValue(String sequenceName) {
        int nextValue = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conector.getConnection();
            String query = "SELECT NEXT VALUE FOR " + sequenceName + " AS NextValue";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                nextValue = rs.getInt("NextValue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close(rs);
            conector.close(stmt);
            conector.close(conn);
        }

        return nextValue;
    }

   
    
}
