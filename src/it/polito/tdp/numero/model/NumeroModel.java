package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

import javax.xml.ws.Holder;

public class NumeroModel {

	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	public NumeroModel() {
		inGioco = false;
	}
	
	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
		this.inGioco = true;
	}
	/**
	 * Metodo per effettuare un tentativo
	 * @param t è il tentativo
	 * 
	 * @return 1 se il tentativo è troppo alto -1 se è troppo basso ,0 se l'utente ha indovinato
	 */
	public int tentativo(int t) {
		
		//controllo se la partita è in corso
		if (!inGioco) {
			throw new IllegalStateException("La partita è terminata");
		}
		
		//controllo sel'input è nel range corretto
		if (!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("Devi inserire un numero "+
		"tra %d e %d",1,NMAX));
		}
		
		this.tentativiFatti++;
		if (this.tentativiFatti == this.TMAX) {
			//la partita è finita perchè ho esaurito i tentativi
			this.inGioco = false;
		}
		
		//gestisci tentativo
		if (t==segreto) {
			// ho indovinato
			this.inGioco = false;
			return 0;
		}
		
		if (t > this.segreto) {
			return 1;
		}
		
		
		return -1;
	
		
		
	}
	
	public boolean tentativoValido(int t) {
		if (t<1 || t>NMAX) {
			return false;
		}else {
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
