package modelo.classes;

import java.util.Random;

import enumerations.CondicaoEnum;
import excecoes.SemEnergiaException;
import modelo.Condicao;
import modelo.Dano;
import modelo.armas.Cajado;

public class Mago extends Classe{
	
	public Mago(String nome, double vidaMax, double vidaAtual, double precisao, int velocidade, Cajado cajado, int energiaAtual, int energiaMax, int energiaRecuperada) {
		super(nome, vidaMax, vidaAtual, precisao, velocidade, cajado, energiaAtual, energiaMax, energiaRecuperada);
	}

	@Override
	public Dano atacarPrimario() {
		
		double dano = 0;
		int energiaAtual = getEnergiaAtual();
		
		if(energiaAtual >= 30) {
			dano = getArma().getDanoPrimario();
			
			setEnergiaAtual(energiaAtual - 30);
		}else {
			throw new SemEnergiaException("Mana");
		}
		
		if(!verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
				
		return new Dano(dano, false, "Ataque Primário com Cajado");
	}

	@Override
	public Dano atacarSecundario() {
		
		if(!verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
				
		double dano = getArma().getDanoSecundario();
		int energiaAtual = getEnergiaAtual();
		
		setEnergiaAtual(energiaAtual + getEnergiaRecuperada());
		
		return new Dano(dano, false, "Ataque Primário com Cajado");
	}
	
	@Override
	public Dano atacarEspecial() {
		
		if(!verificarHabilidadeEspecialCarregada()) {
			throw new SemEnergiaException("Mana");
		}
		
		if(!getArma().getHabilidadeEspecial().verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
		
		double danoTotal = 0;
		int energiaAtual = getEnergiaAtual();
		
		Dano dano = new Dano(danoTotal, true, "Habilidade Especial - Meteoro Carmesim");
		
		dano.setDano(getArma().getHabilidadeEspecial().getDanoBase());
			
		Random random = new Random();
		int valorRandom = 1 + random.nextInt(4);
		if(valorRandom == 1) {
			dano.setCondicao(new Condicao(CondicaoEnum.EM_CHAMAS, 3));
		}
			
		setEnergiaAtual(energiaAtual - getArma().getHabilidadeEspecial().getEnergiaHabilidade());
		
		return dano;
	}

	@Override
	public void fimDeTurno(double dano) {
		
		int energiaAtual = getEnergiaAtual();
		
		if(energiaAtual + getEnergiaRecuperada() > getEnergiaMax()) {
			setEnergiaAtual(getEnergiaMax());
		}else {
			setEnergiaAtual(energiaAtual + getEnergiaRecuperada());
		}
		
	}	
}
