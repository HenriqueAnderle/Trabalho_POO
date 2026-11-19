package principal;

import controle.Partida;
import swing.frames.PrincipalFrame;

public class Principal {

	public static void main(String[] args) {
		//new Principal();
		
		new PrincipalFrame();
	}
	
	Principal(){
		new Partida();
	}
}
