package hechizo;

import java.util.Objects;

public abstract class HechizoConcreto implements Hechizo {
	
	protected TipoHechizo tipo;
	protected boolean dirigidoOponente;
	
	public TipoHechizo getTipo() {
		return this.tipo;
	}
	
	public abstract boolean esDirigidoOponente();
	
	@Override
	public int hashCode() {
		return Objects.hash(tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HechizoConcreto other = (HechizoConcreto) obj;
		return tipo == other.tipo;
	}
}
