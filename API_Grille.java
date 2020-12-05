import  javax.swing.*;
import  java.awt.*;

/**
 * API_Grille creates a graphic display of {@link Grille}.
 * 
 * @author Sylvain Rakotomalala, Ali Touzi
 */
public class API_Grille{


	//  ************************************************************************

    //  Fields

    //  ************************************************************************

	/**
	 * Private field, Grille which will be shown.
	 * 
	 * @see API_Grille#run()
	 * @see API_Grille#API_Grille(Grille)
	 * @see API_Grille#getGrille()
	 */
	private Grille G;

	/**
	 * Private field, panel of the Grille.
	 * 
	 * @see API_Grille#API_Grille(Grille)
	 * @see API_Grille#getGrille()
	 */
	private JPanel pan;




	//  *************************************************************************

    //  Constructors

    //  *************************************************************************
	
	/**
	 * Parametrized constructor.
	 * 
	 * The {@link #pan} is created with the parameter G.
	 * 
	 * @param G Grille used to create the panel {@link #pan}.
	 * 
	 * @see API_Grille#pan
	 * 
	 */
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




	//  *************************************************************************

    //  Public methods

    //  *************************************************************************
	
	/**
	 * This method display on screen the value of {@link #G}.
	 * 
	 * @see API_Grille#G
	 */
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


	/**
	 * This method returns the value of {@link #G}.
	 * 
	 * @return The value of {@link #G}.
	 * 
	 * @see API_Grille#G
	 * @see API_Grille#pan
	 */
	public JPanel getGrille(){
		return pan;
	}
}


