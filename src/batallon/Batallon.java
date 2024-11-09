package batallon;

import java.util.ArrayList;

import personaje.Personaje;


public class Batallon implements BatallonIterable {
	
	private boolean tipo; //true Mago, false Mortifago
	protected ArrayList<Personaje> personajes = new ArrayList<> ();
	
	public ArrayList  <Personaje> getPersonajes(){
		return new ArrayList<Personaje> (personajes);
	}
	
	public boolean agregarPersonaje(Personaje personaje) {
		return personajes.add(personaje);
	}

	@Override
	public BatallonIterator createIterator() {

		return new BatallonIteratorSecuencial (personajes);
	}
	
}
