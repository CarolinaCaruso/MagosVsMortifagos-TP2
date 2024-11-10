package batalla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import batallon.Batallon;
import batallon.BatallonIterator;
import batallon.BatallonIteratorSecuencial;
import exception.BatallonNuloException;
import exception.LanzarHechizoVictimaNoValidaException;
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
	private int ronda;
	
	private Set<Hechizo> hechizosUsados = new HashSet<>();
	private Map<Personaje, ArrayList<Hechizo>> hechizosLanzadosPorPersonaje = new HashMap<>();
	
	public Batalla(Batallon batallonMagos, Batallon batallonMortifagos) {
		
		if(batallonMagos == null) {
			throw new BatallonNuloException("Constructor Batalla: batallonMagos no puede ser nulo.");
		}
		if(batallonMortifagos == null) {
			throw new BatallonNuloException("Constructor Batalla: batallonMortifagos no puede ser nulo.");
		}
			// validar que no sean batallones del mismo tipo
		
		this.batallonMagos = batallonMagos;
		this.batallonMortifagos = batallonMortifagos;
		
		iteradorMagos = (BatallonIteratorSecuencial) batallonMagos.createIterator();
		iteradorMortifagos = (BatallonIteratorSecuencial) batallonMortifagos.createIterator();
		
		Random rand = new Random();
		flagTurno = rand.nextBoolean();
		ronda = 1;
	}
	
	public boolean realizarBatalla() {	// ver return
		while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
			realizarRonda();
		}
	
		if (batallonMagos.tienePersonajesSaludables()) {
		log.agregarRegistro("¡Los magos han ganado la batalla!");
		return true;
		} else {
		log.agregarRegistro("¡Los mortífagos han ganado la batalla!");
		return false;
		}
	}
	
	public void realizarRonda() {
		
		log.agregarRegistro("------------RONDA " + ronda + " ----------------");				
		if (flagTurno) {
			batallonMagosAtaca();	
			hechizosUsados.clear();
			if(batallonMortifagos.tienePersonajesSaludables()) {
				batallonMortifagosAtaca();		
			}
			
		} else {
			batallonMortifagosAtaca();
			hechizosUsados.clear();
			if(batallonMagos.tienePersonajesSaludables()) {
				batallonMagosAtaca();
			}
		}
		hechizosUsados.clear();
		ronda++;
		
	}
	
	public void batallonMagosAtaca() {
		Mago magoAtacante;
		Personaje personajeDestino;
		
		while(iteradorMagos.hasNext() && batallonMortifagos.tienePersonajesSaludables()) {
			magoAtacante = (Mago)iteradorMagos.getNext();
			HechizoConcreto hechizoSeleccionado = (HechizoConcreto)magoAtacante.getHechizoAleatorioNoUsado(hechizosUsados);
			if(hechizoSeleccionado != null) {
				personajeDestino = magoSeleccionaVictima(hechizoSeleccionado);
				magoLanzaHechizo(magoAtacante, personajeDestino, hechizoSeleccionado);
				log.agregarRegistro(magoAtacante, personajeDestino, hechizoSeleccionado);
				registrarHechizoLanzadoPorPersonaje(magoAtacante, hechizoSeleccionado);
				hechizosUsados.add(hechizoSeleccionado);
				if(!personajeDestino.estaVivo()) {
					log.agregarRegistro(personajeDestino + " fue derrotado.");
					((BatallonIteratorSecuencial)iteradorMortifagos).eliminarPersonaje(personajeDestino);
				}
			}
			else {	
				log.agregarRegistro(magoAtacante + " no tiene hechizos disponible para lanzar.");
			}
		}
	}
	
	public void batallonMortifagosAtaca() {
		
		Mortifago mortifagoAtacante;
		Personaje personajeDestino;
		while(iteradorMortifagos.hasNext() && batallonMagos.tienePersonajesSaludables()) {
			mortifagoAtacante = (Mortifago)iteradorMortifagos.getNext();
			HechizoConcreto hechizoSeleccionado = (HechizoConcreto)mortifagoAtacante.getHechizoAleatorioNoUsado(hechizosUsados);
			
			if(hechizoSeleccionado != null) {
				personajeDestino = mortifagoSeleccionaVictima(hechizoSeleccionado);
				mortifagoLanzaHechizo(mortifagoAtacante, personajeDestino, hechizoSeleccionado);
				log.agregarRegistro(mortifagoAtacante, personajeDestino, hechizoSeleccionado);	
				registrarHechizoLanzadoPorPersonaje(mortifagoAtacante, hechizoSeleccionado);
				hechizosUsados.add(hechizoSeleccionado);
				if(!personajeDestino.estaVivo()) {
					log.agregarRegistro(personajeDestino + " fue derrotado.");
					((BatallonIteratorSecuencial)iteradorMagos).eliminarPersonaje(personajeDestino);
				}
			}
			else {
				log.agregarRegistro(mortifagoAtacante + " no tiene hechizos disponible para lanzar.");
			}
		}
	}
	
	public int magoLanzaHechizo(Mago origen, Personaje destino, HechizoConcreto hechizo) {
		
		if(destino.esMago() ) {
			if(hechizo.esDirigidoOponente()) {
				throw new LanzarHechizoVictimaNoValidaException(origen + " no puede lanzar hechizo a " + destino + ". Fuego amigo.");	
			}
		} else {
			if(!hechizo.esDirigidoOponente()) {
				throw new LanzarHechizoVictimaNoValidaException(origen + " no puede lanzar hechizo a " + destino + ". es hechizo de equipo.");	
			}
		}
		return origen.lanzarHechizo(hechizo, destino);
	}
	
	public int mortifagoLanzaHechizo(Mortifago origen, Personaje destino, HechizoConcreto hechizo) {
		if(destino.esMortifago() ) {
			if(hechizo.esDirigidoOponente()) {
				throw new LanzarHechizoVictimaNoValidaException(origen + " no puede lanzar hechizo a " + destino + ". Fuego amigo.");	
			}
		} else {
			if(!hechizo.esDirigidoOponente()) {
				throw new LanzarHechizoVictimaNoValidaException(origen + " no puede lanzar hechizo a " + destino + ". es hechizo de equipo.");	
			}
		}
		return origen.lanzarHechizo(hechizo, destino);
	}
	
	public void mostrarLog() {
		log.mostrarRegistroConsola();
	}
	
	private Personaje magoSeleccionaVictima(HechizoConcreto hechizo) {
		if(hechizo.esDirigidoOponente()) {
			return batallonMortifagos.getPersonajeVivoAleatorio();
		}
		return batallonMagos.getPersonajeVivoAleatorio();		
		
	}
	
	private Personaje mortifagoSeleccionaVictima(HechizoConcreto hechizo) {
		
		if(hechizo.esDirigidoOponente()) {
			return batallonMagos.getPersonajeVivoAleatorio();
		}
		return batallonMortifagos.getPersonajeVivoAleatorio();		
	}
	
	private boolean registrarHechizoLanzadoPorPersonaje(Personaje personaje, Hechizo hechizo) {
		if(!hechizosLanzadosPorPersonaje.containsKey(personaje)) {
			hechizosLanzadosPorPersonaje.put(personaje, new ArrayList<Hechizo>());
		}
		 return hechizosLanzadosPorPersonaje.get(personaje).add(hechizo);
	}
	
}
