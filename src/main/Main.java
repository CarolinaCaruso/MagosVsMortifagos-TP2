package main;

import batalla.Batalla;
import batallon.Batallon;
import hechizo.Hechizo;
import personaje.Personaje;
import personaje.PersonajeFactory;
import personaje.mago.TipoMago;
import personaje.mortifago.TipoMortifago;

public class Main {

	public static void main(String[] args) {
		Batallon batallonMagos = crearBatallonMago();
		Batallon batallonMortifagos = crearBatallonMortifago();
		Batalla batalla = new Batalla(batallonMagos, batallonMortifagos);
		batalla.realizarBatalla();
		batalla.mostrarLog();

	}

	private static Batallon crearBatallonMago() {
		Batallon batallonMago = new Batallon();
		batallonMago.setTipoBatallonMago(true);
		for (int i = 0; i < 3; i++) {
			batallonMago.agregarPersonaje(PersonajeFactory.crearMago(TipoMago.Auror, "Auror00" + i));
		}
		batallonMago.setTipoBatallonMago(true);
		return batallonMago;
	}

	private static Batallon crearBatallonMortifago() {
		Batallon batallonMortifago = new Batallon();
		batallonMortifago.setTipoBatallonMortifago(true);
		for (int i = 0; i < 3; i++) {
			batallonMortifago.agregarPersonaje(PersonajeFactory.crearMortifago(TipoMortifago.Seguidor, "Seguidor0" + i));
		}
		batallonMortifago.setTipoBatallonMortifago(true);
		return batallonMortifago;
	}
	
	private static void mostrarResultadoConsola(Personaje atacante, Hechizo hechizoLanzado, Personaje destino) {	// solo para test
		System.out.print(atacante + " -> " + hechizoLanzado + " -> " + destino);
		System.out.println("\t\t" + atacante.getPuntosVida() + " / " + destino.getPuntosVida());
	}

}
