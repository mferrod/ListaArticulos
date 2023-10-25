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
    public int getCountRows(String tabla) {
        int r;
        
        r = 0;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) as total FROM " + tabla);
            while (rs.next()) r = rs.getInt("total");
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (r);
    }
    public ArrayList<String[]> getArticulos() {
        this.clearArrayList();
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
    public ArrayList<String[]> getClientes() {
        this.clearArrayList();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM clientes");
            
            while (rs.next()) {
                String[] astr = new String[6];
                
                astr[0] = String.valueOf(rs.getInt("idcliente"));
                astr[1] = rs.getString("nombre");
                astr[2] = rs.getString("apellido1");
                astr[3] = rs.getString("apellido2");
                astr[4] = rs.getString("telefono");
                astr[5] = rs.getString("dni");
                
                arrayDevuelvo.add(astr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (arrayDevuelvo);
    }
    private void clearArrayList() {
        arrayDevuelvo.clear();
    }
    public void addCliente(String nombre, String ape1,
            String ape2, String telef, String dni) {
        try {
            pst = conn.prepareStatement("INSERT INTO clientes (nombre, apellido1, apellido2, telefono, dni)"
                    + " VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, nombre);
            pst.setString(2, ape1);
            pst.setString(3, ape2);
            pst.setString(4, telef);
            pst.setString(5, dni);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void removeCliente(String idcl) {
        try {
            pst = conn.prepareStatement("DELETE FROM clientes WHERE idcliente=?");
            pst.setInt(1, Integer.parseInt(idcl));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifyCliente(String dni, String nombre, String ape1,
            String ape2, String telef, String idcli) {
        try {
            pst = conn.prepareStatement("UPDATE clientes SET nombre=?, apellido1=?,"
                    + " apellido2=?, telefono=?, dni=? WHERE idcliente=?");
            pst.setString(1, nombre);
            pst.setString(2, ape1);
            pst.setString(3, ape2);
            pst.setString(4, telef);
            pst.setString(5, dni);
            pst.setInt(6, Integer.parseInt(idcli));
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
