package Algorithmes;

import java.util.ArrayList;
import Grille.*;


public class Propagation extends Algo1{

    public Propagation(Grille G){
        super(G);
    }

    public boolean colorLigne(int i, int j, int l){ 
        if(!T(i,j,l)){
            return false;
        }
        G.getCase(i, j).changeCouleur(2);
        if(T(i,j,l)){
            return colorLigne(i, j-l-1, l-1);
        }else{
            G.getCase(i, j).changeCouleur(1);
            if(T(i,j,l)){
                return colorLigne(i,j-1,l);
            }
        }
        return true;
    }

    public boolean colorColonne(int i, int j, int l){ 
        if(!T(i,j,l)){
            return false;
        }
        G.getCase(i, j).changeCouleur(2);
        if(T(i,j,l)){
            return colorColonne(i-l-1, j, l-1);
        }else{
            G.getCase(i, j).changeCouleur(1);
            if(T(i,j,l)){
                return colorColonne(i-1,j,l);
            }
        }
        return true;
    }


    public Grille coloration(Grille G){
        Grille grille = G;
        int n = grille.getN()-1;
        int m = grille.getM()-1;

        ArrayList<Integer> lignes = new ArrayList<Integer>();
        for(int i = n; i >= 0 ; i--){  lignes.add(i);  }

        ArrayList<Integer> colonnes = new ArrayList<Integer>();
        for(int i = m; i>=0; i--){ colonnes.add(i);    }

        int cpt = 0;

        boolean ok;
        while(!lignes.isEmpty() && !colonnes.isEmpty()){
            for(int i : lignes){
                ok = colorLigne(i, m, G.getSequencesLigne()[i].getTaille());    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    G.setPasComplete();
                    return G;
                }
                for(int j = 0; j < m; j++){
                    if(grille.getCase(i, j).getRecent()){
                        colonnes.add(j);
                        cpt++;
                    }
                    lignes.remove(i);
                }
            }
            for(int j : colonnes){
                ok = colorColonne(n,j,G.getSequencesColonne()[j].getTaille());    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    G.setPasComplete();
                    return G;
                }
                for(int i = 0; i < n; i++){
                    if(grille.getCase(i, j).getRecent()){
                        lignes.add(i);
                        cpt++;
                    }
                    colonnes.remove(j);
                }
            }
            if(cpt == (m+1)*(m-1)){
                grille.setComplete();
                return grille;
            }
        }
        return G;
    }
}