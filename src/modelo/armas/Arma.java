package modelo.armas;

import excecoes.CampoInvalidoException;
import modelo.HabilidadeEspecial;

public abstract class Arma {

	private String nomeArma;
	private double danoPrimario;
	private double danoSecundario;
	private HabilidadeEspecial habilidadeEspecial;
	
	public Arma(String nomeArma, double danoPrimario, double danoSecundario, HabilidadeEspecial habilidadeEspecial) {
		setNomeArma(nomeArma);
		setDanoPrimario(danoPrimario);
		setDanoSecundario(danoSecundario);
		setHabilidadeEspecial(habilidadeEspecial);
	}
	
	public String getNomeArma() {
		return nomeArma;
	}
	public void setNomeArma(String nomeArma) {
		if(nomeArma == null || nomeArma.isBlank() || nomeArma.length() < 3) {
			throw new CampoInvalidoException("Nome");
		}
		this.nomeArma = nomeArma;
	}
	
	public double getDanoPrimario() {
		return danoPrimario;
	}

	public void setDanoPrimario(double danoPrimario) {
		if(danoPrimario < 0) {
			throw new CampoInvalidoException("Dano Primário");
		}
		this.danoPrimario = danoPrimario;
	}

	public double getDanoSecundario() {
		return danoSecundario;
	}

	public void setDanoSecundario(double danoSecundario) {
		if(danoSecundario < 0) {
			throw new CampoInvalidoException("Dano Secundário");
		}
		this.danoSecundario = danoSecundario;
	}

	public HabilidadeEspecial getHabilidadeEspecial() {
		return habilidadeEspecial;
	}
	public void setHabilidadeEspecial(HabilidadeEspecial habilidadeEspecial) {
		this.habilidadeEspecial = habilidadeEspecial;
	}
}
