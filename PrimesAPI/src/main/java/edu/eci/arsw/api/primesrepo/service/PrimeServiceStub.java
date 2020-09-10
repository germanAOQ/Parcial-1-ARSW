package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 * @author Santiago Carrillo 2/22/18.
 */

@Service
public class PrimeServiceStub implements PrimeService {
	static ConcurrentHashMap<String, FoundPrime> primosEncontrados;

	public PrimeServiceStub() {
		primosEncontrados = new ConcurrentHashMap<String, FoundPrime>();
	}

	@Override
	public void addFoundPrime(FoundPrime foundPrime) throws PrimeException {
		if(primosEncontrados.contains(foundPrime.getPrime())) throw new PrimeException("El número primo ya tiene un usuario"); 
		primosEncontrados.put(foundPrime.getPrime(),foundPrime);
	}

	@Override
	public List<FoundPrime> getFoundPrimes() {
		ArrayList<FoundPrime> list = new ArrayList<FoundPrime>();
		for(FoundPrime fp: primosEncontrados.values()) {
			list.add(fp);
		}
		return list;
	}

	@Override
	public FoundPrime getPrime(String prime) {
		return primosEncontrados.get(prime);
	}
}
