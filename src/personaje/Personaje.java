package personaje;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import exception.PersonajeNuloException;
import exception.PersonajeHechizoNoDisponibleException;
import exception.PersonajeHechizoNuloException;
import hechizo.Hechizo;

public abstract class Personaje {

	protected String nombre;
	protected int nivelMagia;
	protected int puntosVida;
	protected boolean protegido;
	protected boolean armado;
	protected ArrayList<Hechizo> hechizos = new ArrayList<>();
	
	public abstract boolean esMago();
	public abstract boolean esMortifago();
	
	public Personaje(String nombre) {
		this.nombre = nombre;
		this.nivelMagia = 10;
		this.puntosVida = 100;
		this.protegido = false;	
		this.armado = true;	
	}
	
	public Personaje(String nombre, int nivelMagia, int puntosVida, boolean armado, boolean protegido) {
		this.nombre = nombre;
		this.nivelMagia = nivelMagia;
		this.puntosVida = puntosVida;
		this.armado = armado;
		this.protegido = protegido;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int lanzarHechizo(Hechizo hechizo, Personaje destino) {
		
		if(hechizo == null) {
			throw new PersonajeHechizoNuloException("El hechizo para lanzar no puede ser null.");
		}
		if(destino == null) {
			throw new PersonajeNuloException("El destino para lanzar hechizo no puede ser null.");
		}
		if(!hechizos.contains(hechizo)) {
			throw new PersonajeHechizoNoDisponibleException("No se puede lanzar un hechizo que no se dispone.");
		}
		return hechizo.ejecutar(this, destino);
	}
	
	public boolean agregarHechizo(Hechizo hechizo) {
		
		if(hechizo == null) {
			throw new PersonajeHechizoNuloException("El hechizo para agregar no puede ser null.");
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
		return puntosVida > 0;
	}
	
	public boolean recibirDanio(int danio) {
		
		if(danio <= 0) {
			return false;
		}
		if(!this.estaVivo()) {
			return false;
		}		
		this.puntosVida -= danio;
		return true;
	}
	
	public boolean curar(int valor) {
		if(valor <= 0) {
			return false;
		}
		if(!this.estaVivo()) {
			return false;
		}		
		this.puntosVida += valor;
		return true;
	}
	
	public int getPuntosVida() {
		return puntosVida;
	}
	
	public boolean getProtegido() {
		return protegido;
	}
	
	public void setProtegido(boolean valor) {
		this.protegido = valor;
	}
	
	public boolean getArmado() {
		return armado;
	}
	
	public void setArmado(boolean valor) {
		this.armado = valor;
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
		
		String str = nombre;		
		
		if(protegido) {
			str += "(p)";
		}
		if(!armado) {
			str += "(d)";
		}
		return str;
	}
	
}
