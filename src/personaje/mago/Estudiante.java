package personaje.mago;


public class Estudiante extends Mago {
	private static final int NIVEL_MAGICO = 3;

	public Estudiante(String nombre) {
        super(TipoMago.Estudiante, nombre);
    }
	
    public Estudiante(String nombrePersonaje,int puntosVida, boolean armado, boolean protegido) {
        super(TipoMago.Estudiante, nombrePersonaje, NIVEL_MAGICO, puntosVida, armado, protegido);
    }
	
	@Override
	public boolean esMago() {
		return true;
	}

	@Override
	public boolean esMortifago() {
		return false;
	}
}
