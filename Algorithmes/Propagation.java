package Algorithmes;

import java.util.ArrayList;
import Grille.*;


public class Propagation extends Algo1{

    public Propagation(Grille G){
        super(G);
    }

    public boolean colorLigne(int i, int j, int l){ 
        int m = G.getM()-1;



        if(!T(i,m,l)){
            return false;
        }
        if(j < 0){return true;}

        if (G.getCouleur(i, j)!=0){
            return colorLigne(i, j-1, l);
        }

        G.getCase(i, j).changeCouleur(2);

        if(T(i,m,l)){
            G.getCase(i, j).changeCouleur(1);
            if(T(i,m,l)){
                G.getCase(i, j).changeCouleur(0); 
            }else{
                G.getCase(i, j).changeCouleur(2);
                G.getCase(i, j).setRecent(true);
            }
            return colorLigne(i, j-1, l);
        }else{
            G.getCase(i, j).changeCouleur(1);
            if(T(i,m,l)){
                G.getCase(i, j).setRecent(true);
                return colorLigne(i, j-1, l);
            }
            G.getCase(i, j).changeCouleur(0); 
            return false;
        }
    }

    public boolean colorColonne(int i, int j, int l){ 
        int n = G.getN()-1;
        if(!T2(n,j,l)){
            return false;
        }
        if(i < 0){return true;}

        if (G.getCouleur(i, j)!=0){
            return colorColonne(i-1, j, l);
        }

        G.getCase(i, j).changeCouleur(2);

        if(T2(n,j,l)){
            G.getCase(i, j).changeCouleur(1);
            if(T2(n,j,l)){
                G.getCase(i, j).changeCouleur(0); 
            }else{
                G.getCase(i, j).changeCouleur(2);
                G.getCase(i, j).setRecent(true);
            }
            return colorColonne(i-1, j, l);
        }else{
            G.getCase(i, j).changeCouleur(1);
            if(T2(n ,j ,l)){
                G.getCase(i, j).setRecent(true);
                return colorColonne(i-1, j, l);
            }
            G.getCase(i, j).changeCouleur(0); 
            return false;
        }
    }


    public Grille coloration(){
        Grille grille = G.clone();
        int n = grille.getN();
        int m = grille.getM();

        ArrayList<Integer> lignesAVoir = new ArrayList<Integer>();
        for(int i = 0 ; i < n ; i++){  lignesAVoir.add(i);}

        ArrayList<Integer> colonnesAVoir = new ArrayList<Integer>();
        for(int i = 0; i < m; i++){ colonnesAVoir.add(i);    }

        int cpt = 0;

        boolean ok;
        while(!lignesAVoir.isEmpty() || !colonnesAVoir.isEmpty()){
            for(int i = lignesAVoir.size()-1; i >= 0; i--){
                ok = colorLigne(lignesAVoir.get(i), m-1, G.getSequencesLigne()[i].getTaille());    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    G.setPasComplete();
                    return G;
                }
                for(int j = 0; j < m; j++){
                    if(grille.getCase(i, j).getRecent()){
                        colonnesAVoir.add(j);
                        cpt++;
                        G.getCase(i, j).setRecent(false);
                    }
                    lignesAVoir.remove((Object)i);
                }
            }
            for(int j = colonnesAVoir.size()-1; j>=0; j--){
                ok = colorColonne(n-1,j,G.getSequencesColonne()[j].getTaille());    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    G.setPasComplete();
                    return G;
                }
                for(int i = 0; i < n; i++){
                    if(grille.getCase(i, j).getRecent()){
                        lignesAVoir.add(i);
                        cpt++;
                        G.getCase(i, j).setRecent(false);
                    }
                    colonnesAVoir.remove((Object)j);
                }
            }
            if(cpt == n*m){
                G = grille;
                G.setComplete();
                return G;
            }
        }
        return G;
    }
}