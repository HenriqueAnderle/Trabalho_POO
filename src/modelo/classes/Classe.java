package modelo.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import excecoes.CampoInvalidoException;
import modelo.Dano;
import modelo.armas.Arma;
import modelo.itens.Item;
import modelo.itens.PocaoDeCura;

public abstract class Classe {
	
	private String nome;
	private double vidaMax;
	private double vidaAtual;
	private double precisao;
	private int velocidade;
	private Arma arma;
	private List<Item> inventario;
	
	private int energiaAtual;
	private int energiaMax;
	private int energiaRecuperada;
	
	private double vidaDefesa;
	
	public Classe(String nome, double vidaMax, double vidaAtual, double precisao, int velocidade, Arma arma, int energiaAtual, int energiaMax, 
			int energiaRecuperada) {
		setNome(nome);
		setVidaMax(vidaMax);
		setVidaAtual(vidaAtual);
		setPrecisao(precisao);
		setVelocidade(velocidade);
		setArma(arma);
		setEnergiaAtual(energiaAtual);
		setEnergiaMax(energiaMax);
		setEnergiaRecuperada(energiaRecuperada);
		setVidaDefesa(0);
		
		inventario = new ArrayList<Item>();
		
		adicionarItem(new PocaoDeCura());
		adicionarItem(new PocaoDeCura());
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome == null || nome.isBlank() || nome.length() < 3) {
			throw new CampoInvalidoException("Nome");
		}
		this.nome = nome;
	}
	public double getVidaMax() {
		return vidaMax;
	}
	public void setVidaMax(double vidaMax) {
		if(vidaMax < 0) {
			throw new CampoInvalidoException("Vida Máxima");
		}
		this.vidaMax = vidaMax;
	}
	public double getVidaAtual() {
		return vidaAtual;
	}
	public void setVidaAtual(double vidaAtual) {
		this.vidaAtual = vidaAtual;
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
	public int getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(int velocidade) {
		if(velocidade < 0) {
			throw new CampoInvalidoException("Velocidade");
		}
		this.velocidade = velocidade;
	}
	public Arma getArma() {
		return arma;
	}
	public void setArma(Arma arma) {
		this.arma = arma;
	}
	public List<Item> getInventario() {
		return inventario;
	}
	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
	}
	public int getEnergiaAtual() {
		return energiaAtual;
	}
	public void setEnergiaAtual(int energiaAtual) {
		if(energiaAtual < 0) {
			throw new CampoInvalidoException("Energia Atual");
		}
		this.energiaAtual = energiaAtual;
	}
	public int getEnergiaMax() {
		return energiaMax;
	}
	public void setEnergiaMax(int energiaMax) {
		if(energiaMax < 0) {
			throw new CampoInvalidoException("Energia Máxima");
		}
		this.energiaMax = energiaMax;
	}
	public int getEnergiaRecuperada() {
		return energiaRecuperada;
	}
	public void setEnergiaRecuperada(int energiaRecuperada) {
		if(energiaRecuperada < 0) {
			throw new CampoInvalidoException("Energia Recuperada");
		}
		this.energiaRecuperada = energiaRecuperada;
	}
	public double getVidaDefesa() {
		return vidaDefesa;
	}
	public void setVidaDefesa(double vidaDefesa) {
		if(energiaRecuperada < 0) {
			throw new CampoInvalidoException("Vida Defesa");
		}
		this.vidaDefesa = vidaDefesa;
	}
	public void adicionarItem(Item item) {
		inventario.add(item);
	}
	public void removerItem(Item item) {
		int index = inventario.indexOf(item);
		
		if(index >= 0) {
			inventario.remove(index);
		}else {
			throw new NullPointerException();
		}
	}

	public Dano defender() {
		
		//Gera Uma Porcentagem de Até 50% do Dano que vai Ser Ignorado
		Random random = new Random();
		int valorDefesa = 1 + random.nextInt(20);
		
		//double danoTotal = dano.getDano() - (dano.getDano() * ((double)valorDefesa / 100));
			
		vidaDefesa = valorDefesa;
		
		return new Dano(0 , false, "Defesa de " + vidaDefesa + " Pontos");
	}
	
	public Dano esquivar() {
		
		//Gera um Número de 1 a 4 para determinar a chance de Esquivar
		Random random = new Random();
		double chanceEsquiva = 1 + random.nextInt(6);
				
		//A Velocidade Determina se a Entidade Esquivou ou não
		if(velocidade >= chanceEsquiva) {
			vidaDefesa = 500;
			
			return new Dano(0, false, "Esquivou");
		}
						
		return new Dano(-1, false, "Esquivou");
	}
	
	public abstract Dano atacarPrimario();
	
	public abstract Dano atacarSecundario();
	
	public abstract Dano atacarEspecial();
	
	public boolean verificarHabilidadeEspecialCarregada() {
		
		if(energiaAtual >= arma.getHabilidadeEspecial().getEnergiaHabilidade()) {
			return true;
		}
		return false;
	}
	
	//Esse vai ser o dano que vai ser aplicado a quem está sendo atacado
	public abstract void fimDeTurno(double dano);
	
	public boolean verificarPrecisao() {
		
		Random random = new Random();
		int valorAleatorio = 1 + random.nextInt(100);
		System.out.println(valorAleatorio);
		
		if(precisao >= valorAleatorio) {
			return true;
		}
		return false;
	}
}
