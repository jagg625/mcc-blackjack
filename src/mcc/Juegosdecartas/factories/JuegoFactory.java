/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.factories;

import mcc.Juegosdecartas.Juegos.blackjack.BlackJack;
import mcc.Juegosdecartas.JuegoDeCartas;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class JuegoFactory {
    public static JuegoDeCartas crear(TipoJuego tipoJuego) throws Exception{
        
        JuegoDeCartas juego = null;
        
        switch(tipoJuego){
            case BLACKJACK:
                juego = new BlackJack();
                
            break;
            default:
                throw new Exception("No fue posible encontrar el juego solicitado.");
            
        }
        
        return juego;
    }
}
