/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.Juegos.blackjack;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class Apuesta {
    
    private Integer idJugador;
    private Integer apuesta;
    
    public Apuesta(Integer idJugador, Integer apuesta){
        this.idJugador = idJugador;
        this.apuesta = apuesta;
    }

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public Integer getApuesta() {
        return apuesta;
    }

    public void setApuesta(Integer apuesta) {
        this.apuesta = apuesta;
    }  
}
