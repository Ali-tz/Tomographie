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

            System.out.println("i:" + i + " j:" + j);
            return false;
        }
    }


    public Grille coloration(){

        int n = G.getN();
        int m = G.getM();

        ArrayList<Integer> lignesAVoir = new ArrayList<Integer>();
        for(int i = 0 ; i < n ; i++){  lignesAVoir.add(i);}

        ArrayList<Integer> colonnesAVoir = new ArrayList<Integer>();
        for(int i = 0; i < m; i++){ colonnesAVoir.add(i);    }

        int cpt = 0;

        boolean ok;
        while(!lignesAVoir.isEmpty() || !colonnesAVoir.isEmpty()){
            System.out.println("On commence par les lignes");
            while ( !lignesAVoir.isEmpty() ){
                int p = lignesAVoir.get(0);
                

                ok = colorLigne(p, m-1, G.getSequencesLigne()[p].getTaille());    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    G.setPasComplete();
                    System.out.println("Grille non solvable\n");
                    return G;
                }
                for(int j = 0; j < m; j++){
                    if(G.getCase(p, j).getRecent()){
                        // if(colonnesAVoir.contains(j)){
                        colonnesAVoir.add(j);
                        //}
                        cpt++;
                        G.getCase(p, j).setRecent(false);
                    }
                        
                    //System.out.println(grille);
                }
                lignesAVoir.remove((Object) p); 
            }


            System.out.println("Puis les colonnes");
            while ( !colonnesAVoir.isEmpty() ){              
                int q = colonnesAVoir.get(0);
        
                ok = colorColonne(n-1, q, G.getSequencesColonne()[q].getTaille());    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    G.setPasComplete();
                    System.out.println("Grille non solvable\n");
                    return G;
                }
                for(int i = 0; i < n; i++){
                    if(G.getCase(i, q).getRecent()){
                        lignesAVoir.add(i);
                        cpt++;
                        G.getCase(i, q).setRecent(false);
                    }
                    
                    //System.out.println(grille);
                }
                colonnesAVoir.remove((Object) q);

            }

            if(cpt == n*m){
                G.setComplete();
                return G;
            }
        }
        return G;
    }
}