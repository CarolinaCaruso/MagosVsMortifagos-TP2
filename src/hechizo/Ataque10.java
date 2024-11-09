package hechizo;

import personaje.Personaje;

public class Ataque10 extends HechizoConcreto {
	
	private int danio;
	
	public Ataque10() {
		danio = 10;
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
			destino.setProtegido(false);
			return 0;
		}
		int danioReal = danio;
		destino.recibirDanio(danioReal);		
		return danioReal;
	}

	@Override
	public String toString() {
		return "Ataque10";
	}	
}