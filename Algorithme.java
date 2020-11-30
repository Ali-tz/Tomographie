import java.util.ArrayList;
import Grille.*;


public class Algorithme{

    private Grille G;
    
    
    public Algorithme(Grille G){this.G = G;}

    
    /**
     * 
     * PARTIE 1
     * 
     * /

   

        /*------------------ GENERALISATION ------------------------------------*/

    public boolean T(int i, int j, int l){  /* FONCTION T SUR LES LIGNES*/
  
        if (j<0){
            return (l==0);
        }

        if(l == 0){ 
            for (int k =0; k<=j; k++){  /* Dans le cas ou on aurait réussi à placer toute les séquences on vérifie qu'il ne reste pas de case noir sur la ligne/colonne*/
                if (G.getCouleur(i, k)== 2){
                    return false; /* Si c'est le case la ligne/colonne est incorrectement résolu */
                }
            }
            return true;
        }

        int sl = this.G.getSequencesLigne()[i].getSequence_i(l-1);
        
        if(j < sl - 1){
            return false;
        }
        if(j == sl - 1){
            if(l != 1){
                return false;
            }

            for(int k=0; k<j+1; k++){ /* on regarde toutes les cases entre 0 et j pour voir si on peut placer la séquence*/
                if (G.getCouleur(i, k)==1){
                    return false;
                }
            }
            return true;

        }
        /*Cas où j > sl-1 */       

        Boolean N = false; /*indique si on a croisé une case noir*/
        
        for (int k=j; k>j-sl;k--){ /* regarde si on peut placer la séquence sl entre les case (i,j) et (i, j-sl-1)*/
            if (G.getCouleur(i, k)==2){
                N = true;
            }
            if (G.getCouleur(i, k)==1){
                if (N){
                    return false;
                }                
                return T(i,j-1,l);
            }
        }

        if (G.getCouleur(i, j-sl)==2){
            if (G.getCouleur(i, j)==2){ /*La séquence l doit etre contrenu dans sl case et entouré de cases blanches de chaques cotés */
                return false;
            }
            return T(i,j-1,l);
        }
        
        if (sl==1 && N){
            return T(i,j-sl-1,l-1)||T(i,j-1,l-1);
        } 

        return T(i,j-sl-1,l-1)||T(i,j-1,l);   
    }



        /*------------------ PROPAGATION ------------------------------------*/

    
    public boolean T2(int i, int j, int l){ /* FONCTION T SUR LES COLONNES*/

        if (i<0){
            return (l==0);
        }

        if(l == 0){
            for (int k =0; k<=i; k++){
                if (G.getCouleur(k, j) == 2){
                    return false;
                }
            }
            return true;
        }

        int sl = this.G.getSequencesColonne()[j].getSequence_i(l-1);

        if(i < sl - 1){
            return false;
        }
        if(i == sl - 1){
            if(l != 1){
                return false;
            }

            for(int k=0; k<i+1; k++){
                if (G.getCouleur(k, j)==1){
                    return false;
                }
            }
            return true;

        }

        Boolean N = false; /* indique si on a croisé une case noir */
        for (int k=i; k>i-sl;k--){ 
            if (G.getCouleur(k,j)==2){
                N = true;
            }
            if (G.getCouleur(k,j)==1){
                if (N){
                    return false;
                }                
                return T2(i-1,j,l);
            }
        }
        /*Cas où j > sl-1*/       
        if (G.getCouleur(i-sl,j)==2){
            if (G.getCouleur(i, j)==2){
                return false;
            }
            return T2(i-1,j,l);
        }

        
        if (sl==1 && N){
            return T2(i-sl-1,j,l-1)||T2(i-1,j,l-1);
        }
        return T2(i-sl-1,j,l-1)||T2(i-1,j,l);   
    }

    

    public boolean colorLigne(int i, int j, int l, Grille grille){  /* FONTION DE COLORATION DES LIGNES */
        int m = grille.getM()-1;
        Algorithme gAlg = new Algorithme(grille);

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



    public boolean colorColonne(int i, int j, int l,  Grille grille){   /* FONTION DE COLORATION DES COLONNES */

        Algorithme gAlg = new Algorithme(grille);
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

    
    public Grille coloration(){ /* FONCTION DE COLORATION DE LA GRILLE */

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
}