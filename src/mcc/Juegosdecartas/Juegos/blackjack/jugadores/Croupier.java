/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.Juegos.blackjack.jugadores;

import java.util.ArrayList;
import mcc.Juegosdecartas.Juegos.Jugador;
import mcc.Juegosdecartas.Juegos.blackjack.Apuesta;
import mcc.Juegosdecartas.Juegos.blackjack.BlackJack;
import mcc.Juegosdecartas.baraja.Baraja;
import mcc.Juegosdecartas.baraja.Barajeable;
import mcc.Juegosdecartas.baraja.Carta;
import mcc.Juegosdecartas.baraja.TipoCarta;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class Croupier extends JugadorBlackJack {

    private Baraja baraja;
    private ArrayList<Apuesta> apuestas;
    private ArrayList<Integer> idsJugadoresConBlackJack;

    public Croupier() {
        super("Croupier", 0);
        this.idsJugadoresConBlackJack = new ArrayList<>();
        this.apuestas = new ArrayList<>();
    }

    public void setBaraja(Baraja baraja) {
        this.baraja = baraja;
    }

    public void borrarIdsJugadoresConBlackJack() {
        this.idsJugadoresConBlackJack = new ArrayList<>();
    }

    public ArrayList<Integer> getIdsJugadoresConBlack() {
        return this.idsJugadoresConBlackJack;
    }

    public void setApuesta(Apuesta apuesta) {
        this.apuestas.add(apuesta);
    }

    public void borrarApuesas() {
        this.apuestas = new ArrayList<>();
    }

    public Integer getApuestaJugador(JugadorBlackJack jugador) {

        Integer apuestaJugador = 0;

        for (Apuesta apuesta : this.getApuestas()) {
            if (apuesta.getIdJugador().compareTo(jugador.getIdJugador()) == 0) {
                apuestaJugador = apuesta.getApuesta();

                break;
            }
        }

        return apuestaJugador;
    }

    public Integer getTotalApuestasTodosLosJugadores() {

        Integer sumApuestas = 0;

        for (Apuesta apuesta : this.apuestas) {
            sumApuestas += apuesta.getApuesta();
        }

        return sumApuestas;
    }

    private ArrayList<Apuesta> getApuestas() {
        return this.apuestas;
    }

    public void barajarBaraja() {
        ((Barajeable) this.baraja).barajar();
    }

    public void partirBaraja() {
        ((Barajeable) this.baraja).partir();
    }

    public void darCarta(Jugador jugador) throws Exception {

        if (this.baraja.getMazoDeCartas().size() <= 0) {
            throw new Exception("No es posible dar una carta al jugador " + jugador.getNombre() + " debido a que no quedan cartas en la baraja.");
        }

        Carta carta = this.baraja.getMazoDeCartas().remove(this.baraja.getMazoDeCartas().size() - 1);
        jugador.setCarta(carta);
    }

    public void repartirCartas(ArrayList<JugadorBlackJack> jugadores, Boolean esUltimaRonda) throws Exception {
        for (Jugador jugador : jugadores) {
            this.darCarta(jugador);
        }

        if (esUltimaRonda) {
            this.darCarta(this);
        }
    }

    public void registrarBlackJack(JugadorBlackJack jugadorBlackJack) {
        ArrayList<Carta> cartas = jugadorBlackJack.getCartas();

        Boolean tieneAS = false;
        Boolean tiene10 = false;

        for (Carta carta : cartas) {

            if (carta.getTipoCarta() == TipoCarta.AS) {
                tieneAS = true;
            }

            switch (carta.getTipoCarta()) {
                case NUMERO_10:
                    tiene10 = true;
                    break;
                case J:
                    tiene10 = true;
                    break;
                case Q:
                    tiene10 = true;
                    break;
                case K:
                    tiene10 = true;
                    break;
            }
        }

        if (tieneAS && tiene10) {
            this.getIdsJugadoresConBlack().add(jugadorBlackJack.getIdJugador());
        }
    }

    public void registrarQuienTieneBlackJack(ArrayList<JugadorBlackJack> jugadores) {
        for (JugadorBlackJack jugador : jugadores) {
            registrarBlackJack(jugador);
        }
    }

    public ArrayList<Integer> getCombinacionesCartasJugador(JugadorBlackJack jugador) {

        ArrayList<Carta> cartasClone = (ArrayList<Carta>) jugador.getCartas().clone();
        Carta primerCarta = cartasClone.remove(0);

        ArrayList<Integer> posiblesValoresPrimerCarta = primerCarta.getPosiblesValores();
        ArrayList<Integer> combinacionesResultantes = (ArrayList<Integer>) posiblesValoresPrimerCarta.clone();

        for (Carta carta : cartasClone) {

            ArrayList<Integer> posiblesValoresCarta = carta.getPosiblesValores();
            combinacionesResultantes = combinacionesEntreArrays(combinacionesResultantes, posiblesValoresCarta);
        }

        return combinacionesResultantes;
    }

    public Boolean tieneBlackJack(JugadorBlackJack jugador) {
        if (this.getIdsJugadoresConBlack().contains(jugador.getIdJugador())) {
            return true;
        }

        return false;
    }

    public Boolean puedeJugadorTomarOtraCarta(JugadorBlackJack jugador) {
        ArrayList<Integer> combinacionesCartas = this.getCombinacionesCartasJugador(jugador);
        Boolean combinacionMenos21 = false;

        for (Integer combinacionesCarta : combinacionesCartas) {
            combinacionMenos21 = combinacionesCarta < BlackJack.PUNTUACION_GANA;

            if (combinacionMenos21) {
                break;
            }
        }

        return combinacionMenos21;
    }

    public Boolean jugadorTieneTodasCombinaciones21(JugadorBlackJack jugador) {
        ArrayList<Integer> combinacionesCartas = this.getCombinacionesCartasJugador(jugador);

        Boolean combinacion21 = false;

        for (Integer combinacionesCarta : combinacionesCartas) {
            combinacion21 = combinacionesCarta.compareTo(BlackJack.PUNTUACION_GANA) == 0;

            if (!combinacion21) {
                break;
            }
        }

        return combinacion21;
    }

    public Boolean pierdeAutomaticamenteJugador(JugadorBlackJack jugador) {

        ArrayList<Integer> combinacionesCartasJugador = this.getCombinacionesCartasJugador(jugador);
        Boolean combinacionesMayor21 = false;

        for (Integer combinacionCarta : combinacionesCartasJugador) {

            combinacionesMayor21 = combinacionCarta > BlackJack.PUNTUACION_GANA;

            if (!combinacionesMayor21) {
                break;
            }
        }

        return combinacionesMayor21;
    }

    public void logicaCroupierDebePedirCarta() throws Exception {

        Boolean debePedirOtraCarta = false;

        do {
            debePedirOtraCarta = croupierDebePedirOtraCarta();

            if (debePedirOtraCarta) {
                this.darCarta(this);
            }
        } while (debePedirOtraCarta);
    }

    public Boolean croupierDebePedirOtraCarta() {
        ArrayList<Integer> combinacionesCartasJugador = this.getCombinacionesCartasJugador(this);
        Boolean pedirOtraCarta = false;

        for (Integer combinacion : combinacionesCartasJugador) {
            if (combinacion <= 16) {
                pedirOtraCarta = true;

                break;
            }
        }

        return pedirOtraCarta;
    }

    public Integer getCombinacionMayorValido(ArrayList<Integer> combinaciones) {

        Integer mayor = 0;

        for (Integer combinacion : combinaciones) {

            if (combinacion < BlackJack.PUNTUACION_GANA + 1 && combinacion > mayor) {
                mayor = combinacion;
            }
        }

        return mayor;
    }

    public String jugadorGanaACroupier(JugadorBlackJack jugador) {

        ArrayList<Integer> combinacionesCartasCroupier = this.getCombinacionesCartasJugador(this);
        Integer combinacionMayorValidaCroupier = this.getCombinacionMayorValido(combinacionesCartasCroupier);

        if (this.pierdeAutomaticamenteJugador(jugador)) {
            return "PIERDE";
        }

        if (this.tieneBlackJack(jugador)) {
            return "GANA_BLACKJACK";
        }

        ArrayList<Integer> combinacionesJugador = this.getCombinacionesCartasJugador(jugador);
        Integer combinacionMayorValidoJugador = this.getCombinacionMayorValido(combinacionesJugador);

        if (combinacionMayorValidoJugador > combinacionMayorValidaCroupier) {
            return "GANA_NORMAL";
        }

        if (combinacionMayorValidoJugador.compareTo(combinacionMayorValidaCroupier) == 0) {
            return "EMPATE";
        }

        return "PIERDE";
    }

    public void decidirGanadores(ArrayList<JugadorBlackJack> jugadores) {
        for (JugadorBlackJack jugador : jugadores) {

            String jugadorGanaACroupier = this.jugadorGanaACroupier(jugador);
            Integer apuestaJugador = this.getApuestaJugador(jugador);

            switch (jugadorGanaACroupier) {
                case "GANA_BLACKJACK":

                    jugador.setJuegosGanados(jugador.getJuegosGanados() + 1);
                    jugador.setDineroDisponible(jugador.getDineroDisponible() + ((apuestaJugador) * 2));

                    this.setJuegosPerdidos(this.getJuegosPerdidos() + 1);
                    this.setDineroDisponible(this.getDineroDisponible() - apuestaJugador);

                    System.out.println(BlackJack.divisor);
                    System.out.println("Jugador " + jugador.nombre + " ha ganado");
                    System.out.println(BlackJack.divisor);

                    break;
                case "GANA_NORMAL":

                    jugador.setJuegosGanados(jugador.getJuegosGanados() + 1);
                    jugador.setDineroDisponible(jugador.getDineroDisponible() + apuestaJugador);

                    this.setJuegosPerdidos(this.getJuegosPerdidos() + 1);
                    this.setDineroDisponible(this.getDineroDisponible() - apuestaJugador);

                    System.out.println(BlackJack.divisor);
                    System.out.println("Jugador " + jugador.nombre + " ha ganado");
                    System.out.println(BlackJack.divisor);

                    break;
                case "PIERDE":

                    jugador.setJuegosPerdidos(jugador.getJuegosPerdidos() + 1);
                    jugador.setDineroDisponible(jugador.getDineroDisponible() - apuestaJugador);

                    this.setJuegosGanados(this.getJuegosGanados() + 1);
                    this.setDineroDisponible(this.getDineroDisponible() + apuestaJugador);

                    System.out.println(BlackJack.divisor);
                    System.out.println("Jugador " + jugador.nombre + " ha perdido");
                    System.out.println(BlackJack.divisor);

                    break;
                default:

                    jugador.setJuegosEmpate(jugador.getJuegosEmpate() + 1);
                    this.setJuegosEmpate(this.getJuegosEmpate() + 1);
                    break;
            }
        }
    }
}
