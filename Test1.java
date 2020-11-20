import Grille.*;
import java.nio.file.*;
import java.io.*;
import Algorithmes.Algo1;
public class Test1{

    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Erreur: <fichier.txt>");
        }

        String str = new String(Files.readAllBytes(Paths.get(args[0])));


        Grille grille = new Grille(str);

        System.out.println(grille);

        Algo1 algo1 = new Algo1(grille);

        System.out.println(grille);
        for(int i=0; i<grille.getN(); i++){
            System.out.println(algo1.T(i, grille.getM()-1, grille.getSequencesLigne()[i].getTaille()));

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