package modelo.armas;

import excecoes.CampoInvalidoException;
import modelo.HabilidadeEspecial;

public class Arco extends Arma{

	private double danoEnvenamento;
	
	public Arco(String nomeArma, double danoPrimario, double danoSecundario, HabilidadeEspecial habilidadeEspecial, double danoEnvenamento) {
		super(nomeArma, danoPrimario, danoSecundario, habilidadeEspecial);
		setDanoEnvenamento(danoEnvenamento);
	}

	public double getDanoEnvenamento() {
		return danoEnvenamento;
	}

	public void setDanoEnvenamento(double danoEnvenamento) {
		if(danoEnvenamento < 0) {
			throw new CampoInvalidoException("Dano Envenenamento");
		}
		this.danoEnvenamento = danoEnvenamento;
	}
}
