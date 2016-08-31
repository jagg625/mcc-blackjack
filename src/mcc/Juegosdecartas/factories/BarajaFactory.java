/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.factories;

import mcc.Juegosdecartas.Juegos.blackjack.baraja.BarajaBlackJack;
import mcc.Juegosdecartas.baraja.Baraja;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class BarajaFactory {
     public static Baraja crear(TipoJuego tipoJuego) throws Exception{
        
        Baraja baraja = null;
        
        switch(tipoJuego){
            case BLACKJACK:
                baraja = new BarajaBlackJack();
                
            break;
            default:
                throw new Exception("No fue posible encontrar el juego solicitado.");
            
        }
        
        return baraja;
    }
}
