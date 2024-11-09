package hechizo;

import personaje.Personaje;

public class Proteccion extends HechizoConcreto {
	
	public Proteccion() {
	}
	
	@Override
	public int ejecutar(Personaje origen, Personaje destino) {
		
		if(origen == null) {
			// Excep
		}
		if(destino == null) {
			// Excep
		}
		if(destino.getProtegido()) {
			// ya esta protegido Excepcion??
		}
		
		destino.setProtegido(true);
		
		return 0;
	}

	@Override
	public String toString() {
		return "Proteccion";
	}
}
