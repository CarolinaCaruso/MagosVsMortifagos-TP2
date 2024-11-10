package hechizo;

public class HechizoFactory {

	public static Hechizo crearHechizo(TipoHechizo tipo) {
		switch (tipo) {
		case TipoHechizo.Ataque10:
			return new Ataque10();
		
		case TipoHechizo.Ataque20:
			return new Ataque20();
			
		case TipoHechizo.Ataque30:
			return new Ataque30();
			
		case TipoHechizo.Curacion:
			return new Curacion();

		case TipoHechizo.Proteccion:
			return new Proteccion();
			
		case TipoHechizo.Desarmar:
			return new Desarmar();

		default:
			throw new IllegalArgumentException("Tipo de hechizo desconocido");
		}
	}
}
