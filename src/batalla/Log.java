package batalla;

import java.util.ArrayList;
import java.util.List;

import hechizo.Hechizo;
import personaje.Personaje;

public class Log {

	private List<String> log = new ArrayList<>();
	
	public boolean agregarRegistro(Personaje atacante, Personaje destino, Hechizo hechizo) {
		if(atacante == null) {
			//
		}
		if(destino == null) {
			//
		}
		if(hechizo == null) {
			//
		}
		if(atacante == destino) {
			log.add(atacante + " -> " + hechizo + "\t\t\t" + atacante.getPuntosVida());
		} else {
			log.add(atacante + " -> " + hechizo + " -> " + destino + "\t\t" + atacante.getPuntosVida() + " / " + destino.getPuntosVida());
		}
		return true;
	}
	
	public boolean agregarRegistro(String registro) {
		return log.add(registro);		
	}
	
	public void mostrarRegistroConsola() {
		for(String registro : log) {
			System.out.println(registro);
		}
	}
}
