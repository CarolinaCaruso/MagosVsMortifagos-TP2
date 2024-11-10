package hechizo;

import personaje.Personaje;

public class Curacion extends HechizoConcreto {

	private int valor;
	
	public Curacion() {
		valor = 10;
	}
	
	@Override
	public int ejecutar(Personaje origen, Personaje destino) {
		
		if(origen == null) {
			// Excep
		}
		if(destino == null) {
			// Excep
		}
		if(!origen.getArmado()) {
			origen.setArmado(true);
			return 0;
		}
		
		// Va a curar siempre!!!
		
		int valorFinal = valor;
		destino.curar(valorFinal);		
		return valorFinal;
	}

	@Override
	public String toString() {
		return "Curacion";
	}

	@Override
	public boolean esDirigidoOponente() {
		return false;
	}
}
