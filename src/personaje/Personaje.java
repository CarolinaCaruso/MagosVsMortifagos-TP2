package personaje;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;

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
	
	public String getNombre() {
		return nombre;
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
	
	public Hechizo getHechizoAleatorioNoUsado(Collection<Hechizo> hechizosUsados) {
		ArrayList<Hechizo> lista = new ArrayList<>(hechizos);
		for(Hechizo usado : hechizosUsados) {
			lista.remove(usado);
		}
		if(lista.isEmpty()) {
			return null;
		}
		Hechizo[] vector = lista.toArray(new Hechizo[0]);
		Random random = new Random();
		return vector[random.nextInt(vector.length)];
	}
	
	public boolean estaVivo() {
		return puntosDeVida > 0;
	}
	
	public boolean recibirDanio(int danio) {
		
		if(danio <= 0) {
			return false;
		}
		if(!this.estaVivo()) {
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
	
	public int getPuntosDeVida() {
		return puntosDeVida;
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
		return nombre;
	}
	
}
