package edu.eci.arsw.primefinder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.eci.arsw.math.MathUtilities;

public class PrimoThread extends Thread{
	
	private int A;
	private int B;
	private CopyOnWriteArrayList<Integer> primosEncontrados;
	MathUtilities mu;

	
	
	public PrimoThread(int A, int B, CopyOnWriteArrayList<Integer> primosEncontrados) {
		this.A = A;
		this.B = B;
		this.primosEncontrados = primosEncontrados;
		this.mu = new MathUtilities();
	}
	
	public void run() {
		for(int i = A; i<B; i++) {
			BigInteger bi = BigInteger.valueOf(i);
			if(mu.isPrime(bi)) {
				primosEncontrados.add(i);
			}
		}
	}

}
