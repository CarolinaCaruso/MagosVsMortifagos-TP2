package Batalla;

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
			log.add(atacante + " usó " + hechizo + "\t\t" + atacante.getPuntosDeVida());
		} else {
			log.add(atacante + " -> " + hechizo + " -> " + destino + "\t\t" + atacante.getPuntosDeVida() + " / " + destino.getPuntosDeVida());
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
