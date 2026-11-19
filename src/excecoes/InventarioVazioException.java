package excecoes;

public class InventarioVazioException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public InventarioVazioException() {
		super("Invetário Vazio");
	}
}
