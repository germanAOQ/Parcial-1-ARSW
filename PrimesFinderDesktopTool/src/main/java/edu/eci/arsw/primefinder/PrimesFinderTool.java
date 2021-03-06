package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

	public static void main(String[] args) throws InterruptedException {
		     /**       
            int maxPrim=1000;
            
            PrimesResultSet prs=new PrimesResultSet("john");
            
            PrimeFinder.findPrimes(new BigInteger("1"), new BigInteger("10"), prs);
            
            System.out.println("Prime numbers found:");
            
            System.out.println(prs.getPrimes());
            */    
		
			//Punto 1
		
            CalcularPrimosConcurrente cpc = new CalcularPrimosConcurrente(10000, 4);
            System.out.println(cpc.encontrarPrimos());
            
            
            
            //Punto 2
            
            
            
            while(!Thread.interrupted()){
                try {
                    //check every 10ms if the idle status (10 seconds without mouse
                    //activity) was reached. 
                    Thread.sleep(10);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
                        System.out.println("Idle CPU ");
                        System.out.println(cpc.encontrarPrimos());
                    }
                    else{
                        System.out.println("User working again!");
                        Thread.holdsLock(cpc);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                synchronized (cpc) {
					cpc.notify();
				}
            }
                        
            
            
            
            
	}
	
}


