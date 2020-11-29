package Algorithmes;


import Grille.*;
public class Algo1 extends Algorithmes{

    public Algo1(Grille g){
        super(g);
    }

    public boolean T(int i, int j, int l){
  
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
            
            /* PAS BESOIN
            if (j+1 < G.getM()-1){
                if (G.getCouleur(i,j+1)==2){
                    return false;
                }
            } */

            for(int k=0; k<j+1; k++){ /* on regarde toutes les cases entre 0 et j pour voir si on peut placer la séquence*/
                if (G.getCouleur(i, k)==1){
                    return false;
                }
            }
            return true;

        }
        /* T(i,j - sl - 1, l-1) || T(i,j-1, l) */
        
        /* PAS BESOIN
        if (j+1 < G.getM()-1){
            if (G.getCouleur(i,j+1)==2){
                return T(i,j+1,l);
            }
        } */


        /*###########################################*/
        /* Cas où j > sl-1 */
        

        Boolean N = false; /*boolean nous indiquant si l'on a croisé une case noir ou non*/
        
        for (int k=j; k>j-sl;k--){ /* On regarde si on peut placer la séquence sl entre les case (i,j)et (i, j-sl-1)*/
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

    public boolean T2(int i, int j, int l){

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
            
            /* PAS BESOIN
            if (j+1 < G.getM()-1){
                if (G.getCouleur(i,j+1)==2){
                    return false;
                }
            } */

            for(int k=0; k<i+1; k++){
                if (G.getCouleur(k, j)==1){
                    return false;
                }
            }
            return true;

        }
        /* T(i,j - sl - 1, l-1) || T(i,j-1, l) */
        
        /* PAS BESOIN
        if (j+1 < G.getM()-1){
            if (G.getCouleur(i,j+1)==2){
                return T(i,j+1,l);
            }
        } */


        Boolean N = false; //boolean nous indiquant si l'on a croisé une case noir ou non
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
}