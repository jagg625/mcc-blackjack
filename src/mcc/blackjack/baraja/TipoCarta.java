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
public enum TipoCarta {
    
    AS("A", 1, 11), NUMERO_2("2", 2), NUMERO_3("3", 3), NUMERO_4("4", 4), NUMERO_5("5", 5), NUMERO_6("6", 6), NUMERO_7("7", 7), NUMERO_8("8", 8), NUMERO_9("9", 9), NUMERO_10("10", 10), J("J", 10), Q("Q", 10), K("K", 10);
    
    private final int [] posiblesValores;
    private final String nombre;
    private final Integer valorDefault;
    
    private TipoCarta(String nombre,int...posiblesValores){
        this.posiblesValores = posiblesValores;
        this.nombre = nombre;
        this.valorDefault = posiblesValores[0];
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int [] getPosiblesValores(){
        return this.posiblesValores;
    }
    
    public Integer getValorDefault(){
        return this.valorDefault;
    }
}
