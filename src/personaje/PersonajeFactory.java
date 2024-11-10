package personaje;

import java.util.Random;

import hechizo.HechizoFactory;
import hechizo.TipoHechizo;
import personaje.mago.Auror;
import personaje.mago.Estudiante;
import personaje.mago.Mago;
import personaje.mago.Profesor;
import personaje.mago.TipoMago;
import personaje.mortifago.Comandante;
import personaje.mortifago.Mortifago;
import personaje.mortifago.Seguidor;
import personaje.mortifago.TipoMortifago;

public class PersonajeFactory {
	private static final int PUNTOS_VIDA = 100;
	private static final boolean ARMADO = true;
	private static final boolean PROTEGIDO = false;
	
	public static Mago crearMago(TipoMago tipo, String nombrePersonaje) {
		
		if(tipo == null) {
			throw new IllegalArgumentException();
		}
		if(nombrePersonaje == null) {
			throw new IllegalArgumentException();
		}
		
		Mago magoNuevo;

		switch (tipo) {
		case TipoMago.Auror:
			magoNuevo = new Auror(nombrePersonaje, PUNTOS_VIDA, ARMADO, PROTEGIDO);
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Ataque10));
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Curacion));
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Proteccion));
			break;

		case TipoMago.Profesor:
			magoNuevo = new Profesor(nombrePersonaje, PUNTOS_VIDA, ARMADO, PROTEGIDO);
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Ataque20));
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Ataque30));
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Desarmar));
			break;
			
		case TipoMago.Estudiante:
			magoNuevo = new Estudiante(nombrePersonaje, PUNTOS_VIDA, ARMADO, PROTEGIDO);
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Ataque10));
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Curacion));
			magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Proteccion));
			break;

		default:
			throw new IllegalArgumentException("Tipo de mago desconocido");
		}
		
//		magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Expelliarmus));
//		magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Protego));
//		magoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Sectumsempra));
		
		return magoNuevo;
	}

	public static Mortifago crearMortifago(TipoMortifago tipo, String nombrePersonaje) {
	
		if(tipo == null) {
			throw new IllegalArgumentException();
		}
		if(nombrePersonaje == null) {
			throw new IllegalArgumentException();
		}
		
		Mortifago mortifagoNuevo;

		switch (tipo) {
		case TipoMortifago.Comandante:
			mortifagoNuevo = new Comandante(nombrePersonaje, PUNTOS_VIDA, ARMADO, PROTEGIDO);
			mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Ataque10));
			mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Ataque20));
			mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Curacion));
			mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Proteccion));
			break;
			
		case TipoMortifago.Seguidor:
			mortifagoNuevo = new Seguidor(nombrePersonaje, PUNTOS_VIDA, ARMADO, PROTEGIDO);
			mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Ataque20));
			mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Ataque30));
			mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Curacion));
			mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Proteccion));
			break;
			
		default:
			throw new IllegalArgumentException("Tipo de mortifago desconocido");
		}
		
		//Se pensó que los mortífagos conozcan todos los mismos hechizos, pero su daño es en base a su nivel mágico
//		mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.AvadaKedavra));
//		mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Crusio));
//		mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Imperio));
//		mortifagoNuevo.agregarHechizo(HechizoFactory.crearHechizo(TipoHechizo.Protego));
		
		return mortifagoNuevo;
	}
	
	public static Mago crearMagoAleatorio(String nombre) {
		
		TipoMago[] tipoMagos = {
			TipoMago.Auror,
			TipoMago.Estudiante,
			TipoMago.Profesor
		};
		Random rand = new Random();
		TipoMago seleccionado = tipoMagos[rand.nextInt(tipoMagos.length)];
		return PersonajeFactory.crearMago(seleccionado, nombre);
	}
	
	public static Mortifago crearMortifagoAleatorio(String nombre) {
		
		TipoMortifago[] tipoMortifagos = {
			TipoMortifago.Seguidor,
			TipoMortifago.Comandante
		};
		Random rand = new Random();
		TipoMortifago seleccionado = tipoMortifagos[rand.nextInt(tipoMortifagos.length)];
		return PersonajeFactory.crearMortifago(seleccionado, nombre);
	}
}
