package batallon;

import java.util.ArrayList;
import java.util.Random;

import exception.BatallonAgregarPersonajeNoValidoException;
import personaje.Personaje;


public class Batallon implements BatallonIterable {
	
	private boolean tipo; //true Mago, false Mortifago
	protected ArrayList<Personaje> personajes = new ArrayList<> ();
	
	public ArrayList<Personaje> getPersonajes(){
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
		
		if(personaje.esMortifago() && tipo) { // tipo==true es batallon de magos
			throw new BatallonAgregarPersonajeNoValidoException("El batallon es tipo Mortifagos, no se puede agregar un Mago");
		}
		
		if(personaje.esMago() && !tipo) {	// tipo==false es batallon de mortifagos
			throw new BatallonAgregarPersonajeNoValidoException("El batallon es tipo Mago, no se puede agregar un Mortifago");
		}	
		if(this.contienePersonaje(personaje)) {
			return false;
		}
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
	
	public boolean contienePersonaje(Personaje personaje) {
		for(Personaje enLista : personajes) {
			if(enLista.getNombre().equals(personaje.getNombre())) {
				return true;
			}
		}
		return false;
	}
	
}
