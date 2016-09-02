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
    public static final Integer PUNTUACION_GANA = 21;
    public static final String divisor = "-----------------------------------------------------------------------------------------------------------------------------";
    private Croupier croupier;
    private ArrayList<JugadorBlackJack> jugadores;

    @Override
    public void iniciar() throws Exception {

        Integer numeroDeJugadores;
        Boolean otroJuego = false;

        try {

            imprimirMensaje("Inicia el juego BlackJack");
            /*Preguntamos por el numero de jugadores que participaran */
            numeroDeJugadores = obtenerNumeroJugadores();
            jugadores = obtenerJugadores(numeroDeJugadores);
            croupier = new Croupier();
            Baraja baraja = BarajaFactory.crear(TipoJuego.BLACKJACK);
            croupier.setBaraja(baraja);

            //Ciclo del juego
            do {

                imprimirMensaje("Apuestas de los jugadores");
                obtenerApuestas();
                imprimirMensaje("INFORMACIÓN DE LOS JUGADORES");
                imprimirJugadores();
                imprimirMensaje("Antes de iniciar el reparto de cartas el Croupier barajar las cartas");
                this.croupier.barajarBaraja();
                /*Se inicia el proceso para repartir cartas*/
                imprimirMensaje("Inicia el reparto de cartas a los jugadores...");
                repartirCartas();
                registrarQuienTieneBlackJack();
                imprimirMensaje("Las cartas fueron asignadas de la siguiente manera");
                imprimirCartasTodosJugadores();
                partidaPorJugador();
                imprimirMensaje("Las cartas de los jugadores quedaron de la siguiente manera ");
                imprimirCartasTodosJugadores();
                decidirGanadores();
                imprimirMensaje("RESULTADOS");
                imprimirJugadores();
                otroJuego = otroJuego();

                if (otroJuego) {
                    reiniciarDatosJugador();
                }

            } while (otroJuego);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Integer obtenerNumeroJugadores() {
        String numeroDeJugadores = "";
        Boolean esNumeroDeJugadoresCorrecto = Boolean.FALSE;
        do {

            System.out.print("Ingresa el numero de jugadores : ");
            numeroDeJugadores = pedirEntradaDelTeclado();

            if (numeroDeJugadores.compareTo("") != 0
                    && numeroDeJugadores.matches("\\d*")) {

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

        return jugadores;
    }

    private JugadorBlackJack obtenerDatosJugador(Integer numeroJugador) {
        JugadorBlackJack jugadorBlackJack = null;
        String nombreJugador = null;

        System.out.print("Ingrese el nombre del jugador # " + numeroJugador + " : ");
        nombreJugador = pedirEntradaDelTeclado();

        jugadorBlackJack = new JugadorBlackJack(nombreJugador, numeroJugador);

        return jugadorBlackJack;
    }

    private void repartirCartas() throws Exception {
        
        final Integer RONDAS = 2;
        Boolean esUltimaRonda = false;
        
        
        for (int ronda = 1; ronda <= RONDAS; ronda++) {
            esUltimaRonda = ronda == RONDAS;
            
            this.croupier.repartirCartas(jugadores, esUltimaRonda);
        }
    }

    public void imprimirNombresDeJugadores() {
        StringBuilder sb = new StringBuilder();

        for (JugadorBlackJack jugador : jugadores) {
            sb.append(divisor);
            sb.append(jugador.getNombre());
        }

        System.out.println(sb.toString());
    }

    private void imprimirCartasTodosJugadores() {
        imprimirEncabezadoCartas();
        for (JugadorBlackJack jugador : jugadores) {
            imprimirCartasJugador(jugador);
        }
        imprimirCartasJugador(this.croupier);
    }

    public void imprimirEncabezadoCartas() {
        System.out.println(divisor);
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %n", "Jugador", "Tipo carta", "Simbolo carta", "Color", "Valores");
        System.out.println(divisor);
    }

    private void imprimirMensaje(String mensaje) {
        System.out.println(divisor);
        System.out.println(mensaje);
        System.out.println(divisor);
    }

    private void partidaPorJugador() throws Exception {

        for (JugadorBlackJack jugador : jugadores) {

            String mensaje = "El siguiente turno es del jugador " + jugador.getNombre() + " sus cartas son : ";

            Boolean tieneBlackJack = this.croupier.tieneBlackJack(jugador);
            if (tieneBlackJack) {
                mensaje = "El jugador " + jugador.getNombre() + " tiene BlackJack sus cartas son : ";
            }

            imprimirMensaje(mensaje);
            imprimirEncabezadoCartas();
            imprimirCartasJugador(jugador);
            ArrayList<Integer> combinacionesCartas = this.croupier.getCombinacionesCartasJugador(jugador);
            System.out.println("");
            System.out.println("Combinaciones entre cartas : " + combinacionesCartas);

            if (tieneBlackJack) {
                continue;
            }

            Boolean continuar = true;

            do {

                Integer opcionJugador = obtenerOpcionJugador();
                switch (opcionJugador) {
                    case 1:

                        if (this.croupier.puedeJugadorTomarOtraCarta(jugador)) {
                            this.croupier.darCarta(jugador);
                        }
                        break;
                    case 2:
                        continuar = false;
                        break;
                }

                imprimirMensaje("Las cartas del jugador " + jugador.getNombre() + " quedaron de la siguiente manera");
                imprimirEncabezadoCartas();
                imprimirCartasJugador(jugador);
                combinacionesCartas = this.croupier.getCombinacionesCartasJugador(jugador);
                System.out.println("Combinaciones : " + combinacionesCartas);

                if (this.croupier.pierdeAutomaticamenteJugador(jugador) || this.croupier.jugadorTieneTodasCombinaciones21(jugador)) {
                    continuar = false;
                }

            } while (continuar);
        }

        this.croupier.logicaCroupierDebePedirCarta();

        imprimirMensaje("Las cartas del jugador " + this.croupier.getNombre() + " quedaron de la siguiente manera");
        imprimirEncabezadoCartas();
        imprimirCartasJugador(this.croupier);
        ArrayList<Integer> combinacionesCartas = this.croupier.getCombinacionesCartasJugador(this.croupier);
        System.out.println("");
        System.out.println("Combinaciones entre cartas : " + combinacionesCartas);

    }

    private String pedirEntradaDelTeclado() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private Integer obtenerOpcionJugador() {
        String opcionJugador = "";
        Boolean esOpcionCorrecta = Boolean.FALSE;
        do {

            imprimirMensaje("Elija una opción para seguir jugando ");
            System.out.print("1 - Otra carta 2 - Parar : ");
            opcionJugador = pedirEntradaDelTeclado();

            if (opcionJugador.compareTo("") != 0
                    && opcionJugador.matches("\\d*")) {

                Integer opcionJugadorInt = Integer.valueOf(opcionJugador);

                if (opcionJugadorInt >= 1 && opcionJugadorInt <= 3) {
                    esOpcionCorrecta = Boolean.TRUE;
                }
            }

        } while (!esOpcionCorrecta);

        return Integer.valueOf(opcionJugador);
    }

    private void imprimirCartasJugador(JugadorBlackJack jugador) {
        ArrayList<Carta> cartas = jugador.getCartas();

        for (Carta carta : cartas) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %n", jugador.getNombre(), carta.getTipoCarta().getNombre(), carta.getTipoSimbolo().getNombre(), carta.getTipoSimbolo().getColor(), carta.getPosiblesValores());
        }
    }

    private void obtenerApuestas() {
        Apuesta apuesta = null;

        for (JugadorBlackJack jugador : jugadores) {
            Integer apuestaJugador = obtenerApuestaJugador(jugador);

            apuesta = new Apuesta(jugador.getIdJugador(), apuestaJugador);
            this.croupier.setApuesta(apuesta);
        }
    }

    private Integer obtenerApuestaJugador(JugadorBlackJack jugador) {
        String apuestaJugador = "";
        Boolean esApuestaCorrecta = Boolean.FALSE;
        String mensaje = "Jugador " + jugador.getNombre() + " ingresa tu apuesta : ";

        do {
            System.out.print(mensaje);
            apuestaJugador = pedirEntradaDelTeclado();

            if (apuestaJugador.compareTo("") != 0
                    && apuestaJugador.matches("\\d*")) {

                Integer apuestaJugadorInt = Integer.valueOf(apuestaJugador);
                Integer disponibleJugador = jugador.getDineroDisponible();
                Integer disponibleMenosApuesta = disponibleJugador - apuestaJugadorInt;
                Integer totalApuestasJugadores = this.croupier.getTotalApuestasTodosLosJugadores() + apuestaJugadorInt;

                if (apuestaJugadorInt >= 0 && disponibleMenosApuesta >= 0 && totalApuestasJugadores <= this.croupier.getDineroDisponible()) {
                    esApuestaCorrecta = Boolean.TRUE;
                }

                mensaje = esApuestaCorrecta ? mensaje : "Jugador " + jugador.getNombre() + " ingresa tu apuesta correcta : ";
            }

        } while (!esApuestaCorrecta);

        return Integer.valueOf(apuestaJugador);
    }

    private Boolean otroJuego() {

        Boolean opcionCorrecta = false;
        Boolean otroJuego = false;
        String opcionOtroJuego;

        do {
            imprimirMensaje("Ingrese \"S\" para inicar otro juego, y \"Q\" para salir.");
            opcionOtroJuego = pedirEntradaDelTeclado();

            if (opcionOtroJuego.toUpperCase().compareTo("S") == 0) {
                otroJuego = Boolean.TRUE;
            } else if (opcionOtroJuego.toUpperCase().compareTo("Q") == 0) {
                otroJuego = Boolean.FALSE;
            }

        } while (opcionCorrecta);

        return otroJuego;
    }

    private void registrarQuienTieneBlackJack() {
        this.croupier.registrarQuienTieneBlackJack(jugadores);
    }

    private void imprimirJugadores() {

        StringBuilder sb = new StringBuilder();

        System.out.printf("%-20s %-20s %-20s %-20s %-20s %n", "Nombre", "Disponible", "Juegos ganados", "Juegos perdidos", "Juegos empate");
        System.out.println(divisor);

        for (JugadorBlackJack jugador : jugadores) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %n",
                    jugador.getNombre(),
                    jugador.getDineroDisponible(),
                    jugador.getJuegosGanados(),
                    jugador.getJuegosPerdidos(),
                    jugador.getJuegosEmpate());
        }

        System.out.printf("%-20s %-20s %-20s %-20s %-20s %n",
                this.croupier.getNombre(),
                this.croupier.getDineroDisponible(),
                this.croupier.getJuegosGanados(),
                this.croupier.getJuegosPerdidos(),
                this.croupier.getJuegosEmpate());

        System.out.println(divisor);

    }

    private void reiniciarDatosJugador() throws Exception {
        for (JugadorBlackJack jugador : jugadores) {
            jugador.borrarCartas();
        }

        this.croupier.borrarCartas();
        this.croupier.borrarIdsJugadoresConBlackJack();
        this.croupier.borrarApuesas();

        Baraja baraja = BarajaFactory.crear(TipoJuego.BLACKJACK);
        croupier.setBaraja(baraja);
    }

    private void decidirGanadores() {
        this.croupier.decidirGanadores(this.jugadores);
    }
}
