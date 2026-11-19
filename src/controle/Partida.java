package controle;

import java.util.List;

import javax.swing.JOptionPane;

import enumerations.EstadoJogo;
import excecoes.CampoInvalidoException;
import modelo.HabilidadeEspecial;
import modelo.armas.Arco;
import modelo.armas.Cajado;
import modelo.armas.Espada;
import modelo.classes.Arqueiro;
import modelo.classes.Guerreiro;
import modelo.classes.Jogador;
import modelo.classes.Mago;

public class Partida {

	private Jogador jogador;
	private Rodada[] rodada;
	private boolean resultado;
	private int pontuacaoTotal;
	private int quantidadeTotalTurnos;
	private int indexRodada;
	private int indexTurno;
	private Rodada rodadaAtual;
	
	public Partida() {
		setQuantidadeTotalTurnos(0);
		rodada = new Rodada[3];
		setResultado(false);
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public Rodada[] getRodada() {
		return rodada;
	}
	public void setRodada(Rodada[] rodada) {
		this.rodada = rodada;
	}
	public Rodada getRodadaEspecifica(int index) {
		return rodada[index];
	}
	public boolean isResultado() {
		return resultado;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	public int getPontuacaoTotal() {
		return pontuacaoTotal;
	}
	public void setPontuacaoTotal(int pontuacaoTotal) {
		this.pontuacaoTotal = pontuacaoTotal;
	}
	public int getQuantidadeTotalTurnos() {
		return quantidadeTotalTurnos;
	}
	public void setQuantidadeTotalTurnos(int quantidadeTotalTurnos) {
		this.quantidadeTotalTurnos = quantidadeTotalTurnos;
	}
	public int getIndexRodada() {
		return indexRodada;
	}
	public void setIndexRodada(int indexRodada) {
		this.indexRodada = indexRodada;
	}
	public int getIndexTurno() {
		return indexTurno;
	}
	public void setIndexTurno(int indexTurno) {
		this.indexTurno = indexTurno;
	}
	public Rodada getRodadaAtual() {
		return rodadaAtual;
	}
	public void setRodadaAtual(Rodada rodadaAtual) {
		this.rodadaAtual = rodadaAtual;
	}

	public void iniciarJogo() {
		
		indexRodada = 0;
		
		comecarRodada();
	}
	
	public void proximaRodada() {
		indexRodada++;
		comecarRodada();
	}
	
	private void comecarRodada() {
		
		rodadaAtual = new Rodada(indexRodada + 1, 0);
		rodadaAtual.iniciarRodada(jogador);
		rodada[indexRodada] = rodadaAtual;

		indexTurno = 0;
		
		processarAcaoInimigo();
	}
	
	public int getVezAtual() {
		return rodadaAtual.getTurnos().get(indexTurno);
	}
	
	public EstadoJogo iniciarTurnos() {
		return executarVezAtual();
	}
	
	public EstadoJogo jogadorAgir(int escolha) {
		if (!rodadaAtual.escolherAcaoAtaque(jogador, 0, escolha)) {
			return EstadoJogo.ACAO_INVALIDA;
		}
		return EstadoJogo.ACAO_REALIZADA;
	}
	
	private EstadoJogo verificarFim() {
		if(jogador.getClasse().getVidaAtual() <= 0) {
			return EstadoJogo.DERROTA;
		}
		
		if(rodadaAtual.getInimigo().getClasse().getVidaAtual() <= 0) {
			
			if(indexRodada == rodada.length - 1) {
				return EstadoJogo.VITORIA_FINAL;
			}else {
				return EstadoJogo.VITORIA_RODADA;
			}
		}
		
		return null;
	}
	
	public EstadoJogo continuar() {
		EstadoJogo fim = verificarFim();
		if (fim != null) {
			return fim;
		}
		avancarIndice();
		return executarVezAtual();
	}
	
	private EstadoJogo executarVezAtual() {
		if (getVezAtual() == 1) {
			rodadaAtual.escolherAcaoAtaque(jogador, 1, 0);
			return EstadoJogo.ACAO_REALIZADA;
		}
		return EstadoJogo.AGUARDANDO_ACAO_JOGADOR;
	}
	
	public void avancarIndice() {
		List<Integer> turnos = rodadaAtual.getTurnos();
		indexTurno ++;
		if(indexTurno >= turnos.size()) {
			turnos.clear();
			rodadaAtual.atualizarTurno(jogador);
			indexTurno = 0;
		}
	}
	
	public void processarAcaoInimigo() {
		List<Integer> turnos = rodadaAtual.getTurnos();
		while (turnos.get(indexTurno) == 1 && verificarFim() == null) {
			rodadaAtual.escolherAcaoAtaque(jogador, 1, 0);
			avancarIndice();
		}
	}
	
	public void iniciarJogador(String escolha, String nome) {
		
		try {
			switch(escolha) {
				
				case "GUERREIRO":
					jogador = new Jogador(new Guerreiro(nome, 100, 100, 100, 1, new Espada("Zabu", 200, 40, new HabilidadeEspecial(100, 80, 100), 10), 50, 100, 20, 10));
					break;
					
				case "MAGO":
					jogador = new Jogador(new Mago(nome, 100, 100, 80, 1, new Cajado("Fogo", 20, 10, new HabilidadeEspecial(100, 80, 100), 10), 50, 100, 20));
					break;
					
				case "ARQUEIRO":
					jogador = new Jogador(new Arqueiro(nome, 100, 100, 80, 3, new Arco("Sauron", 20, 10, new HabilidadeEspecial(100, 80, 100), 10), 10, 10, 1));
					break;
			}
		}catch(CampoInvalidoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	
	
	/*
	public void jogarJogo() {
		
		int fase = 0;
		
		for(int i = 0; i < rodada.length; i++) {
			
			resultado = false;
			
			rodada[i] = new Rodada(++fase, 0);
			Rodada rodadaEspecifica = rodada[i];
			
			
			rodadaEspecifica.iniciarRodada(jogador);
			
			List<Integer> turnos = rodadaEspecifica.getTurnos();
			System.out.println(turnos);
			Inimigo inimigo = rodadaEspecifica.getInimigo();
			
			System.out.println(inimigo.getClasse());
			
			int indexTurno = 0;
			
			while(jogador.getClasse().getVidaAtual() > 0 && inimigo.getClasse().getVidaAtual() > 0) {
				System.out.println(turnos);
				int vez = turnos.get(indexTurno);
				
				System.out.println(turnos.get(indexTurno));
				
				if(vez == 0) {
					rodadaEspecifica.escolherAcaoAtaque(jogador, vez);
				}else if(vez == 1) {
					rodadaEspecifica.escolherAcaoAtaque(jogador, vez);
				}
				
				
				System.out.println("Jogador Vida:" + jogador.getClasse().getVidaAtual());
				System.out.println("Inimigo Vida:" + inimigo.getClasse().getVidaAtual());
				
				System.out.println(jogador.getClasse().getEnergiaAtual() + " Energia Jogador");
				System.out.println(inimigo.getClasse().getEnergiaAtual() + " Energia Inimigo");
				
				if(vez != turnos.getLast()) {
					indexTurno++;
				}else {
					rodadaEspecifica.atualizarTurno(jogador);
					indexTurno++;
				}
			}
			if(inimigo.getClasse().getVidaAtual() <= 0) {
				setResultado(true);
			}
			
			quantidadeTotalTurnos += rodadaEspecifica.getQuantidadeTurnos();
			jogador.setPontuacao(jogador.getPontuacao() + rodada[i].getPontuacaoRodada());
			System.out.println(jogador.getPontuacao());
			
			//Verifica pra ver se é a utlima rodada
			if(i != rodada.length - 1 && resultado) {
				Intervalo intervalo  = new Intervalo();
				intervalo.iniciarIntervalo(jogador);
			}else {
				if(resultado) {
					System.out.println("Vítoria");
				}else {
					System.out.println("Derrota");
					break;
				}
			}
		}
	}
	*/
}
