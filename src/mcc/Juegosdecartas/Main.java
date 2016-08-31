/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas;

import java.util.logging.Level;
import java.util.logging.Logger;
import mcc.Juegosdecartas.factories.JuegoFactory;
import mcc.Juegosdecartas.factories.TipoJuego;

/**
 *
 * @author Guerra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            JuegoDeCartas juego = JuegoFactory.crear(TipoJuego.BLACKJACK);
            juego.iniciar();
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
