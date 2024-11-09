package Batalla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import batallon.Batallon;
import batallon.BatallonIterator;
import batallon.BatallonIteratorSecuencial;
import hechizo.Hechizo;
import hechizo.HechizoConcreto;
import personaje.Personaje;
import personaje.mago.Mago;
import personaje.mortifago.Mortifago;

public class Batalla {
	
	private Batallon batallonMagos;
	private Batallon batallonMortifagos;
	private BatallonIterator iteradorMagos;
	private BatallonIterator iteradorMortifagos;
	private Log log = new Log();
	private boolean flagTurno;
	
	private Set<Hechizo> hechizosUsados = new HashSet<>();
	private Map<Personaje, ArrayList<Hechizo>> registroHechizos = new HashMap<>();
	
	public Batalla(Batallon batallonMagos, Batallon batallonMortifagos) {
		
		if(batallonMagos == null) {
			// Excepcion
		}
		if(batallonMortifagos == null) {
			// Excepcion
		}
		
		// Validar que no sean batallones del mismo tipo
		
		this.batallonMagos = batallonMagos;
		this.batallonMortifagos = batallonMortifagos;
		
		iteradorMagos = (BatallonIteratorSecuencial) batallonMagos.createIterator();
		iteradorMortifagos = (BatallonIteratorSecuencial) batallonMortifagos.createIterator();
		
		Random rand = new Random();
		flagTurno = rand.nextBoolean();
	}
	
	public void realizarBatalla() {
		while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
			realizarRonda();
		}
	
		
		if (batallonMagos.tienePersonajesSaludables()) {
		log.agregarRegistro("¡Los magos han ganado la batalla!");
		} else {
		log.agregarRegistro("¡Los mortífagos han ganado la batalla!");
		}
	}
	
	public void realizarRonda() {
				
		if (flagTurno) {
			batallonMagosAtaca();	
			hechizosUsados.clear();
			batallonMortifagosAtaca();
			hechizosUsados.clear();
		} else {
			batallonMortifagosAtaca();
			hechizosUsados.clear();
			batallonMagosAtaca();
			hechizosUsados.clear();
		}
		log.agregarRegistro("----------------------------");
	}
	
	private void batallonMagosAtaca() {
		Personaje personajeAtacante;
		Personaje personajeDestino;
		while(iteradorMagos.hasNext()) {
			personajeAtacante = (Mago)iteradorMagos.getNext();
			Hechizo hechizoSeleccionado = personajeAtacante.getHechizoAleatorioNoUsado(hechizosUsados);
			if(hechizoSeleccionado == null) {
				System.out.println("Personaje " + personajeAtacante.getNombre() + " No tiene hechizos para lanzar");
				break;
			}
			else {
				if(((HechizoConcreto)hechizoSeleccionado).esDirigidoOponente()) {
					personajeDestino = (Mortifago)batallonMortifagos.getPersonajeVivoAleatorio();
				}
				else {
					personajeDestino = (Mago)batallonMagos.getPersonajeVivoAleatorio();
				}	
				personajeAtacante.lanzarHechizo(hechizoSeleccionado, personajeDestino);	// arreglar ACa
				hechizosUsados.add(hechizoSeleccionado);
				log.agregarRegistro(personajeAtacante, personajeDestino, hechizoSeleccionado);	
			}
		}
	
		
	}
	
	private void batallonMortifagosAtaca() {
		Personaje personajeAtacante;
		Personaje personajeDestino;

		
		while(iteradorMortifagos.hasNext()) {
			personajeAtacante = (Mortifago)iteradorMortifagos.getNext();
			Hechizo hechizoSeleccionado = personajeAtacante.getHechizoAleatorioNoUsado(hechizosUsados);
			
			if(hechizoSeleccionado == null) {
				System.out.println("Personaje " + personajeAtacante.getNombre() + " No tiene hechizos para lanzar");
				break;
			}
			else {
				if(((HechizoConcreto)hechizoSeleccionado).esDirigidoOponente()) {
					personajeDestino = (Mago)batallonMagos.getPersonajeVivoAleatorio();
				}
				else {
					personajeDestino = (Mortifago)batallonMortifagos.getPersonajeVivoAleatorio();
				}	
				personajeAtacante.lanzarHechizo(hechizoSeleccionado, personajeDestino);
				hechizosUsados.add(hechizoSeleccionado);
				log.agregarRegistro(personajeAtacante, personajeDestino, hechizoSeleccionado);	
			}
		}
	
	}
	
	public void mostrarLog() {
		log.mostrarRegistroConsola();
	}
	
	// Solo para test!!!
	private void mostrarResultadoConsola(Personaje atacante, Hechizo hechizoLanzado, Personaje destino) {	// solo para test
		System.out.print(atacante + " -> " + hechizoLanzado + " -> " + destino);
		System.out.println("\t\t" + atacante.getPuntosDeVida() + " / " + destino.getPuntosDeVida());
	}
	
}
