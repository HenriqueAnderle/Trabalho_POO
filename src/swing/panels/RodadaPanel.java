package swing.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controle.Partida;
import enumerations.EstadoJogo;

public class RodadaPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JLabel vidaJogadorLabel;
	private JLabel energiaJogadorLabel;
	
	private JLabel vidaInimigoLabel;
	private JLabel energiaInimigoLabel;
	
	private JButton[] botoes;
	
	private JPanel batalhaPanel;
	private JPanel jogadorPanel;
	private JPanel inimigoPanel;
	private JPanel bottomPanel;
	
	private Partida partida;
	private JLabel mensagemLabel;
	
	private JPanel cardPanel;
	private CardLayout cardLayout;
	
	private CardLayout rodapeLayout;
	private JPanel rodapePanel;
	private JTextArea textoArea;
	private JButton continuarButton;
	
	public RodadaPanel(JPanel cardPanel, CardLayout cardLayout, PartidaPanel partidaPanel) {
		
		this.cardPanel = cardPanel;
		this.cardLayout = cardLayout;
		
		this.setLayout(new BorderLayout());
		
		//JOGADOR PANEL
		
		vidaJogadorLabel = new JLabel("VIDA : ");
		energiaJogadorLabel = new JLabel("ENERGIA : ");
		
		jogadorPanel = new JPanel();
        jogadorPanel.setLayout(new BoxLayout(jogadorPanel, BoxLayout.Y_AXIS));

        jogadorPanel.setBorder(new LineBorder(Color.BLACK));
		
		vidaJogadorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        energiaJogadorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        jogadorPanel.add(vidaJogadorLabel);
        jogadorPanel.add(energiaJogadorLabel);
		
		//INIMIGO PANEL
		
		vidaInimigoLabel = new JLabel("VIDA : ");
		energiaInimigoLabel = new JLabel("ENERGIA : ");
		
		inimigoPanel = new JPanel();
		inimigoPanel.setLayout(new BoxLayout(inimigoPanel, BoxLayout.Y_AXIS));

        inimigoPanel.setBorder(new LineBorder(Color.BLACK));

        vidaInimigoLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        energiaInimigoLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        vidaInimigoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        energiaInimigoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        inimigoPanel.add(vidaInimigoLabel);
        inimigoPanel.add(energiaInimigoLabel);
		
		//BATALHA PANEL
		
        batalhaPanel = new JPanel();
        batalhaPanel.setLayout(new GridLayout(1, 2));

        batalhaPanel.add(jogadorPanel);
        batalhaPanel.add(inimigoPanel);
		
		//BOTTOM PANEL
        		
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 3, 10, 10));

        bottomPanel.setBorder(new LineBorder(Color.BLACK));

        bottomPanel.setPreferredSize(new Dimension(0, 150));
        
        String[] nomesBotoes = {"ATACAR PRIMÁRIO", "ATACAR SECUNDÁRIO", "HABILIDADE ESPECIAL", "DEFENDER", "ESQUIVAR", "USAR ITEM"};
        
        botoes = new JButton[nomesBotoes.length];
        
        for(int i = 0; i < botoes.length; i++) {
        	int acao = i + 1;
        	
        	botoes[i] = new JButton(nomesBotoes[i]);
        	
        	botoes[i].addActionListener(e -> jogadorEscolheu(acao));
        	
        	bottomPanel.add(botoes[i]);
        }
        
        // CAIXA DE TEXTO + CONTINUAR
        textoArea = new JTextArea();
        textoArea.setEditable(false);
        textoArea.setLineWrap(true);
        textoArea.setWrapStyleWord(true);
        textoArea.setMargin(new Insets(10, 10, 10, 10));

        continuarButton = new JButton("Continuar");
        continuarButton.addActionListener(e -> tratarEstado(partida.continuar()));

        JPanel textoPanel = new JPanel(new BorderLayout(10, 10));
        textoPanel.add(textoArea, BorderLayout.CENTER);
        textoPanel.add(continuarButton, BorderLayout.SOUTH);

        // RODAPÉ (troca entre os dois)
        rodapeLayout = new CardLayout();
        rodapePanel = new JPanel(rodapeLayout);
        rodapePanel.setBorder(new LineBorder(Color.BLACK));
        rodapePanel.setPreferredSize(new Dimension(0, 150));
        rodapePanel.add(bottomPanel, "ACOES");
        rodapePanel.add(textoPanel, "TEXTO");

        add(batalhaPanel, BorderLayout.CENTER);
        add(rodapePanel, BorderLayout.SOUTH);
	}
	
	private void jogadorEscolheu(int acao) {
		EstadoJogo estado = partida.jogadorAgir(acao);

		if (estado == EstadoJogo.ACAO_INVALIDA) {
			// ex.: sem energia. Continua com os botões na tela
			JOptionPane.showMessageDialog(this, partida.getRodadaAtual().getMensagem());
			return;
		}
		tratarEstado(estado);
	}

	/** Chamado pelo PartidaPanel quando a partida começa. */
	public void iniciarRodadaTela() {
		tratarEstado(partida.iniciarTurnos());
	}

	private void tratarEstado(EstadoJogo estado) {
		atualizar();

		switch (estado) {
			case ACAO_REALIZADA:
				textoArea.setText(partida.getRodadaAtual().getMensagem());
				rodapeLayout.show(rodapePanel, "TEXTO");
				break;

			case AGUARDANDO_ACAO_JOGADOR:
				rodapeLayout.show(rodapePanel, "ACOES");
				break;

			case VITORIA_RODADA:
				JOptionPane.showMessageDialog(this, "Rodada vencida!");
				IntervaloPanel intervaloPanel = new IntervaloPanel(cardPanel, cardLayout, partida);
				cardPanel.add(intervaloPanel, "IntervaloPanel");
				
				cardLayout.show(cardPanel, "IntervaloPanel");
				
				partida.proximaRodada();
				tratarEstado(partida.iniciarTurnos());
				break;

			case VITORIA_FINAL:
				JOptionPane.showMessageDialog(this, "Vitória!");
				cardLayout.show(cardPanel, "InicioPanel");
				break;

			case DERROTA:
				JOptionPane.showMessageDialog(this, "Derrota!");
				cardLayout.show(cardPanel, "InicioPanel");
				break;

			default:
				break;
		}
	}
	
	public void atualizar() {

		vidaJogadorLabel.setText("VIDA : " + (int) partida.getJogador().getClasse().getVidaAtual());
		energiaJogadorLabel.setText("ENERGIA : " + (int) partida.getJogador().getClasse().getEnergiaAtual());
		vidaInimigoLabel.setText("VIDA : " + (int) partida.getRodadaAtual().getInimigo().getClasse().getVidaAtual());
		energiaInimigoLabel.setText("ENERGIA : " + (int) partida.getRodadaAtual().getInimigo().getClasse().getEnergiaAtual());
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}
}
