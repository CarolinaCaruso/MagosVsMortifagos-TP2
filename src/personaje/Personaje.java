package personaje;

import java.util.ArrayList;
import java.util.Objects;

import hechizo.Hechizo;

public abstract class Personaje {

	protected String nombre;
	protected int nivelDeMagia;
	protected int puntosDeVida;
	protected boolean protegido;
	protected ArrayList<Hechizo> hechizos = new ArrayList<> ();
	
	public Personaje(String nombre) {
		
		this.nombre = nombre;
		this.puntosDeVida = 100;
		this.protegido = false;		
	}
	
	public int lanzarHechizo (Hechizo hechizo, Personaje destino) {
		
		if(hechizo == null) {
			// Excepcion
		}
		if(destino == null) {
			// Excepcion
		}
		return hechizo.ejecutar(this, destino);		
	}
	
	public boolean agregarHechizo(Hechizo hechizo) {
		
		if(hechizo == null) {
			// Excepcion
		}
		if(hechizos.contains(hechizo)) {
			return false;
		}
		hechizos.add(hechizo);
		return true;		
	}
	
	public boolean recibirDanio(int danio) {
		
		if(danio <= 0) {
			return false;
		}
		this.puntosDeVida -= danio;
		return true;
	}
	
	public boolean curar(int valor) {
		if(valor <= 0) {
			return false;
		}
		this.puntosDeVida += valor;
		return true;
	}
	
	public boolean getProtegido() {
		return protegido;
	}
	
	public void setProtegido(boolean valor) {
		this.protegido = valor;
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
