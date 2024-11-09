package personaje;

import java.util.ArrayList;
import java.util.Objects;

import hechizo.Hechizo;

public abstract class Personaje {

	protected String nombre;
	protected int nivelDeMagia;
	protected int puntosDeVida;
	protected ArrayList<Hechizo> hechizos = new ArrayList<> ();
	
	public Personaje(String nombre) {
		
		this.nombre = nombre;
		this.puntosDeVida = 100;
		
	}
	
	public int lanzarHechizo (Hechizo hechizo, Personaje destino) {
		
		
		return 0;
		
	}
	
	public boolean agregarHechizo(Hechizo hechizo) {
		
		return true;
	}
	
	public boolean recibirDanio(int danio) {
		
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personaje other = (Personaje) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Personaje [nombre=" + nombre + "]";
	}
	
	
	

	
	
}
