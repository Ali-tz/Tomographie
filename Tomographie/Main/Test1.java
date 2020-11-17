package Main;
import Grille.*;
public class Test1{

    public static void main(String[] args){
        String seq = "3\n1 1 1\n3\n1 1\n1 1\n#1 1\n1 2\n3\n1 2\n1 1";

        Grille grille = new Grille(seq);

        System.out.println(grille);

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