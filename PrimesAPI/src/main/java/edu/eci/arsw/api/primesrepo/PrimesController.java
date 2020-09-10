package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeException;
import edu.eci.arsw.api.primesrepo.service.PrimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{
	@Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = GET )
    public ResponseEntity<?> getPrimes()
    {
    	String data = new Gson().toJson(primeService.getFoundPrimes());
    	return new ResponseEntity<>(data,HttpStatus.ACCEPTED);	
    }
    
    @PostMapping("/primes")
    public ResponseEntity<?> addFoundPrime(@RequestBody FoundPrime foundPrime ) {
    	try {
			primeService.addFoundPrime(foundPrime);
		} catch (PrimeException e) {
			// TODO Auto-generated catch block
	        return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN); 
		}
		return new ResponseEntity<>(HttpStatus.CREATED);

    }
    
    @RequestMapping(value = "/primes/{primenumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrimeNumber(@PathVariable String primenumber)
    {
    	String data = new Gson().toJson(primeService.getPrime(primenumber));
        return new ResponseEntity<>(data,HttpStatus.ACCEPTED);

    }

    //TODO implement additional methods provided by PrimeService



}
