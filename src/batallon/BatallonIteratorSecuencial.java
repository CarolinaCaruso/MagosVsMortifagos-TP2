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
		return indiceActual < personajesSaludables.size();	
	}

	@Override
	public Personaje getNext() {
		
		if(!hasNext()) {
			if(!personajesSaludables.isEmpty()) {	
				mezclar();
			}
			indiceActual = 0;
			return  null;
		}
		return personajesSaludables.get(indiceActual++);
	}
	
	public boolean eliminarPersonaje (Personaje personaje) {
		return personajesSaludables.remove(personaje);

	}
	
	private void mezclar() {
		Collections.shuffle(personajesSaludables);
	}
	

}
