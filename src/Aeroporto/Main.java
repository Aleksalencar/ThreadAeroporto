package Aeroporto;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		int permits = 2;
		Semaphore semaforo = new Semaphore(permits);
		for (int i = 0; i < 12; i++) {
			ThreadDecolagem TDec = new ThreadDecolagem(i,semaforo);
			TDec.start();
		}
	}

}
