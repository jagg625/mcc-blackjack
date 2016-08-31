/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.Juegos.blackjack.jugadores;

import java.util.ArrayList;
import mcc.Juegosdecartas.Juegos.Jugador;
import mcc.Juegosdecartas.Juegos.blackjack.Apuesta;
import mcc.Juegosdecartas.baraja.Baraja;
import mcc.Juegosdecartas.baraja.Barajeable;
import mcc.Juegosdecartas.baraja.Carta;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class Croupier extends JugadorBlackJack {

    private final Baraja baraja;
    private  ArrayList<Apuesta> apuestas;

    public Croupier(Baraja baraja) {
        super("Croupier", 0);
        this.baraja = baraja;
    }
    
    public void setApuestas(ArrayList<Apuesta> apuestas){
        this.apuestas = apuestas;
    }

    public void barajarBaraja() {
        ((Barajeable) this.baraja).barajar();
    }

    public void partirBaraja() {
        ((Barajeable) this.baraja).partir();
    }

    public void darCarta(Jugador jugador) throws Exception {
        
        if (this.baraja.getMazoDeCartas().size() <= 0) {
            throw new Exception("No es posible dar una carta al jugador " + jugador.getNombre()+ " debido a que no quedan cartas en la baraja.");
        }
        
        Carta carta = this.baraja.getMazoDeCartas().remove(this.baraja.getMazoDeCartas().size() - 1 );
        jugador.setCarta(carta);
    }

    public void repartirCartas(ArrayList<JugadorBlackJack> jugadores) throws Exception{
        for (Jugador jugador : jugadores) {
            this.darCarta(jugador);
        }
        
        this.darCarta(this);
    }
}
