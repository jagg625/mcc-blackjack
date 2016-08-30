/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.Juegosdecartas.baraja;

import java.util.ArrayList;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class Carta {
    
    private TipoCarta tipoCarta;
    private TipoSimbolo tipoSimbolo;
    private Boolean esOcupada;
    private Integer valorActual;
    private ArrayList<Integer> posiblesValores;
    
    public Carta(TipoCarta tipoCarta,
            TipoSimbolo tipoSimbolo){
       
        this.tipoCarta = tipoCarta;
        this.tipoSimbolo = tipoSimbolo;
        this.esOcupada = Boolean.FALSE;
    }

    public ArrayList<Integer> getPosiblesValores() {
        return posiblesValores;
    }

    public void setPosiblesValores(ArrayList<Integer> posiblesValores) {
        this.posiblesValores = posiblesValores;
    }

    public Integer getValorActual() {
        return valorActual;
    }

    public void setValorActual(Integer valorActual) {
        this.valorActual = valorActual;
    }
    
    public TipoCarta getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(TipoCarta tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public TipoSimbolo getTipoSimbolo() {
        return tipoSimbolo;
    }

    public void setTipoSimbolo(TipoSimbolo tipoSimbolo) {
        this.tipoSimbolo = tipoSimbolo;
    }
    
    public Boolean getEsOcupada() {
        return esOcupada;
    }

    public void setEsOcupada(Boolean esOcupada) {
        this.esOcupada = esOcupada;
    }
    
    @Override
    public String toString(){
                 
        StringBuilder sbCarta = new StringBuilder();
        
        sbCarta.append(" carta : ");
        sbCarta.append(this.getTipoCarta().getNombre());
        sbCarta.append(" simbolo : ");
        sbCarta.append(this.getTipoSimbolo().getNombre());
        sbCarta.append(" color : ");
        sbCarta.append(this.getTipoSimbolo().getColor());
        sbCarta.append(" esOcupada : ");
        sbCarta.append(this.getEsOcupada());
        
        return sbCarta.toString();
        
    }
}
