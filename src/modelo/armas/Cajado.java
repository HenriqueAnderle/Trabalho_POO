package modelo.armas;

import excecoes.CampoInvalidoException;
import modelo.HabilidadeEspecial;

public class Cajado extends Arma{

	private double danoContinuo;
	
	public Cajado(String nomeArma, double danoPrimario, double danoSecundario, HabilidadeEspecial habilidadeEspecial, double danoContinuo) {
		super(nomeArma, danoPrimario, danoSecundario, habilidadeEspecial);
		setDanoContinuo(danoContinuo);
	}

	public double getDanoContinuo() {
		return danoContinuo;
	}

	public void setDanoContinuo(double danoContinuo) {
		if(danoContinuo < 0) {
			throw new CampoInvalidoException("Dano Contínuo");
		}
		this.danoContinuo = danoContinuo;
	}
}
