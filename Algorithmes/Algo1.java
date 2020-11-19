package Algorithmes;
import Grille.*;
public class Algo1 extends Algorithmes{

    public Algo1(Grille g){
        super(g);
    }

    public boolean T(int i, int j, int l){

        int tab[] = new int[l];
        for(int k = 0; k < l ; k++){
            tab[k] = (this.G.getSequencesLigne())[i].getSequence_i(k);
        }
        
        int sl = tab[l-1];

        if(l == 0){
            return true;
        }

        else{
            if(j < sl - 1){
                return false;
            }else if(j == sl - 1){
                if(l == 1){
                    return true;
                }
                return false;

            }else{
                return T(i,j - l - 1, l-1) || T(i,j-l-2, l - 1);
            }
        }
    }
} 