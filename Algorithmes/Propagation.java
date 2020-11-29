package Algorithmes;

import java.util.ArrayList;
import Grille.*;


public class Propagation extends Algo1{

    public Propagation(Grille G){
        super(G);
    }

    public boolean colorLigne(int i, int j, int l, Grille grille){ 
        int m = grille.getM()-1;
        Algo1 gAlg = new Algo1(grille);

        if(!gAlg.T(i,m,l)){
            return false;
        }
        if(j < 0){return true;}

        if (grille.getCouleur(i, j)!=0){
            return colorLigne(i, j-1, l, grille);
        }

        grille.getCase(i, j).changeCouleur(2);

        if(gAlg.T(i,m,l)){
            grille.getCase(i, j).changeCouleur(1);
            if(gAlg.T(i,m,l)){
                grille.getCase(i, j).changeCouleur(0); 
            }else{
                grille.getCase(i, j).changeCouleur(2);
                grille.getCase(i, j).setRecent(true);
            }
            return colorLigne(i, j-1, l, grille);
        }else{
            grille.getCase(i, j).changeCouleur(1);
            if(gAlg.T(i,m,l)){
                grille.getCase(i, j).setRecent(true);
                return colorLigne(i, j-1, l, grille);
            }
            grille.getCase(i, j).changeCouleur(0); 
            return false;
        }
    }

    public boolean colorColonne(int i, int j, int l,  Grille grille){

        Algo1 gAlg = new Algo1(grille);
        int n = grille.getN()-1;
        if(!gAlg.T2(n,j,l)){
            return false;
        }
        if(i < 0){return true;}

        if (grille.getCouleur(i, j)!=0){
            return colorColonne(i-1, j, l, grille);
        }

        grille.getCase(i, j).changeCouleur(2);

        if(gAlg.T2(n,j,l)){
            grille.getCase(i, j).changeCouleur(1);
            if(gAlg.T2(n,j,l)){
                grille.getCase(i, j).changeCouleur(0); 
            }else{
                grille.getCase(i, j).changeCouleur(2);
                grille.getCase(i, j).setRecent(true);
            }
            return colorColonne(i-1, j, l, grille);
        }else{
            grille.getCase(i, j).changeCouleur(1);
            if(gAlg.T2(n ,j ,l)){
                grille.getCase(i, j).setRecent(true);
                return colorColonne(i-1, j, l, grille);
            }
            grille.getCase(i, j).changeCouleur(0);

            System.out.println("i:" + i + " j:" + j);
            return false;
        }
    }


    public Grille coloration(){

        Grille gClone = G.clone();
        int n = gClone.getN();
        int m = gClone.getM();

        ArrayList<Integer> lignesAVoir = new ArrayList<Integer>();
        for(int i = 0 ; i < n ; i++){  lignesAVoir.add(i,i); }

        ArrayList<Integer> colonnesAVoir = new ArrayList<Integer>();
        for(int i = 0; i < m; i++){ colonnesAVoir.add(i,i);    }

        int cpt = 0;

        boolean ok;
        while(!lignesAVoir.isEmpty() || !colonnesAVoir.isEmpty()){
            System.out.println("On commence par les lignes");
            while ( !lignesAVoir.isEmpty() ){
                int p = lignesAVoir.get(0);
                

                ok = colorLigne(p, m-1, gClone.getSequencesLigne()[p].getTaille(), gClone);    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    gClone.setComplet(0);
                    System.out.println("Grille non solvable\n");
                    return gClone;
                }
                for(int j = 0; j < m; j++){
                    if(gClone.getCase(p, j).getRecent()){
                        colonnesAVoir.add(colonnesAVoir.size(), j);
                        cpt++;
                        gClone.getCase(p, j).setRecent(false);
                    }
                        
                }
                //System.out.println(p+ "  "+ lignesAVoir.indexOf(p) + '\n');
                //System.out.println(G);
                lignesAVoir.remove((Object) p); 
            }


            System.out.println("Puis les colonnes");
            while ( !colonnesAVoir.isEmpty() ){              
                int q = colonnesAVoir.get(0);
                //System.out.println(q+"  "  +colonnesAVoir.indexOf(q) +'\n');
        
                ok = colorColonne(n-1, q, gClone.getSequencesColonne()[q].getTaille(), gClone);    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    gClone.setComplet(0);
                    System.out.println("Grille non solvable\n");
                    return gClone;
                }
                for(int i = 0; i < n; i++){
                    if(gClone.getCase(i, q).getRecent()){
                        lignesAVoir.add(lignesAVoir.size(),i);
                        cpt++;
                        gClone.getCase(i, q).setRecent(false);
                    }    
                }
                //System.out.println(G);
                colonnesAVoir.remove((Object) q);
            }

            if(cpt == n*m){
                gClone.setComplet(1);
                return gClone;
            }
        }
        return gClone;
    }

    /*
    public Grille enumeration(){
        Grille g = this.coloration();
        if(g.complet() ==  0){
            return null;
        }

    }
    */
}