package excecoes;

public class VidaEnergiaCheiaException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;
	
	public VidaEnergiaCheiaException(String texto) {
		super(texto + " Cheia");
	}
}
