package batallon;

import java.util.ArrayList;
import java.util.Random;

import personaje.Personaje;


public class Batallon implements BatallonIterable {
	
	private boolean tipo; //true Mago, false Mortifago
	protected ArrayList<Personaje> personajes = new ArrayList<> ();
	
	public ArrayList  <Personaje> getPersonajes(){
		return new ArrayList<Personaje> (personajes);
	}
	
	public boolean esBatallonMago() {
		return tipo;
	}
	
	public void setTipoBatallonMago(boolean valor) {
		tipo = valor;
	}
	
	public void setTipoBatallonMortifago(boolean valor) {
		tipo = !valor;
	}
	
	public boolean agregarPersonaje(Personaje personaje) {
		
		// Falta validar que no se pueda mezclar magos y mortifagos en un batallon
		
		return personajes.add(personaje);
	}
	
	public Personaje getPersonajeVivoAleatorio() {
		Personaje[] personajesVivos = getPersonajesVivos();
		if(personajesVivos == null) {
			return null;
		}
		Random rand = new Random();
		return personajesVivos[rand.nextInt(personajesVivos.length)];
	}
	
	public boolean tienePersonajesSaludables() {
		for(Personaje personaje : personajes) {
			if(personaje.estaVivo()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public BatallonIterator createIterator() {

		return new BatallonIteratorSecuencial(personajes);
	}

	public void atacar(Batallon oponente) {
		
		
	}
	
	private Personaje[] getPersonajesVivos() {
		ArrayList<Personaje> listaPersonajesVivos = new ArrayList<>();
		for(Personaje personaje : personajes) {
			if(personaje.estaVivo()) {
				listaPersonajesVivos.add(personaje);
			}
		}
		if(listaPersonajesVivos.isEmpty()) {
			return null;
		}
		Personaje[] vectorPersonajesVivos = listaPersonajesVivos.toArray(new Personaje[0]);
		return vectorPersonajesVivos;
	}
	
}
