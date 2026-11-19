package modelo.armas;

import excecoes.CampoInvalidoException;
import modelo.HabilidadeEspecial;

public class Espada extends Arma{
	
	private double danoCortante;

	public Espada(String nomeArma, double danoPrimario, double danoSecundario, HabilidadeEspecial habilidadeEspecial, double danoCortante) {
		super(nomeArma, danoPrimario, danoSecundario, habilidadeEspecial);
		setDanoCortante(danoCortante);
	}

	public double getDanoCortante() {
		return danoCortante;
	}

	public void setDanoCortante(double danoCortante) {
		if(danoCortante < 0) {
			throw new CampoInvalidoException("Dano Cortante");
		}
		this.danoCortante = danoCortante;
	}
}
