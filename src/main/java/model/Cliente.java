/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mariano
 */
public class Cliente {
    private Integer idCliente;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String dni;
    
    public Cliente(Integer idCliente, String nombre, String apellido1,
            String apellido2, String telefono, String dni) 
    {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.dni = dni;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getApellido1() {
        return this.apellido1;
    }
    
    public String getApellido2() {
        return this.apellido2;
    }
    
    public String getTelefono() {
        return this.telefono;
    }
    
    public String getDNI() {
        return this.dni;
    }
    public String getIdCliente()
    {
        return String.valueOf(this.idCliente);
    }
    @Override
    public String toString() {
        return this.getDNI() + "$" + this.getNombre() + "$" + 
                this.getApellido1() + "$" + this.getApellido2() + 
                "$" + this.getTelefono();
    }
}
