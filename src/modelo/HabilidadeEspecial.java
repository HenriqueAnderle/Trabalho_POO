package modelo;

import java.util.Random;

import excecoes.CampoInvalidoException;

public class HabilidadeEspecial {

	private double danoBase;
	private double precisao;
	private int energiaHabilidade;
	
	public HabilidadeEspecial(double danoBase, double precisao, int energiaHabilidade) {
		setDanoBase(danoBase);
		setPrecisao(precisao);
		setEnergiaHabilidade(energiaHabilidade);
	}
	
	public double getDanoBase() {
		return danoBase;
	}
	public void setDanoBase(double danoBase) {
		if(danoBase < 0) {
			throw new CampoInvalidoException("Dano Base");
		}
		this.danoBase = danoBase;
	}
	public double getPrecisao() {
		return precisao;
	}
	public void setPrecisao(double precisao) {
		if(precisao < 0) {
			throw new CampoInvalidoException("Precisão");
		}
		this.precisao = precisao;
	}
	public int getEnergiaHabilidade() {
		return energiaHabilidade;
	}
	public void setEnergiaHabilidade(int energiaHabilidade) {
		if(energiaHabilidade < 0) {
			throw new CampoInvalidoException("Energia Habilidade");
		}
		this.energiaHabilidade = energiaHabilidade;
	}
	
	public boolean verificarPrecisao() {
		
		Random random = new Random();
		int valorAleatorio = 1 + random.nextInt(100);
		
		if(precisao >= valorAleatorio) {
			return true;
		}
		return false;
	}
}
