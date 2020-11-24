import java.nio.file.*;
import java.io.*;
import Grille.*;
import Algorithmes.*;

public class Main{
    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Erreur: <fichier.txt>");
        }
        
        String str = new String(Files.readAllBytes(Paths.get(args[0])));

        Grille grille = new Grille(str);
        System.out.println(grille);

        Propagation test = new Propagation(grille);

        Grille grilleColo = test.coloration();

        System.out.println(grilleColo);

        API_Grille f = new API_Grille(grille);
        f.run();

    }
  
}