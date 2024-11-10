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
		if(!origen.getArmado()) {
			origen.setArmado(true);
			return 0;
		}
		
		destino.setProtegido(true);
		
		return 0;
	}

	@Override
	public String toString() {
		return "Proteccion";
	}

	@Override
	public boolean esDirigidoOponente() {
		return false;
	}
}
