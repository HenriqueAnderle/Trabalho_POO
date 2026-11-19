package swing.panels;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InicioPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel tituloLabel;
	private JButton novaPartidaButton;
	private JButton carregarPartidaButton;
	private JButton rankingButton;

	public InicioPanel(JPanel cardPanel, CardLayout cardLayout) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		tituloLabel = new JLabel("CAVALEIROS DA RELÍQUIA");
		tituloLabel.setVisible(true);
		tituloLabel.setPreferredSize(new Dimension(200, 50));
		tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		add(tituloLabel);
		
		novaPartidaButton = new JButton("Nova Partida");
		novaPartidaButton.setPreferredSize(new Dimension(200, 50));
		novaPartidaButton.setMaximumSize(new Dimension(200, 50));
		novaPartidaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(novaPartidaButton);
		
		carregarPartidaButton = new JButton("Carregar Partida");
		carregarPartidaButton.setPreferredSize(new Dimension(200, 50));
		carregarPartidaButton.setMaximumSize(new Dimension(200, 50));
		carregarPartidaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(carregarPartidaButton);
		
		rankingButton = new JButton("Ranking");
		rankingButton.setPreferredSize(new Dimension(200, 50));
		rankingButton.setMaximumSize(new Dimension(200, 50));
		rankingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(rankingButton);
		
		PartidaPanel partidadaPanel = new PartidaPanel(cardPanel, cardLayout);
		cardPanel.add(partidadaPanel, "PartidaPanel");
		
		novaPartidaButton.addActionListener(e -> cardLayout.show(cardPanel, "PartidaPanel"));
		
		//Carregar Partida Aqui
		
		RankingPanel rankingPanel = new RankingPanel(cardPanel, cardLayout);
		cardPanel.add(rankingPanel, "RankingPanel");
		
		rankingButton.addActionListener(e -> cardLayout.show(cardPanel, "RankingPanel"));
	}
}
