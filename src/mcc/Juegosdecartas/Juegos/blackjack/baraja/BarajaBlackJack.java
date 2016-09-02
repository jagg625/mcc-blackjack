/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.Juegos.blackjack.baraja;

import java.util.ArrayList;
import java.util.Collections;
import mcc.Juegosdecartas.baraja.Carta;
import mcc.Juegosdecartas.baraja.TipoCarta;
import mcc.Juegosdecartas.baraja.TipoSimbolo;
import mcc.Juegosdecartas.baraja.Barajeable;
import mcc.Juegosdecartas.baraja.Baraja;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class BarajaBlackJack extends Baraja implements Barajeable {

    private final ArrayList<Carta> mazoDeCartas;

    public BarajaBlackJack() {
        this.mazoDeCartas = generarMazoDeCartas();
    }

    @Override
    public ArrayList<Carta> getMazoDeCartas() {
        return this.mazoDeCartas;
    }

    /**
     *
     * @return
     */
    private ArrayList<Carta> generarMazoDeCartas() {

        ArrayList<Carta> nuevoMazoDeCartas = new ArrayList<>();
        TipoSimbolo[] tiposDeSimbolos = TipoSimbolo.values();
        TipoCarta[] tiposDeCartas = TipoCarta.values();
        Carta carta = null;

        for (TipoSimbolo tipoSimbolo : tiposDeSimbolos) {

            for (TipoCarta tipoCarta : tiposDeCartas) {

                carta = new Carta(tipoCarta, tipoSimbolo);
                
                getValoresCarta(carta, tipoCarta);

                nuevoMazoDeCartas.add(carta);

            }
        }

        return nuevoMazoDeCartas;
    }

    @Override
    public void barajar() {

        ArrayList<Carta> mazoDeCartas1 = getMazoDeCartas();

        Collections.shuffle(mazoDeCartas1);
    }

    @Override
    public void partir() {

    }

    @Override
    public void dividir() {

    }

    private void getValoresCarta(Carta carta, TipoCarta tipoCarta) {

        Integer valorDefault;
        ArrayList<Integer> posiblesValores;

        switch (tipoCarta) {
            case AS:

                valorDefault = 1;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);
                posiblesValores.add(11);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);
                break;
            case NUMERO_2:

                valorDefault = 2;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case NUMERO_3:

                valorDefault = 3;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case NUMERO_4:

                valorDefault = 4;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case NUMERO_5:

                valorDefault = 5;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case NUMERO_6:

                valorDefault = 6;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case NUMERO_7:

                valorDefault = 7;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case NUMERO_8:

                valorDefault = 8;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case NUMERO_9:

                valorDefault = 9;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case NUMERO_10:

                valorDefault = 10;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case J:

                valorDefault = 10;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case Q:

                valorDefault = 10;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;
            case K:

                valorDefault = 10;

                posiblesValores = new ArrayList<>();
                posiblesValores.add(valorDefault);

                carta.setValorActual(valorDefault);
                carta.setPosiblesValores(posiblesValores);

                break;

        }
    }
}
