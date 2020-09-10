package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.List;

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
	
	
	public List<Integer> encontrarPrimos() throws InterruptedException {
		ArrayList<Integer> primosEncontrados = new ArrayList<Integer>();
		Thread[] hilos = new PrimoThread[totalThreads];
		
		int acum = 0;
		for(int i = 0; i<totalThreads; i++) {
			hilos[i] = new PrimoThread(acum, asignado[i]+acum, primosEncontrados);
			hilos[i].start();
			acum+= asignado[i];
		}
		
		for(int i = 0; i<totalThreads; i++) {
			hilos[i].join();
		}
		
		return primosEncontrados;
	}
	
	
}
