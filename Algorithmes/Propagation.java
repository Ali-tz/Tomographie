package Algorithmes;

import java.util.ArrayList;
import Grille.*;


public class Propagation extends Algo1{

    public Propagation(Grille G){
        super(G);
    }

    public boolean colorLigne(int i){ return true;}

    public boolean colorColonne(int j){ return true;}


    public boolean coloration(Grille G){
        Grille grille = G;

        ArrayList<Integer> lignes = new ArrayList<Integer>();
        for(int i = grille.getN()-1; i >= 0 ; i--){  lignes.add(i);  }

        ArrayList<Integer> colonnes = new ArrayList<Integer>();
        for(int i = grille.getM()-1; i>=0; i--){ colonnes.add(i);    }


        boolean ok;
        while(!lignes.isEmpty() && !colonnes.isEmpty()){
            for(int i : lignes){
                ok = colorLigne(i);    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    G = new Grille(G.getSequences());
                    return false;
                }

            }
        }
        return true;
    }
}