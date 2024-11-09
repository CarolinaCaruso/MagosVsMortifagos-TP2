package batallon;

import java.util.ArrayList;
import java.util.Collections;

import personaje.Personaje;

public class BatallonIteratorSecuencial implements BatallonIterator {

	private ArrayList <Personaje> personajesSaludables;
	private int indiceActual;
	
	
	public BatallonIteratorSecuencial (ArrayList <Personaje> personajes) {
		personajesSaludables = new ArrayList <Personaje> (personajes);
		mezclar();
		indiceActual = 0;
	}
	
	@Override
	public boolean hasNext() {
		if(indiceActual >= personajesSaludables.size()) {
			reset();
			return false;
		}
		return true;	
	}

	@Override
	public Personaje getNext() {
		
		if(!hasNext()) {
			return  null;
		}
		return personajesSaludables.get(indiceActual++);
	}
	
	public boolean eliminarPersonaje (Personaje personaje) {
		return personajesSaludables.remove(personaje);

	}
	
	public void reset() {
		indiceActual = 0;
		mezclar();
	}
	
	private void mezclar() {
		Collections.shuffle(personajesSaludables);
	}
	

}
