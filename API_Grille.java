import  javax.swing.*;
import  java.awt.*;
import Grille.*;


public class API_Grille{
	private Grille G;
	private JPanel pan;

	public API_Grille(Grille G){

		this.G = G;
		//this.frame = frame;
		int n = G.getN();
		int m = G.getM();

		pan = new JPanel(); //Panel
		pan.setSize(n*50, m*50);
		pan.setLayout(new GridLayout(n, m, 1, 1)); /* initialisation de la grille*/
		//this.frame.setContentPane(grille);

		JPanel[][] cases = new JPanel[n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){  				  /* Mise en place des case de la grille */
				cases[i][j] = new JPanel();
				cases[i][j].setSize(50,50);
				if(this.G.getCouleur(i, j) == 2){
					cases[i][j].setBackground(Color.BLACK);
				}else if(this.G.getCouleur(i, j) == 1){
					cases[i][j].setBackground(Color.WHITE);
				}else{
					cases[i][j].setBackground(Color.GRAY);
				}
				//cases[i][j].setLayout(new BorderLayout());
				pan.add(cases[i][j]);
			}
		}
		
	}

	public void run(){
		int y = this.pan.getHeight();
		int x = this.pan.getWidth();

		while(x > 1000 || y > 1000){
    		x = x / 2;
			y = y / 2;
		}
		JFrame fenetre = new JFrame();
        fenetre.setSize(y, x);
        fenetre.setTitle("Tomographie");
        fenetre.setLocationRelativeTo(null);
        fenetre.setContentPane(this.pan);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JPanel getGrille(){
		return pan;
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