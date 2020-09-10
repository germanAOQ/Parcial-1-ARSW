package edu.eci.arsw.primefinder;

public class CalcularPrimosConcurrente {

	private int totalNumeros;
	private int[] asignado;
	private int totalThreads;
	private int divisor;
	private int mod;
	
	public CalcularPrimosConcurrente(int totalNumeros, int totalThreads) {
		this.totalNumeros = totalNumeros;
		this.totalNumeros = totalThreads;
		
		divisor = totalNumeros/totalThreads;
		mod = totalNumeros%totalThreads;
		
		int temp = divisor;
		for(int i = 0; i<totalThreads; i++) {
			if(i+1 == totalThreads) {
				this.asignado[i] = temp+mod;
			}else {
				this.asignado[i] = temp;
			}
		}
		
		
		
		
	}
	
	
	public int[] encontrarPrimos() {
		return null;
	}
	
	
}
