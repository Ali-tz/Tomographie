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
		for(int j = 0; j < m; j++){  				  /* Mise en place des case de la grille */
			for(int i = 0; i < n; i++){
				cases[i][j] = new JPanel();
				if(this.G.getCouleur(i, j) == 2){
					cases[i][j].setBackground(Color.BLACK);
				}else{
					cases[i][j].setBackground(Color.WHITE);
				}
				cases[i][j].setLayout(new BorderLayout());
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