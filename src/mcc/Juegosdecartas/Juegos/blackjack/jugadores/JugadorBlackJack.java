/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.Juegos.blackjack.jugadores;

import java.util.ArrayList;
import mcc.Juegosdecartas.Juegos.Jugador;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class JugadorBlackJack extends Jugador {

    private Integer dineroDisponible = 1000;

    public JugadorBlackJack(String nombre, Integer id) {
        super(nombre, id);
        
        this.setJuegosGanados(0);
        this.setJuegosPerdidos(0);
        this.setJuegosEmpate(0);
    }

    public Integer getDineroDisponible() {
        return dineroDisponible;
    }
    
    public void setDineroDisponible(Integer dineroDisponible){
        this.dineroDisponible = dineroDisponible;
    }

    public ArrayList<Integer> combinacionesEntreArrays(ArrayList<Integer> posiblesValores1, ArrayList<Integer> posiblesValores2) {

        ArrayList<Integer> arrayResultante = new ArrayList<>();
        Integer suma = 0;

        for (Integer valor1 : posiblesValores1) {

            for (Integer valor2 : posiblesValores2) {
                suma = valor1 + valor2;

                if (!arrayResultante.contains(suma)) {
                    arrayResultante.add(suma);
                }

                suma = 0;
            }
        }

        return arrayResultante;
    }

    @Override
    public String toString() {
        return this.getIdJugador() + this.getNombre() + this.getDineroDisponible();
    }
}
