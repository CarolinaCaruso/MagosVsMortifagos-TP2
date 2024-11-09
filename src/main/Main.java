package main;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Batalla.Batalla;
import batallon.Batallon;
import batallon.BatallonIteratorSecuencial;
import hechizo.Hechizo;
import personaje.Personaje;
import personaje.PersonajeFactory;
import personaje.mago.Auror;
import personaje.mago.Mago;
import personaje.mago.TipoMago;
import personaje.mortifago.Mortifago;
import personaje.mortifago.Seguidor;
import personaje.mortifago.TipoMortifago;

public class Main {

	public static void main(String[] args) {
		Batallon batallonMagos = crearBatallonMago();
		Batallon batallonMortifagos = crearBatallonMortifago();
		
		Batalla batalla = new Batalla(batallonMagos, batallonMortifagos);
		batalla.realizarBatalla();
		batalla.mostrarLog();
		
		
		
//		Set<Hechizo> hechizosUsados = new HashSet<>();
		
//		BatallonIteratorSecuencial iteradorMagos = (BatallonIteratorSecuencial) batallonMagos.createIterator();
//		BatallonIteratorSecuencial iteradorMortifagos = (BatallonIteratorSecuencial) batallonMortifagos.createIterator();
		

//		Random rand = new Random();
//		boolean flagTurno = rand.nextBoolean();
		
//		while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {

//			if (flagTurno) {
//				
//				while(iteradorMagos.hasNext()) {
//					Mago magoAtacante = (Mago)iteradorMagos.getNext();
//					Hechizo hechizoSeleccionado = magoAtacante.getHechizoAleatorioNoUsado(hechizosUsados);
//					Mortifago mortifagoDestino = (Mortifago)batallonMortifagos.getPersonajeVivoAleatorio();
//					if(hechizoSeleccionado == null) {
//						System.out.println("Personaje " + magoAtacante.getNombre() + " No tiene hechizos para lanzar");
//						break;
//					}
//					else if(mortifagoDestino != null) {
//						int danioHecho = magoAtacante.lanzarHechizo(hechizoSeleccionado, mortifagoDestino);
//						hechizosUsados.add(hechizoSeleccionado);
//						mostrarResultadoConsola(magoAtacante, hechizoSeleccionado, mortifagoDestino);
//					}
//				}
//				flagTurno = !flagTurno;
//				hechizosUsados.clear();
//			} else {
//				while(iteradorMortifagos.hasNext()) {
//					Mortifago mortifagoAtacante = (Mortifago)iteradorMortifagos.getNext();
//					Hechizo hechizoSeleccionado = mortifagoAtacante.getHechizoAleatorioNoUsado(hechizosUsados);
//					Mago magoDestino = (Mago)batallonMagos.getPersonajeVivoAleatorio();
//					if(hechizoSeleccionado == null) {
//						System.out.println("Personaje " + mortifagoAtacante.getNombre() + " No tiene hechizos para lanzar");
//						break;
//					}
//					else if(magoDestino != null) {
//						int danioHecho = mortifagoAtacante.lanzarHechizo(hechizoSeleccionado, magoDestino);
//						hechizosUsados.add(hechizoSeleccionado);
//						mostrarResultadoConsola(mortifagoAtacante, hechizoSeleccionado, magoDestino);
//					}
//				}
//				flagTurno = !flagTurno;	
//				hechizosUsados.clear();
//			}
			

//			System.out.println("----------------------------");
//		}

//		if (batallonMagos.tienePersonajesSaludables()) {
//			System.out.println("¡Los magos han ganado la batalla!");
//		} else {
//			System.out.println("¡Los mortífagos han ganado la batalla!");
//		}

	}

	private static Batallon crearBatallonMago() {
		Batallon batallonMago = new Batallon();
		for (int i = 0; i < 3; i++) {
			//batallonMago.agregarPersonaje(new Auror("Auror0" + i));
			batallonMago.agregarPersonaje(PersonajeFactory.crearMago(TipoMago.Auror, "Auror0" + i));
		}
		batallonMago.setTipoBatallonMago(true);
		return batallonMago;
	}

	private static Batallon crearBatallonMortifago() {
		Batallon batallonMortifago = new Batallon();
		for (int i = 0; i < 3; i++) {
			//batallonMortifago.agregarPersonaje(new Seguidor("Seguidor0" + i));
			batallonMortifago.agregarPersonaje(PersonajeFactory.crearMortifago(TipoMortifago.Seguidor, "Seguidor0" + i));
		}
		batallonMortifago.setTipoBatallonMortifago(true);
		return batallonMortifago;
	}
	
	private static void mostrarResultadoConsola(Personaje atacante, Hechizo hechizoLanzado, Personaje destino) {	// solo para test
		System.out.print(atacante + " -> " + hechizoLanzado + " -> " + destino);
		System.out.println("\t\t" + atacante.getPuntosDeVida() + " / " + destino.getPuntosDeVida());
	}

}
