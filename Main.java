import java.nio.file.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Erreur: <fichier.txt>");
        }
        
        String str = new String(Files.readAllBytes(Paths.get(args[0])));

        Grille grille = new Grille(str);
        //System.out.println(grille);

        //Algorithme test = new Algorithme(grille);

        //Grille grilleColo1 = test.coloration();

        Grille grilleColo = grille.enumeration();

        System.out.println(grilleColo);

        API_Grille f = new API_Grille(grilleColo);
        f.run();

    }
  
}