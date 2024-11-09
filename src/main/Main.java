package main;

import batallon.Batallon;
import batallon.BatallonIteratorSecuencial;
import personaje.mago.Auror;

public class Main {

	public static void main(String[] args) {
		Batallon batallonMago = crearBatallonMago();
		
		BatallonIteratorSecuencial iterador =  (BatallonIteratorSecuencial) batallonMago.createIterator();
		
		
		System.out.println(iterador.hasNext());
		
		System.out.println(iterador.getNext());
		System.out.println(iterador.getNext());
		System.out.println(iterador.getNext());
		
		System.out.println(iterador.hasNext());
		
		iterador.eliminarPersonaje(new Auror("Auror00"));
		
		System.out.println(iterador.getNext());
		
		System.out.println(iterador.getNext());
		System.out.println(iterador.getNext());
		System.out.println(iterador.getNext());

	}
	
	private static Batallon crearBatallonMago() {
		Batallon batallonMago = new Batallon();
		for(int i=0; i<3; i++) {
			batallonMago.agregarPersonaje(new Auror("Auror0" + i));
		}
		return batallonMago;
	}

}

