import  javax.swing.*;
import  java.awt.*;
import Grille.*;


public class API_Grille{
	private Grille G;
	private JFrame frame;

	public API_Grille(Grille G, JFrame frame){

		this.G = G;
		this.frame = frame;
		int n = G.getN();
		int m = G.getM();

		JPanel grille = new JPanel(); //Panel
		grille.setLayout(new GridLayout(n, m, 1, 1)); /* initialisation de la grille*/
		this.frame.setContentPane(grille);

		JPanel[][] cases = new JPanel[n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){  				  /* Mise en place des case de la grille */
				cases[i][j] = new JPanel();
				cases[i][j].setSize(frame.getWidth()/n,frame.getHeight()/m);
				if(this.G.getCouleur(i, j) == 2){
					cases[i][j].setBackground(Color.BLACK);
				}else if(this.G.getCouleur(i, j) == 1){
					cases[i][j].setBackground(Color.WHITE);
				}else{
					cases[i][j].setBackground(Color.GRAY);
				}
				//cases[i][j].setLayout(new BorderLayout());
				grille.add(cases[i][j]);
			}

		}
		
	}

}









/*API_Case[][] cases = new API_Case[n][m];
		for(int j = 0; j < m; j++){  /* Mise en place des case de la grille 
			for(int i = 0; i < n; i++){	
				cases[i][j] = new API_Case(i, j, this.G.getCouleur(i, j));
			}
			cases[i][j].setLayout(new BorderLayout());
			grille.add(cases[i][j]);
		}*/