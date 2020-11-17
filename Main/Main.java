package Main;
import java.nio.file.*;
import java.io.*;
import Grille.*;

public class Main{
    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Erreur: <fichier.txt>");
        }
        
        String str = new String(Files.readAllBytes(Paths.get(args[0])));
        System.out.println(str);

        Grille grille = new Grille(str);

        System.out.println(grille);
    }
  
}