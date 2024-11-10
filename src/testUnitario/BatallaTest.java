package testUnitario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import batalla.Batalla;
import batallon.Batallon;
import exception.LanzarHechizoVictimaNoValidaException;
import hechizo.Ataque10;
import hechizo.Proteccion;
import personaje.mago.Mago;
import personaje.mago.Profesor;
import personaje.mago.TipoMago;
import personaje.Personaje;
import personaje.PersonajeFactory;
import personaje.mago.Auror;
import personaje.mago.Estudiante;
import personaje.mortifago.Mortifago;
import personaje.mortifago.Seguidor;
import personaje.mortifago.TipoMortifago;
import personaje.mortifago.Comandante;

class BatallaTest {

	private Batalla batalla;
	private Batallon batallonMagos;
	private Batallon batallonMortifagos;
	
	@BeforeEach
	void inicializar() {
		batallonMagos = new Batallon();
		batallonMagos.setTipoBatallonMago(true);
		batallonMortifagos = new Batallon();
		batallonMortifagos.setTipoBatallonMortifago(true);
		
//		Mago 	auror = new Auror("Auror"),
//				estudiante = new Estudiante("Estudiante"),
//				profesor = new Profesor("Profesor");	
//		Mortifago 	seguidor = new Seguidor("Seguidor"),
//					seguidor02 = new Seguidor("Seguidor02"),
//					comandante = new Comandante("Comandante");
		
		Mago 	auror = PersonajeFactory.crearMago(TipoMago.Auror, "Auror"),
				estudiante = PersonajeFactory.crearMago(TipoMago.Estudiante, "Estudiante"),
				profesor = PersonajeFactory.crearMago(TipoMago.Profesor, "Profesor");
		Mortifago 	seguidor = PersonajeFactory.crearMortifago(TipoMortifago.Seguidor, "Seguidor00"),
					seguidor02 = PersonajeFactory.crearMortifago(TipoMortifago.Seguidor, "Seguidor02"),
					comandante = PersonajeFactory.crearMortifago(TipoMortifago.Comandante, "Comandante");
		
		batallonMagos.agregarPersonaje(auror);
		batallonMagos.agregarPersonaje(estudiante);
		batallonMagos.agregarPersonaje(profesor);	
		batallonMortifagos.agregarPersonaje(seguidor);
		batallonMortifagos.agregarPersonaje(seguidor02);
		batallonMortifagos.agregarPersonaje(comandante);
		batalla = new Batalla(batallonMagos, batallonMortifagos);
	}
	
	@AfterEach
	void reiniciar() {
		batalla = null;
		batallonMagos = null;
		batallonMortifagos = null;
	}
	
	@Test
	void batallonMagosIniciaSinMagosEnBatalla() {	// ver return realizarBatalla
		batallonMagos = new Batallon();
		batalla = new Batalla(batallonMagos, batallonMortifagos);
		
		assertFalse(batalla.realizarBatalla());
	}
	
	@Test
	void batallonMortifagosIniciaSinMortifagosEnBatalla() {	// ver return realizarBatalla
		batallonMortifagos = new Batallon();
		batalla = new Batalla(batallonMagos, batallonMortifagos);
		
		assertTrue(batalla.realizarBatalla());
	}
	
	@Test
	void batallonMagosIniciaSinSaludablesEnBatalla() {	// ver return realizarBatalla
		List<Personaje> magos = batallonMagos.getPersonajes();
		for(Personaje mago : magos) {
			mago.recibirDanio(mago.getPuntosVida());
		}
		batalla = new Batalla(batallonMagos, batallonMortifagos);
		assertFalse(batalla.realizarBatalla());
	}
	
	@Test
	void batallonMortifagosIniciaSinSaludablesEnBatalla() {	// ver return realizarBatalla
		List<Personaje> mortifagos = batallonMortifagos.getPersonajes();
		for(Personaje mortifago : mortifagos) {
			mortifago.recibirDanio(mortifago.getPuntosVida());
		}
		batalla = new Batalla(batallonMagos, batallonMortifagos);
		
		assertTrue(batalla.realizarBatalla());
	}
	
	@Test
	void magoLanzaHechizoDeEquipoAenemigo() {
		Mago mago = (Mago)batallonMagos.getPersonajeVivoAleatorio();
		Mortifago mortifago = (Mortifago)batallonMortifagos.getPersonajeVivoAleatorio();
		
		assertThrows(
				LanzarHechizoVictimaNoValidaException.class,
				() -> batalla.magoLanzaHechizo(mago, mortifago, new Proteccion())
				);
	}
	
	@Test
	void mortifagoLanzaHechizoDeEquipoAenemigo() {
		Mago mago = (Mago)batallonMagos.getPersonajeVivoAleatorio();
		Mortifago mortifago = (Mortifago)batallonMortifagos.getPersonajeVivoAleatorio();
		
		assertThrows(
				LanzarHechizoVictimaNoValidaException.class,
				() -> batalla.mortifagoLanzaHechizo(mortifago, mago, new Proteccion())
				);
	}
	
	@Test	
	void magoLanzaHechizoDirigidoOponenteAaliado() {
		Mago mago = (Mago)batallonMagos.getPersonajeVivoAleatorio();
		Mago nuevoMago = new Estudiante("nuevo");
		
		assertThrows(
				LanzarHechizoVictimaNoValidaException.class,
				() -> batalla.magoLanzaHechizo(mago, nuevoMago, new Ataque10())
				);
	}
	
	@Test	
	void mortifagoLanzaHechizoDirigidoOponenteAaliado() {
		Mortifago mortifago = (Mortifago)batallonMortifagos.getPersonajeVivoAleatorio();
		Mortifago nuevoMortifago = new Seguidor("nuevo");
		
		assertThrows(
				LanzarHechizoVictimaNoValidaException.class,
				() -> batalla.mortifagoLanzaHechizo(mortifago, nuevoMortifago, new Ataque10())
				);
	}

}
