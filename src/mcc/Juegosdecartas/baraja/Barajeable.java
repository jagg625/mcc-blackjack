/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.baraja;

import java.util.ArrayList;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public interface Barajeable {
    
    ArrayList<Carta> getMazoDeCartas();
    void barajar();
    void partir();
    void dividir();
}
