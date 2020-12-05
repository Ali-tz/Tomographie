import java.nio.file.*;
import java.io.*;
//import  javax.swing.*;
//import  java.awt.*;

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


        Algorithme algo = new Algorithme(grille);

        System.out.println(grille);
        int m = grille.getM();

        for(int i=0; i<grille.getN(); i++){
            System.out.println(algo.T(i, m-1, grille.getSequencesLigne()[i].getTaille()));

        }

    }
}