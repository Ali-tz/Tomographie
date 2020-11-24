import java.nio.file.*;
import java.io.*;
//import  javax.swing.*;
//import  java.awt.*;

import Grille.*;
import Algorithmes.*;

public class Test1{

    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Erreur: <fichier.txt>");
        }

        String str = new String(Files.readAllBytes(Paths.get(args[0])));

        System.out.println(str);
        Grille grille = new Grille(str);
        
        /*
        API_Grille f = new API_Grille(grille);
        f.run(); */
        
        

        Algo1 algo1 = new Algo1(grille);

        grille.setColor(0, 2, 2);
        grille.setColor(0, 0, 2);
        grille.setColor(1, 1, 1);
        grille.setColor(2, 1, 2);
        grille.setColor(2, 4, 2);
        grille.setColor(3, 1, 1);
        grille.setColor(3, 2, 1);
        grille.setColor(3, 3, 1);
        grille.setColor(3, 4, 1);



        System.out.println(grille);

        for(int j=0; j<grille.getM(); j++){
            System.out.println(algo1.T2(grille.getN()-1, j, grille.getSequencesColonne()[j].getTaille()));

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