/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.blackjack.baraja;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public enum TipoSimbolo {
    CORAZON_ROJO("CORAZON", "ROJO"), CORAZON_NEGRO("CORAZON","NEGRO"), DIAMANTE("DIAMANTE", "ROJO"), TREBOL("TREBOL", "NEGRO");
    
    private final String nombre;
    private final String color;
    
    
    private TipoSimbolo(String nombre, String color){
        this.nombre = nombre;
        this.color = color;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getColor(){
        return color;
    }
}
