package Aeroporto;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadDecolagem extends Thread {
	
	protected int id;
	protected String pista;
	public Random r = new Random();
	Semaphore semaN,semaS;

	public ThreadDecolagem(int i, Semaphore sema) {
		id = i;
		semaN = semaS = sema;
		
		if (r.nextInt() % 2 == 0) {
			pista = "norte";
		} else {
			pista = "sul";
		}
	}

	public void run() {
		Decolar();
	}

	
	public void Decolar() {
		long t1,tf = 0;
		
		switch (pista ) {
		case "norte":
			Adquirir(semaN);
			break;
			
		case "sul":
			Adquirir(semaS);
			break;
		
		}
		t1 = System.nanoTime();
		ProcDecolagem();

		switch (pista ) {
		case "norte":
			semaN.release();
			break;
			
		case "sul":
			semaS.release();
			break;
		
		}
		tf = (System.nanoTime()-t1)/1000000000;
		show(tf);
	}

	private void ProcDecolagem() {
		try {
			int x = 500;
			int time = (r.nextInt(4)+3);
			ThreadDecolagem.sleep(time*x);//manobrar
			time =(r.nextInt(5)+5);
			ThreadDecolagem.sleep(time*x);//taxiar
			time =(r.nextInt(3)+1);
			ThreadDecolagem.sleep(time*x);//decolar
			time =(r.nextInt(5)+3);
			ThreadDecolagem.sleep(time*x);//afastamento
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void show(long tf) {
		System.out.println
				("---------------------------\n"
				+ "Avião nº "+id+" \n"
				+ "Saida :"+pista+"\n"
				+ "Tempo de decolagem :"+tf+" s\n"
				+"--------------------------- ");
	}
	
	private void Adquirir(Semaphore semaN2) {
		try {
			semaN2.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
