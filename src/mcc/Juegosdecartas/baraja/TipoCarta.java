/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.baraja;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public enum TipoCarta {
    
    AS("A"), NUMERO_2("2"), NUMERO_3("3"), NUMERO_4("4"), NUMERO_5("5"), NUMERO_6("6"), NUMERO_7("7"), NUMERO_8("8"), NUMERO_9("9"), NUMERO_10("10"), J("J"), Q("Q"), K("K");
    
    private final String nombre;
    
    private TipoCarta(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
}
