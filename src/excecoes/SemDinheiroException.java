package excecoes;

public class SemDinheiroException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public SemDinheiroException(int dinheiro) {
		super(dinheiro + " é Insuficente");
	}
}
