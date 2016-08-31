/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.Juegos.blackjack.jugadores;

import java.util.ArrayList;
import mcc.Juegosdecartas.Juegos.Jugador;
import mcc.Juegosdecartas.baraja.Carta;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class JugadorBlackJack extends Jugador {

    private final Integer dineroDisponible = 1000;

    public JugadorBlackJack(String nombre, Integer id) {
        super(nombre, id);
    }

    public Integer getDineroDisponible() {
        return dineroDisponible;
    }

    public ArrayList<Integer> getCombinacionesCartas() {

        ArrayList<Integer> combinaciones = new ArrayList<>();
        Integer suma;
        
        for (Carta c1 : cartas) {
            ArrayList<Integer> posiblesValoresCartaC1 = c1.getPosiblesValores();
            
            for (Integer posibleValorC1 : posiblesValoresCartaC1) {
                for (Carta c2 : cartas) {
                    
                    if (c2.equals(c1)) {
                        continue;
                    }
                    
                    ArrayList<Integer> posiblesValoresCartaC2 = c2.getPosiblesValores();
                    for (Integer posibleValorC2 : posiblesValoresCartaC2) {               
                        suma = posibleValorC1 + posibleValorC2;   
                        
                        if (combinaciones.contains(suma)) {
                            continue;
                        }
                        
                        combinaciones.add(suma);
                        suma = 0;
                    }
                }
            }
        }

        return combinaciones;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
