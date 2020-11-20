import Grille.*;
import Algorithmes.Algo1;
public class Test1{

    public static void main(String[] args){
        String seq = "3\n1 1 1\n3\n1 1\n1 1\n#1 1\n1 2\n3\n1 2\n1 1";

        Grille grille = new Grille(seq);
        
        Algo1 algo1 = new Algo1(grille);

        System.out.println(grille);
        for(int i=0; i<grille.getN(); i++){
            System.out.println(algo1.T(i, grille.getM(), grille.getSequencesLigne()[i].getTaille()));

        }

    }
}
/*public class Test1{

    public static void main(String[] args){

        int n = 5;
        int m = 7;

        Grille grille = new Grille(n,m);
        Sequence sequence = new Sequence("1 2 5 3 6");
        System.out.println(grille + "\n" + sequence);

    }
}*/