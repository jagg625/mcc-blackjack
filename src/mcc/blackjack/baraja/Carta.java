/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc.blackjack.baraja;

/**
 *
 * @author jesusarmandoguerragarcia
 */
public class Carta {
    
    private TipoCarta tipoCarta;
    private TipoSimbolo tipoSimbolo;
    private Integer valorActual;
    private Boolean esOcupada;
    
    public Carta(TipoCarta tipoCarta,
            TipoSimbolo tipoSimbolo){
       
        this.tipoCarta = tipoCarta;
        this.tipoSimbolo = tipoSimbolo;
        this.valorActual = tipoCarta.getValorDefault();
        this.esOcupada = Boolean.FALSE;
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

    public Integer getValorActual() {
        return valorActual;
    }

    public void setValorActual(Integer valorActual) {
        this.valorActual = valorActual;
    }

    public Boolean getEsOcupada() {
        return esOcupada;
    }

    public void setEsOcupada(Boolean esOcupada) {
        this.esOcupada = esOcupada;
    }
    
   public Boolean tienePosiblesValores(){
        
        if (this.tipoCarta.getPosiblesValores().length < 2) {
            return false;
        }
        
        return true;
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
        sbCarta.append(" valorActual : ");
        sbCarta.append(this.getValorActual());
        sbCarta.append(" posiblesValores : ");
        
        Integer posiblesValoresIteracion = 1;
        int[] posiblesValores = this.getTipoCarta().getPosiblesValores();
        for (Integer posiblesValore : posiblesValores) {
            
            sbCarta.append(posiblesValore);
            
            if (posiblesValoresIteracion != posiblesValores.length) 
                sbCarta.append(",");
            
            posiblesValoresIteracion++;    
        }
        
        sbCarta.append(" esOcupada : ");
        sbCarta.append(this.getEsOcupada());
        
        return sbCarta.toString();
        
    }
}
