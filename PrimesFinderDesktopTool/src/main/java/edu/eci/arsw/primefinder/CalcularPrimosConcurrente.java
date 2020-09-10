package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CalcularPrimosConcurrente {

	private int totalNumeros;
	private int[] asignado;
	private int totalThreads;
	private int divisor;
	private int mod;
	
	public CalcularPrimosConcurrente(int totalNumeros, int totalThreads) throws InterruptedException {
		this.totalNumeros = totalNumeros;
		this.totalThreads = totalThreads;
		this.asignado = new int[totalThreads];
		
		
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
	
	
	public CopyOnWriteArrayList<Integer> encontrarPrimos() throws InterruptedException {
		//ArrayList<Integer> primosEncontrados = new ArrayList<Integer>();
		CopyOnWriteArrayList<Integer> primosEncontrados = new CopyOnWriteArrayList<Integer>();
		Thread[] hilos = new PrimoThread[totalThreads];
		
		
		int acum = 1;
		for(int i = 0; i<totalThreads; i++) {
			hilos[i] = new PrimoThread(acum, asignado[i]+acum, primosEncontrados);
			hilos[i].start();
			acum+= asignado[i];
		}
		
		for(int i = 0; i<totalThreads; i++) {
			hilos[i].join();
		}
		
		Collections.sort(primosEncontrados);
		
		return primosEncontrados;
	}
	
	
}
