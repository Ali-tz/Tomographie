package Algorithmes;


import Grille.*;
public class Algo1 extends Algorithmes{

    public Algo1(Grille g){
        super(g);
    }

    public boolean T(int i, int j, int l){

        
        if (j<0){
            return false;
        }

        if(l == 0){
            for (int k =0; k<j; k++){
                if (G.getCouleur(i, k)== 2){
                    return false;
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

            for(int k=0; k<j+1; k++){
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
        
        Boolean N = false; //boolean nous indiquant si l'on a croisé une case noir ou non
        for (int k=j; k>j-sl;k--){ 
            if (G.getCouleur(i, k)==2){
                N = true;
            }
            if (G.getCouleur(i, k)==1){
                if (N){
                    return false;
                }                
                return T(i,k-1,l);
            }
        }

        if (G.getCouleur(i, j-sl)==2){
            if (G.getCouleur(i, j)==2){
                return false;
            }
            return T(i,j-1,l);
        }

        return T(i,j-sl-1,l-1)||T(i,j-1,l);   
    }

    public boolean T2(int i, int j, int l){

        
        if (i<0){
            return false;
        }

        if(l == 0){
            for (int k =0; k<i; k++){
                if (G.getCouleur(k, j)== 2){
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
                if (G.getCouleur(k, i)==1){
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
                return T2(k-1,j,l);
            }
        }

        if (G.getCouleur(i-sl,j)==2){
            if (G.getCouleur(i, j)==2){
                return false;
            }
            return T2(i-1,j,l);
        }

        return T2(i-sl-1,j,l-1)||T2(i-1,j,l);   
    }
}