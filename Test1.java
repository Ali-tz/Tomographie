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
        
    

        grille.setColor(14, 0, 1);
        grille.setColor(14, 1, 1);
        grille.setColor(14, 2, 1);
        grille.setColor(14, 3, 1);   
        grille.setColor(14, 4, 1);
        grille.setColor(14, 5, 2);
        grille.setColor(14, 6, 2);
        grille.setColor(14, 7, 2);
        grille.setColor(14, 8, 2);   
        grille.setColor(14, 9, 2);
        grille.setColor(14, 10, 2);
        grille.setColor(14, 11, 1);
        grille.setColor(14, 12, 1);
        grille.setColor(14, 13, 1);   
        grille.setColor(14, 14, 1);
        grille.setColor(14, 15, 1);
        grille.setColor(14, 16, 1);
        grille.setColor(14, 17, 1);
        grille.setColor(14, 18, 1);  
        grille.setColor(14, 19, 1);
        grille.setColor(14, 20, 1);
        grille.setColor(14, 21, 1);
        grille.setColor(14, 22, 1);
        grille.setColor(14, 23, 1);
        grille.setColor(14, 24, 2); //ici
        grille.setColor(14, 25, 1);
        grille.setColor(14, 26, 1);
        grille.setColor(14, 27, 2);
        grille.setColor(14, 28, 2);
        grille.setColor(14, 29, 2);
        


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