package hechizo;

import personaje.Personaje;

public class Desarmar extends HechizoConcreto {

	@Override
	public int ejecutar(Personaje origen, Personaje destino) {
		if(!origen.getArmado()) {
			origen.setArmado(true);
		} else destino.setArmado(false);
		
		return 0;
	}

	@Override
	public boolean esDirigidoOponente() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Desarmar";
	}

}
