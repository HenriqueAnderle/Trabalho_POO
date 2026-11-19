package modelo.classes;

import java.util.Random;

import enumerations.CondicaoEnum;
import excecoes.SemEnergiaException;
import modelo.Condicao;
import modelo.Dano;
import modelo.armas.Arco;

public class Arqueiro extends Classe{
	
	public Arqueiro(String nome, double vidaMax, double vidaAtual, double precisao, int velocidade, Arco arco, int energiaAtual, int energiaMax, int energiaRecuperada) {
		super(nome, vidaMax, vidaAtual, precisao, velocidade, arco, energiaAtual, energiaMax, energiaRecuperada);
	}

	public Dano atacarPrimario() throws SemEnergiaException{
		
		double dano = 0;
		
		int energiaAtual = getEnergiaAtual();
		
		if(energiaAtual > 0) {
			dano = getArma().getDanoPrimario();
			setEnergiaAtual(energiaAtual - 1);
		}else {
			throw new SemEnergiaException("Flechas");
		}
		
		if(!verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
		
		return new Dano(dano, true, "Ataque Primário com Arco");
	}
	
	public Dano atacarSecundario() {
		
		if(!verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
		
		double dano = getArma().getDanoSecundario();
		int energiaAtual = getEnergiaAtual();
		
		setEnergiaAtual(energiaAtual + 3);
		
		if(energiaAtual > getEnergiaMax()) {
			setEnergiaAtual(getEnergiaMax());
		}
		
		return new Dano(dano, false, "Ataque Secundário com Arco");
	}
	
	public Dano atacarEspecial() {
		
		if(!verificarHabilidadeEspecialCarregada()) {
			throw new SemEnergiaException("Flechas");
		}
		
		if(!getArma().getHabilidadeEspecial().verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
		
		double danoTotal = 0;
		int energiaAtual = getEnergiaAtual();
		
		Dano dano = new Dano(danoTotal, true, "Habilidade Espacial - Saraivada de Flechas");
		
		dano.setDano(getArma().getHabilidadeEspecial().getDanoBase());
			
		Random random = new Random();
		int valorRandom = 1 + random.nextInt(4);
		if(valorRandom == 1) {
			dano.setCondicao(new Condicao(CondicaoEnum.ENVENENAMENTO, 3));
		}
			
		setEnergiaAtual(energiaAtual - getArma().getHabilidadeEspecial().getEnergiaHabilidade());
		
		return dano;
	}

	@Override
	public void fimDeTurno(double dano) {
	}
}
