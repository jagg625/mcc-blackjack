/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.Juegos.blackjack;

import java.util.ArrayList;
import java.util.Scanner;
import mcc.Juegosdecartas.JuegoDeCartas;
import mcc.Juegosdecartas.Juegos.blackjack.jugadores.Croupier;
import mcc.Juegosdecartas.Juegos.blackjack.jugadores.JugadorBlackJack;
import mcc.Juegosdecartas.baraja.Baraja;
import mcc.Juegosdecartas.baraja.Carta;
import mcc.Juegosdecartas.factories.BarajaFactory;
import mcc.Juegosdecartas.factories.TipoJuego;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class BlackJack extends JuegoDeCartas {

    private final Integer NUM_MAX_JUGADORES = 7;
    private final Integer BLACKJACK = 21;
    private final String SEPARADOR_PALABRAS = " ********** ";
    private Croupier croupier;
    private ArrayList<JugadorBlackJack> jugadores;

    @Override
    public void iniciar() throws Exception {

        Integer numeroDeJugadores;
        Boolean otroJuego = false;
        System.out.println("**********BlackJack**********");

        try {

            //Ciclo del juego
            do {

                imprimirMensaje("Inicia el juego BlackJack");

                /*Preguntamos por el numero de jugadores que participaran */
                numeroDeJugadores = obtenerNumeroJugadores();
                jugadores = obtenerJugadores(numeroDeJugadores);

                Baraja baraja = BarajaFactory.crear(TipoJuego.BLACKJACK);
                croupier = new Croupier(baraja);

                ArrayList<Apuesta> apuestas = obtenerApuestas();
                this.croupier.setApuestas(apuestas);

                imprimirMensaje("Antes de iniciar el reparto de cartas el Crupier barajea la baraja");

                this.croupier.barajarBaraja();

                /*Se inicia el proceso para repartir cartas*/
                System.out.println("********** Inicia el reparto de cartas **********");

                System.out.println("********** Repartiendo cartas a los juegadores **********");

                //imprimirNombresDeJugadores();
                repartirCartas();

                System.out.println("********** Las cartas fueron asignadas de la siguiente manera **********");

                imprimirCartasTodosJugadores();

                partidaPorJugador();

                otroJuego = otroJuego();

            } while (otroJuego);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Integer obtenerNumeroJugadores() {
        String numeroDeJugadores = "";
        Boolean esNumeroDeJugadoresCorrecto = Boolean.FALSE;
        do {

            System.out.println(SEPARADOR_PALABRAS + "Ingresa el numero de jugadores" + SEPARADOR_PALABRAS);
            numeroDeJugadores = pedirEntradaDelTeclado();

            if (numeroDeJugadores.matches("\\d*")) {

                Integer numeroJugadoresInt = Integer.valueOf(numeroDeJugadores);

                if (numeroJugadoresInt >= 0 && numeroJugadoresInt <= NUM_MAX_JUGADORES) {
                    esNumeroDeJugadoresCorrecto = Boolean.TRUE;
                }
            }

        } while (!esNumeroDeJugadoresCorrecto);

        return Integer.valueOf(numeroDeJugadores);
    }

    private ArrayList<JugadorBlackJack> obtenerJugadores(Integer numeroDeJugadores) {

        ArrayList<JugadorBlackJack> jugadores = new ArrayList<>();
        JugadorBlackJack jugadorBlackJack = null;

        for (int i = 1; i <= numeroDeJugadores; i++) {
            jugadorBlackJack = obtenerDatosJugador(i);
            jugadores.add(jugadorBlackJack);
        }

        /* Se agrega el crupier como jugador ya que tambien es un jugador */
        //jugadores.add(this.croupier);
        return jugadores;
    }

    private JugadorBlackJack obtenerDatosJugador(Integer numeroJugador) {
        JugadorBlackJack jugadorBlackJack = null;
        String nombreJugador = null;

        System.out.println("Ingrese el nombre del jugador # " + numeroJugador);
        nombreJugador = pedirEntradaDelTeclado();

        jugadorBlackJack = new JugadorBlackJack(nombreJugador, numeroJugador);

        return jugadorBlackJack;
    }

    private void repartirCartas() throws Exception {
        for (int ronda = 1; ronda <= 2; ronda++) {
            this.croupier.repartirCartas(jugadores);
        }
    }

    public void imprimirNombresDeJugadores() {
        StringBuilder sb = new StringBuilder();

        for (JugadorBlackJack jugador : jugadores) {
            sb.append(SEPARADOR_PALABRAS);
            sb.append(jugador.getNombre());
        }

        System.out.println(sb.toString());
    }

    private void imprimirCartasTodosJugadores() {
        StringBuilder sb = new StringBuilder();

        for (JugadorBlackJack jugador : jugadores) {
            System.out.println(jugador.getNombre());
            imprimirCartasJugador(jugador);
        }

        System.out.println(this.croupier.getNombre());
        imprimirCartasJugador(this.croupier);
    }

    private void imprimirMensaje(String mensaje) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(mensaje);
        System.out.println("--------------------------------------------------------------------------------");
    }

    private void partidaPorJugador() {
        
        for (JugadorBlackJack jugador : jugadores) {
            
            ArrayList<Integer> combinacionesCartas = jugador.getCombinacionesCartas();
            
            if (combinacionesCartas.contains(BLACKJACK)) {
                 String mensaje = "El siguiente tu " + jugador.getNombre() + " sus cartas son : ";
            }
            
             String mensaje = "El siguiente turno es del jugador " + jugador.getNombre() + " sus cartas son : ";

            imprimirMensaje(mensaje);
            imprimirCartasJugador(jugador);

            System.out.println("Combinaciones : " + combinacionesCartas);

            Integer opcionJugador = obtenerOpcionJugador();

            switch (opcionJugador) {
                case 1:
                    
                    break;
                case 2:
                    break;
            }
        }
    }

    private String pedirEntradaDelTeclado() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private Integer obtenerOpcionJugador() {
        String opcionJugador = "";
        Boolean esOpcionCorrecta = Boolean.FALSE;
        do {
            imprimirMensaje("Elija una opcion : \n 1 - Otra carta \n 2 - Parar \n 3 - Ver cartas");
            opcionJugador = pedirEntradaDelTeclado();

            if (opcionJugador.matches("\\d*")) {

                Integer opcionJugadorInt = Integer.valueOf(opcionJugador);

                if (opcionJugadorInt >= 1 && opcionJugadorInt <= 3) {
                    esOpcionCorrecta = Boolean.TRUE;
                }
            }

        } while (!esOpcionCorrecta);

        return Integer.valueOf(opcionJugador);
    }

    private void imprimirCartasJugador(JugadorBlackJack jugador) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Carta> cartas = jugador.getCartas();

        for (Carta carta : cartas) {
            sb.append(carta.getTipoCarta().getNombre());
            sb.append("-");
            sb.append(carta.getTipoSimbolo().getNombre());
            sb.append("-");
            sb.append(carta.getTipoSimbolo().getColor());
            sb.append(" Posibles valores : ");
            sb.append(carta.getPosiblesValores());

            System.out.println(sb.toString());
            sb.delete(0, sb.length());
        }
    }

    private ArrayList<Apuesta> obtenerApuestas() {

        ArrayList<Apuesta> apuestas = new ArrayList<>();
        Apuesta apuesta = null;

        for (JugadorBlackJack jugador : jugadores) {
            Integer apuestaJugador = obtenerApuestaJugador(jugador);

            apuesta = new Apuesta(jugador.getIdJugador(), apuestaJugador);
            apuestas.add(apuesta);

        }

        return apuestas;
    }

    private Integer obtenerApuestaJugador(JugadorBlackJack jugador) {
        String apuestaJugador = "";
        Boolean esApuestaCorrecta = Boolean.FALSE;
        String mensaje = "Jugador " + jugador.getNombre() + " ingresa tu apuesta : ";

        do {
            System.out.println(mensaje);
            apuestaJugador = pedirEntradaDelTeclado();

            if (apuestaJugador.matches("\\d*")) {

                Integer apuestaJugadorInt = Integer.valueOf(apuestaJugador);
                Integer disponibleJugador = jugador.getDineroDisponible();
                Integer disponibleMenosApuesta = disponibleJugador - apuestaJugadorInt;

                if (apuestaJugadorInt >= 1 && disponibleMenosApuesta >= 0) {
                    esApuestaCorrecta = Boolean.TRUE;
                }

                mensaje = esApuestaCorrecta ? mensaje : "Jugador " + jugador.getNombre() + " ingresa tu apuesta correcta : ";
            }

        } while (!esApuestaCorrecta);

        return Integer.valueOf(apuestaJugador);
    }

    private Boolean otroJuego() {
        imprimirMensaje("Ingrese \"s\" para inicar otro juego, ingrese cualquier otra tecla para terminar.");
        String otroJuego = pedirEntradaDelTeclado();

        if (otroJuego.toUpperCase().compareTo("S") == 0) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
