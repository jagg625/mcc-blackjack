/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.blackjack.baraja;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class Baraja {

    private static Baraja instancia;
    private final ArrayList<Carta> mazoDeCartas;

    private Baraja() {
        this.mazoDeCartas = generarMazoDeCartas();
    }

    public static Baraja getInstancia() {
        
        if (instancia == null) {
            instancia = new Baraja();
        }
        
        return instancia;
    }
    
    public ArrayList<Carta> getMazoDeCartas(){
        return this.mazoDeCartas;
    }

    private ArrayList<Carta> generarMazoDeCartas() {

        ArrayList<Carta> nuevoMazoDeCartas = new ArrayList<>();
        TipoSimbolo[] tiposDeSimbolos = TipoSimbolo.values();
        TipoCarta[] tiposDeCartas = TipoCarta.values();
        Carta carta = null;

        for (TipoSimbolo tipoSimbolo : tiposDeSimbolos) {

            for (TipoCarta tipoCarta : tiposDeCartas) {

                carta = new Carta(tipoCarta, tipoSimbolo);

                nuevoMazoDeCartas.add(carta);

            }
        }

        return nuevoMazoDeCartas;
    }

    public void barajar() {
        
        ArrayList<Carta> mazoDeCartas1 = getMazoDeCartas();
        
        Collections.shuffle(mazoDeCartas1);
               
    }

    public void partir() {
        
    }
    
    public void dividir(){
    
    }
}
