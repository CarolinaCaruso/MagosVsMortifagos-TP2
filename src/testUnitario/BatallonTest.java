package testUnitario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import batallon.Batallon;
import exception.BatallonAgregarPersonajeNoValidoException;
import personaje.mago.Auror;
import personaje.mago.Mago;
import personaje.mortifago.Comandante;

class BatallonTest {

	private Batallon batallonMagos;
	private Batallon batallonMortifagos;
	
	@BeforeEach
	public void inicializar() {
		batallonMagos = new Batallon();
		batallonMortifagos = new Batallon();
		batallonMagos.setTipoBatallonMago(true);
		batallonMortifagos.setTipoBatallonMortifago(true);
	}
	
	@AfterEach
	public void reiniciar() {
		batallonMagos = null;
		batallonMortifagos = null;
	}
	
	@Test
	void agregarPersonajeDuplicado() {
		Auror mago = new Auror("Mago");
		batallonMagos.agregarPersonaje(mago);
		
		assertFalse(batallonMagos.agregarPersonaje(mago));
	}
	
	@Test
	void batallonTienePersonajesSaludables() {
		for(int i = 0; i<10; i++) {
			Auror auror = new Auror("Auror0" + i);
			batallonMagos.agregarPersonaje(auror);
		}
		assertTrue(batallonMagos.tienePersonajesSaludables());
	}
	
	@Test
	void batallonNoTienePersonajesSaludables() {
		for(int i = 0; i<10; i++) {
			Auror auror = new Auror("Auror0" + i);
			auror.recibirDanio(auror.getPuntosVida());
			batallonMagos.agregarPersonaje(auror);
		}
		assertFalse(batallonMagos.tienePersonajesSaludables());
	}
	
	@Test
	void batallonMagoAgregaMortifagoException() {				
		assertThrows(
				BatallonAgregarPersonajeNoValidoException.class,
				() -> batallonMagos.agregarPersonaje(new Comandante("nuevoComandante"))
				);
	}
	
	@Test
	void batallonMortifagoAgregaMagoException() {		
		assertThrows(
				BatallonAgregarPersonajeNoValidoException.class,
				() -> batallonMortifagos.agregarPersonaje(new Auror("nuevoAuror"))
				);
	}
	
	void getPersonajeVivoAleatorioDeBatallonMuerto() {
		for(int i = 0; i<10; i++) {
			Auror auror = new Auror("Auror0" + i);
			auror.recibirDanio(auror.getPuntosVida());
			batallonMagos.agregarPersonaje(auror);
		}
		assertEquals(null, batallonMagos.getPersonajeVivoAleatorio());
	}

}
