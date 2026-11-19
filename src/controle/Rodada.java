package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import enumerations.CondicaoEnum;
import excecoes.InventarioVazioException;
import excecoes.SemEnergiaException;
import excecoes.VidaEnergiaCheiaException;
import modelo.Condicao;
import modelo.Dano;
import modelo.HabilidadeEspecial;
import modelo.armas.Arco;
import modelo.armas.Cajado;
import modelo.armas.Espada;
import modelo.classes.Arqueiro;
import modelo.classes.Entidade;
import modelo.classes.Guerreiro;
import modelo.classes.Jogador;
import modelo.classes.Mago;
import modelo.inimigos.Inimigo;

public class Rodada {

	private int fase;
	private int quantidadeTurnos;
	private int pontuacaoRodada;
	private Inimigo inimigo;
	private List<Integer> turnos;
	
	private String mensagem;
	
	
	public Rodada(int fase, int quantidadeTurnos) {
		setFase(fase);
		setQuantidadeTurnos(quantidadeTurnos);
		setPontuacaoRodada(0);
		inimigo = new Inimigo(null);
		turnos = new ArrayList<Integer>();
	}
	
	public int getQuantidadeTurnos() {
		return quantidadeTurnos;
	}
	public void setQuantidadeTurnos(int quantidadeTurnos) {
		this.quantidadeTurnos = quantidadeTurnos;
	}
	public int getPontuacaoRodada() {
		return pontuacaoRodada;
	}
	public void setPontuacaoRodada(int pontuacaoRodada) {
		this.pontuacaoRodada = pontuacaoRodada;
	}
	public Inimigo getInimigo() {
		return inimigo;
	}
	public void setInimigo(Inimigo inimigo) {
		this.inimigo = inimigo;
	}
	public List<Integer> getTurnos() {
		return turnos;
	}
	public void setTurnos(List<Integer> turnos) {
		this.turnos = turnos;
	}
	public int getFase() {
		return fase;
	}
	public void setFase(int fase) {
		this.fase = fase;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void iniciarRodada(Jogador jogador) {
		
		Random random = new Random();
		int valorRandom = 1 + random.nextInt(3);
		
		//Escolhe Aleatoriamente o Inimigo da Rodada
		if(valorRandom == 1) {
			inimigo.setClasse(new Guerreiro("Ka-Zar", 100, 100, 70, 1, new Espada("Zabu", 20, 40, new HabilidadeEspecial(100, 80, 100), 10), 50, 100, 5, 10));
		}else if(valorRandom == 2) {
			inimigo.setClasse(new Mago("Gorrok", 100, 100, 80, 1, new Cajado("Fogo", 20, 10, new HabilidadeEspecial(100, 80, 100), 10), 50, 100, 20));
		}else {
			inimigo.setClasse(new Arqueiro("Karl", 100, 100, 80, 2, new Arco("Sauron", 20, 10, new HabilidadeEspecial(100, 80, 100), 10), 5, 10, 1));
		}
		
		//Define a Dificuldade do Inimigo da Rodada
		if(fase == 2) {
			//TODO Balancear os diferentes níveis do 2 e do 3 em diante
			if(inimigo.getClasse() instanceof Guerreiro) {
				inimigo.setClasse(new Guerreiro("Ka-Zar", 100, 100, 70, 1, new Espada("Zabu", 20, 40, new HabilidadeEspecial(100, 80, 100), 10), 50, 100, 20, 10));
			}else if(inimigo.getClasse() instanceof Mago) {
				inimigo.setClasse(new Mago("Gorrok", 100, 100, 80, 1, new Cajado("Fogo", 20, 10, new HabilidadeEspecial(100, 80, 100), 10), 50, 100, 20));
			}else if(inimigo.getClasse() instanceof Arqueiro) {
				inimigo.setClasse(new Arqueiro("Karl", 100, 100, 80, 2, new Arco("Sauron", 20, 10, new HabilidadeEspecial(100, 80, 100), 10), 5, 10, 1));
			}
		}else if(fase == 3){
			if(inimigo.getClasse() instanceof Guerreiro) {
				inimigo.setClasse(new Guerreiro("Ka-Zar", 100, 100, 70, 1, new Espada("Zabu", 20, 40, new HabilidadeEspecial(100, 80, 100), 10), 50, 100, 20, 10));
			}else if(inimigo.getClasse() instanceof Mago) {
				inimigo.setClasse(new Mago("Gorrok", 100, 100, 80, 1, new Cajado("Fogo", 20, 10, new HabilidadeEspecial(100, 80, 100), 10), 50, 100, 20));
			}else if(inimigo.getClasse() instanceof Arqueiro) {
				inimigo.setClasse(new Arqueiro("Karl", 100, 100, 80, 2, new Arco("Sauron", 20, 10, new HabilidadeEspecial(100, 80, 100), 10), 5, 10, 1));
			}
		}
		
		atualizarTurno(jogador);
	}
	
	public void atualizarTurno(Jogador jogador) {
		
		int velocidadeJogador = jogador.getClasse().getVelocidade();
		int velocidadeInimigo = inimigo.getClasse().getVelocidade();
		
		switch(velocidadeJogador) {
			case 6:
				turnos.add(0);
			case 5:
			case 4:
			case 3:
				turnos.add(0);
			case 2:
			case 1:
				turnos.add(0);
		}
		switch(velocidadeInimigo) {
			case 6:
				turnos.add(1);
			case 5:
			case 4:
			case 3:
				turnos.add(1);
			case 2:
			case 1:
				turnos.add(1);
		}
	}
	
	public boolean escolherAcaoAtaque(Entidade entidade, int turno, int escolha) {
		
		try {
			if (turno == 1) {
				escolha = 1 + new Random().nextInt(5);
			}
			switch (escolha) {
				case 1: 
					atacarPrimarioAcao(entidade, turno); 
					break;
				case 2: 
					atacarSecudarioAcao(entidade, turno); 
					break;
				case 3: 
					atacarEspecialAcao(entidade, turno); 
					break;
				case 4: 
					defenderAcao(entidade, turno); 
					break;
				case 5: 
					esquivarAcao(entidade, turno); 
					break;
				case 6: 
					usarItemAcao(entidade, turno); 
					break;
			}
		} catch (SemEnergiaException | InventarioVazioException e) {
			if (turno == 1) {
				return escolherAcaoAtaque(entidade, turno, 0);
			}
			mensagem = e.getMessage();
			return false;
		}
		quantidadeTurnos++;
		return true;
	}
	
	/*
	public double escolherAcaoDefesa(Entidade entidade, Dano dano) {
		
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		double danoTotal = 0;
		
		if(entidade instanceof Jogador) {
			System.out.println("Informe 1 - Defender, 2 - Esquivar");
			int escolha = scanner.nextInt();
			
			switch(escolha) {
				
				case 1:
					danoTotal = defenderAcao(entidade, dano);
					break;
					
				case 2:
					danoTotal = esquivarAcao(entidade, dano);
					break;
					
			}
		}else if(entidade instanceof Inimigo) {
			int escolhaAleatoria = 1 + random.nextInt(2);
			
			switch(escolhaAleatoria) {
				
				case 1:
					danoTotal = defenderAcao(entidade, dano);
					break;
					
				case 2:
					danoTotal = esquivarAcao(entidade, dano);
					break;	
			}
		}
		
		return danoTotal;
	}
	*/

	private void atacarPrimarioAcao(Entidade jogador, int turno) {
		
		if(turno == 0) {
			Dano dano = jogador.getClasse().atacarPrimario();
			
			acaoAtaqueJogador(jogador, dano);
						
		}else if(turno == 1) {
			
			Dano dano = inimigo.getClasse().atacarPrimario();
			
			acaoAtaqueInimigo(jogador, dano);
		}
	}
	
	private void atacarSecudarioAcao(Entidade jogador, int turno) {
		
		if(turno == 0) {
			Dano dano = jogador.getClasse().atacarSecundario();
			
			acaoAtaqueJogador(jogador, dano);
			
		}else if(turno == 1) {
			
			Dano dano = inimigo.getClasse().atacarSecundario();
			
			acaoAtaqueInimigo(jogador, dano);
		}
	}
	
	private void atacarEspecialAcao(Entidade jogador, int turno) {

		if(turno == 0) {
			Dano dano = jogador.getClasse().atacarEspecial();
			
			acaoAtaqueJogador(jogador, dano);
			
		}else if(turno == 1) {
			Dano dano = inimigo.getClasse().atacarEspecial();
			
				acaoAtaqueInimigo(jogador, dano);
		}
	}
	
	private void defenderAcao(Entidade jogador, int turno) {
		
		if(turno == 0) {
			Dano dano = jogador.getClasse().defender();
			
			acaoAtaqueJogador(jogador, dano);
			
		}else if(turno == 1) {
			Dano dano = inimigo.getClasse().defender();
			
			acaoAtaqueInimigo(jogador, dano);
		}
	}
	
	private void esquivarAcao(Entidade jogador, int turno) {
		
		if(turno == 0) {
			Dano dano = jogador.getClasse().esquivar();
			
			acaoAtaqueJogador(jogador, dano);
			
		}else if(turno == 1) {
			Dano dano = inimigo.getClasse().esquivar();
			
			acaoAtaqueInimigo(jogador, dano);
		}
	}
	
	//Esse método é exatamente igual do Intervalo, não sei se precisa rever isso ou não mas é uma solução
	private void usarItemAcao(Entidade jogador, int turno) {
		
		if(turno == 0) {
			
			Scanner scanner = new Scanner(System.in);
			
			int escolha = 0;
			
			System.out.println(jogador.getClasse().getInventario());
			
			while(escolha != -1) {
				String textoEscolha = "";
				
				if(!jogador.getClasse().getInventario().isEmpty()) {
				
					for(int i = 0; i < jogador.getClasse().getInventario().size(); i++) {
						if(jogador.getClasse().getInventario().get(i).isUsavelIntervalo()) {
							textoEscolha += i + " - "+ jogador.getClasse().getInventario().get(i).getClass().getSimpleName() + ", ";
						}else {
							
						}
					}
					
					textoEscolha += "-1 Para Sair";
					
					System.out.println(textoEscolha);
					escolha = scanner.nextInt();
					if(escolha >= 0) {
						
						try {
							jogador.getClasse().getInventario().get(escolha).usarItem(jogador, inimigo);
							jogador.getClasse().removerItem(jogador.getClasse().getInventario().get(escolha));
						}catch(VidaEnergiaCheiaException vidaEnergiaCheiaException) {
							System.out.println(vidaEnergiaCheiaException.getMessage());
						}
					}
				}else {
					throw new InventarioVazioException();
				}
			}
			
		}else if(turno == 1) {
			
			Random random = new Random();
			
			int escolhaAleatoria = 0;
			
			if(!inimigo.getClasse().getInventario().isEmpty()) {
				escolhaAleatoria = random.nextInt(inimigo.getClasse().getInventario().size());
			}else {
				throw new InventarioVazioException();
			}
			
			try {
				jogador.getClasse().getInventario().get(escolhaAleatoria).usarItem(inimigo, jogador);
				inimigo.getClasse().removerItem(inimigo.getClasse().getInventario().get(escolhaAleatoria));
			}catch(VidaEnergiaCheiaException vidaEnergiaCheiaException) {
				System.out.println(vidaEnergiaCheiaException.getMessage());
			}
		}
	}
	
	//A condição é aplicada a Primeira entidade e a segunda é de onde vem a condição
	private double verificarCondicaoFimTurno(Entidade entidade, Entidade inimigo) {
		
		if(entidade.getCondicao().getCondicao().equals(CondicaoEnum.NADA)) {
			return 0;
		}
		if(entidade.getCondicao().getCondicao().equals(CondicaoEnum.SANGRANDO)) {
			//Verificando se acabou a duração da Condição
			if(entidade.getCondicao().getDuracao() == 0) {
				entidade.setCondicao(new Condicao(CondicaoEnum.NADA));
				
				return 0;
			}
			Espada espada = (Espada) inimigo.getClasse().getArma();
			
			int duracao = entidade.getCondicao().getDuracao();
			entidade.getCondicao().setDuracao(duracao - 1);;
			
			return espada.getDanoCortante();
		}
		if(entidade.getCondicao().getCondicao().equals(CondicaoEnum.EM_CHAMAS)) {
			//Verificando se acabou a duração da Condição
			if(entidade.getCondicao().getDuracao() == 0) {
				entidade.setCondicao(new Condicao(CondicaoEnum.NADA));
				
				return 0;
			}
			
			Cajado cajado = (Cajado) inimigo.getClasse().getArma();
			
			int duracao = entidade.getCondicao().getDuracao();
			entidade.getCondicao().setDuracao(duracao - 1);;
			
			return cajado.getDanoContinuo();
		}
		if(entidade.getCondicao().getCondicao().equals(CondicaoEnum.ENVENENAMENTO)) {
			//Verificando se acabou a duração da Condição
			if(entidade.getCondicao().getDuracao() == 0) {
				entidade.setCondicao(new Condicao(CondicaoEnum.NADA));
				
				return 0;
			}
			
			Arco arco = (Arco) inimigo.getClasse().getArma();
			
			int duracao = entidade.getCondicao().getDuracao();
			entidade.getCondicao().setDuracao(duracao - 1);;
			
			return arco.getDanoEnvenamento();
		}
		return 0;
	}
	
	/*
	
	private double defenderAcao(Entidade entidade, Dano dano) {
		
		return entidade.getClasse().defender(entidade.getClasse().getArma(), dano);
	}
	
	private double esquivarAcao(Entidade entidade, Dano dano) {
		
		return entidade.getClasse().esquivar(entidade.getClasse().getArma(), dano);
	}
	
	*/
	
	private void aplicarCondicao(Dano dano, Entidade entidade) {
		
		if(!dano.getCondicao().getCondicao().equals(CondicaoEnum.NADA)){
			entidade.setCondicao(dano.getCondicao());
		}
	}
	
	private void acaoAtaqueJogador(Entidade jogador, Dano dano) {
		aplicarCondicao(dano, inimigo);
		
		double danoTotal = 0;
		
		//Vendo Se errou o Golpe
		if(!(dano.getDano() == 0) && !dano.isPerfurante()) {
			
			double danoResistido = dano.getDano() - inimigo.getClasse().getVidaDefesa();
			
			if(danoResistido > 0) {
				danoTotal = danoResistido;
			}else {
				danoTotal = 0;
			}
		}else if(dano.isPerfurante() && inimigo.getClasse() instanceof Guerreiro) {
			Guerreiro guerreiro = (Guerreiro) inimigo.getClasse(); 
			
			danoTotal = dano.getDano() - (inimigo.getClasse().getVidaDefesa() - guerreiro.getArmadura());
			
			double danoResistido = dano.getDano() - (inimigo.getClasse().getVidaDefesa() - guerreiro.getArmadura());
			
			if(danoResistido > 0) {
				danoTotal = danoResistido;
			}else {
				danoTotal = 0;
			}
		}
		inimigo.getClasse().setVidaDefesa(0);
		
		//Atualizando a Pontuação
		int pontuacaoTotal = (int) (danoTotal * 10);
		atualizarPontuacaoRodada(pontuacaoTotal);
		
		double vidaInimigo = inimigo.getClasse().getVidaAtual();
		
		double danoCondicao = verificarCondicaoFimTurno(inimigo, jogador);
		
		inimigo.getClasse().setVidaAtual(vidaInimigo - (danoTotal + danoCondicao));
		
		mensagem = montarMensagem(jogador.getClasse().getNome(), dano, danoTotal, danoCondicao);
		
		jogador.getClasse().fimDeTurno(danoTotal + danoCondicao);
	}
	
	private void acaoAtaqueInimigo(Entidade jogador, Dano dano) {
		aplicarCondicao(dano, jogador);
		
		double danoTotal = 0;
		
		//Vendo Se errou o Golpe
		if(!(dano.getDano() == 0) && !dano.isPerfurante()) {
			
			double danoResistido = dano.getDano() - jogador.getClasse().getVidaDefesa();
			
			if(danoResistido > 0) {
				danoTotal = danoResistido;
			}else {
				danoTotal = 0;
			}
		}else if(dano.isPerfurante() && jogador.getClasse() instanceof Guerreiro) {
			Guerreiro guerreiro = (Guerreiro) jogador.getClasse(); 
			
			danoTotal = dano.getDano() - (jogador.getClasse().getVidaDefesa() - guerreiro.getArmadura());
			
			double danoResistido = dano.getDano() - (jogador.getClasse().getVidaDefesa() - guerreiro.getArmadura());
			
			if(danoResistido > 0) {
				danoTotal = danoResistido;
			}else {
				danoTotal = 0;
			}
		}
		jogador.getClasse().setVidaDefesa(0);
		
		double vidaJogador = jogador.getClasse().getVidaAtual();
		
		double danoCondicao = verificarCondicaoFimTurno(jogador, inimigo);
		
		jogador.getClasse().setVidaAtual(vidaJogador - (danoTotal + danoCondicao));
		
		mensagem = montarMensagem(inimigo.getClasse().getNome(), dano, danoTotal, danoCondicao);
		
		inimigo.getClasse().fimDeTurno(danoTotal + danoCondicao);
	}
	
	public void atualizarPontuacaoRodada(int pontuacao) {
		setPontuacaoRodada(getPontuacaoRodada() + pontuacao);
	}
	
	private String montarMensagem(String quem, Dano dano, double danoTotal, double danoCondicao) {
		StringBuilder sb = new StringBuilder(quem + ": " + dano.getDescricao());
		if (dano.getDano() > 0) {
			sb.append("\nDano causado: ").append((int) danoTotal);
		}
		if (danoCondicao > 0) {
			sb.append("\nDano da condição: ").append((int) danoCondicao);
		}
		return sb.toString();
	}
}
