package testUnitario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exception.PersonajeHechizoNoDisponibleException;
import hechizo.Ataque10;
import personaje.mago.Auror;
import personaje.mago.Mago;
import personaje.mortifago.Mortifago;
import personaje.mortifago.Seguidor;


class PersonajeTest {
	
	private Mago mago;
	private Mortifago mortifago;

	@BeforeEach
	void inicializar() {
		mago = new Auror("Mago00");
		mortifago = new Seguidor("Mortifago00");
	}
	
	@AfterEach
	void reiniciar() {
		mago = null;
		mortifago = null;
	}
	
	@Test
	void personajeAgregaHechizoDuplicado() {
		mago.agregarHechizo(new Ataque10());
		assertFalse(mago.agregarHechizo(new Ataque10()));
	}
	

	@Test
	void personajeLanzaHechizoNoDispone() {
		assertThrows(PersonajeHechizoNoDisponibleException.class,
				() -> mago.lanzarHechizo(new Ataque10(), mortifago));
	}
	
	@Test
	void personajeRecibeDanioNegativo() {
		assertFalse(mago.recibirDanio(-10));
	}

	@Test
	void personajeRecibeDanioEstandoMuerto() {
		int hp = mago.getPuntosVida();
		mago.recibirDanio(hp);
		assertFalse(mago.recibirDanio(10));
	}
	
	@Test
	void personajeSeCuraEstandoMuerto() {
		int hp = mago.getPuntosVida();
		mago.recibirDanio(hp);
		assertFalse(mago.curar(10));
	}
	
	

}
