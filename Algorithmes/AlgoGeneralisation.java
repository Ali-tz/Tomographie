package Algorithmes;
import Grille.*;
public class AlgoGeneralisation extends Algorithmes{

    public AlgoGeneralisation(Grille g){
        super(g);
    }

    public boolean T(int i, int j, int l){

        if (l==0){
            return false;
        }

        int sl = this.G.getSequencesLigne()[i].getSequence_i(l-1);

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
                return T(i,j - sl - 1, l-1) || T(i,j-1, l);
            }
        }
    }
}