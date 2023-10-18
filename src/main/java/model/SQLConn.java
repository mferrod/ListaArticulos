/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mariano
 */
public class SQLConn {
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private final String URL = "jdbc:mysql://localhost/tiendainformatica";
    private final String usuario = "root";
    private final String password = "";    
    private ArrayList<String[]> arrayDevuelvo = new ArrayList();
    
    public SQLConn() {
        this.connectToDB();
    }
    
    private void connectToDB() {
        try {
            conn = DriverManager.getConnection(URL, usuario, password);
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnectionToDB() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getCountRowsArticulos() {
        int r;
        
        r = 0;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) as total FROM articulos");
            while (rs.next()) r = rs.getInt("total");
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (r);
    }
    public ArrayList<String[]> getArticulos() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM articulos");
            while (rs.next()) {
                String[] c = new String[5];
                c[0] = rs.getString("codigo");
                c[1] = rs.getString("descripcion");
                c[2] = rs.getString("fabricante");
                c[3] = rs.getString("categoria");
                c[4] = rs.getString("pvp");
                arrayDevuelvo.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (arrayDevuelvo);
    }
    public void addArticulos(String descripcion, String fabricante,
            String categoria, String pvp) {
        try {
            pst = conn.prepareStatement("INSERT INTO articulos (descripcion, fabricante, categoria, pvp)"
                    + " VALUES (?, ?, ?, ?)");
            pst.setString(1, descripcion);
            pst.setString(2, fabricante);
            pst.setString(3, categoria);
            pst.setInt(4, Integer.parseInt(pvp));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void removeArticulos(String codigo) {
        try {
            pst = conn.prepareStatement("DELETE FROM articulos WHERE codigo=?");
            pst.setInt(1, Integer.parseInt(codigo));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifyArticulos(String codigo, String descripcion, String fabricante,
            String categoria, String pvp) {
        try {
            pst = conn.prepareStatement("UPDATE articulos SET descripcion=?, fabricante=?,"
                    + " categoria=?, pvp=? WHERE codigo=?");
            pst.setString(1, descripcion);
            pst.setString(2, fabricante);
            pst.setString(3, categoria);
            pst.setFloat(4, Integer.parseInt(pvp));
            pst.setInt(5, Integer.parseInt(codigo));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
