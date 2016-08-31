/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.Juegos;

import java.util.ArrayList;
import mcc.Juegosdecartas.baraja.Carta;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public abstract class Jugador {

    public String nombre;
    public ArrayList<Carta> cartas;
    private Integer id;
    
    public Jugador(String nombre, Integer id){
        this.nombre = nombre;
        this.cartas = new ArrayList<>();
        this.id = id;
    }
    
    public Integer getIdJugador(){
        return this.id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCarta(Carta carta) {
        this.cartas.add(carta);
    }
}
