/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mariano
 */
public class Articulo {
    private final String codigoArt;
    private final String descripcionArt;
    private final String fabricanteArt;
    private final String categoriaArt;
    private final Integer precioArt;
    public Articulo(String codigoArt, String descripcionArt, String fabricanteArt,
            String categoriaArt, Integer precioArt) {
        this.codigoArt = codigoArt;
        this.descripcionArt = descripcionArt;
        this.categoriaArt = categoriaArt;
        this.fabricanteArt = fabricanteArt;
        this.precioArt = precioArt;
    }
    public String getCodigoArt() {
        return this.codigoArt;
    }
    public Integer getPrecioArt() {
        return this.precioArt;
    }
    public String getCatArt() {
        return this.categoriaArt;
    }
    public String getFabricanteArt() {
        return this.fabricanteArt;
    }
    public String getDescArt() {
        return this.descripcionArt;
    }
    @Override
    public String toString() {
        return (getCodigoArt() + "$" + getDescArt() + "$" + getFabricanteArt() + "$" +
                getCatArt() + "$" + getPrecioArt());
    }
}
