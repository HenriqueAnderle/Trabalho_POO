package modelo.classes;

import java.util.Random;

import enumerations.CondicaoEnum;
import excecoes.CampoInvalidoException;
import excecoes.SemEnergiaException;
import modelo.Condicao;
import modelo.Dano;
import modelo.armas.Arma;
import modelo.armas.Espada;

public class Guerreiro extends Classe {

	private int armadura;
	
	public Guerreiro(String nome, double vidaMax, double vidaAtual, double precisao, int velocidade, Espada espada, int energiaAtual, int energiaMax, 
			int energiaRecuperada, int armadura) {
		super(nome, vidaMax, vidaAtual, precisao, velocidade, espada, energiaAtual, energiaMax, energiaRecuperada);
		setArmadura(armadura);
	}

	public int getArmadura() {
		return armadura;
	}

	public void setArmadura(int armadura) {
		if(armadura < 0) {
			throw new CampoInvalidoException("Armadura");
		}
		this.armadura = armadura;
	}

	public Dano atacarPrimario() {
		
		if(!verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
				
		double dano = getArma().getDanoPrimario();
	
		return new Dano(dano, false, "Ataque Primário com Espada");
	}
	
	public Dano atacarSecundario() {
		
		double danoTotal = 0;
		int energiaAtual = getEnergiaAtual();
		
		Dano dano = new Dano(danoTotal, false, "Ataque Secundário com Espada");
		
		if(energiaAtual >= 30) {
		
			danoTotal = getArma().getDanoSecundario();
			
			setEnergiaAtual(energiaAtual - 30);
			
			dano.setDano(danoTotal);
			
			Random random = new Random();
			int valorRandom = random.nextInt(2);
			if(valorRandom == 1) {
				dano.setCondicao(new Condicao(CondicaoEnum.SANGRANDO, 2));
			}
		}else {
			throw new SemEnergiaException("Fúria");
		}
		
		if(!verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
		
		return dano;
	}
	
	@Override
	public Dano atacarEspecial() {
		
		if(!verificarHabilidadeEspecialCarregada()) {
			throw new SemEnergiaException("Fúria");
		}
		
		if(!getArma().getHabilidadeEspecial().verificarPrecisao()) {
			return new Dano(0, false, "Errou o Golpe");
		}
		
		double danoTotal = 0;
		int energiaAtual = getEnergiaAtual();
		
		Dano dano = new Dano(danoTotal, true, "Habilidade Espeicial - Furia Ensandecida");
		
		dano.setDano(getArma().getHabilidadeEspecial().getDanoBase());
			
		setEnergiaAtual(energiaAtual - getArma().getHabilidadeEspecial().getEnergiaHabilidade());
		
		return dano;
	}
	
	public void fimDeTurno(double dano) {
		
		int energiaAtual = getEnergiaAtual();
		
		//Se Guerreiro Não Ataca, Ele Perde Energia
		if(dano <= 0) {
			if(energiaAtual - 10 >= 0) {
				setEnergiaAtual(energiaAtual - 10);
			}else {
				setEnergiaAtual(0);
			}
		}else {
			if(energiaAtual < getEnergiaMax()) {
				setEnergiaAtual(energiaAtual + 10);
				if(getEnergiaAtual() > getEnergiaMax()) {
					setEnergiaAtual(getEnergiaMax());
				}
			}
		}
	}
	
	@Override
	public Dano defender() {
		
		//Gera Uma Porcentagem de Até 50% do Dano que vai Ser Ignorado
		Random random = new Random();
		int valorDefesa = 1 + random.nextInt(20);
		
		valorDefesa += armadura;
		
		setVidaDefesa(valorDefesa);
				
		return new Dano(0, false,"Defesa de " + getVidaDefesa() + " Pontos");
	}
}
