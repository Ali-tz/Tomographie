import java.nio.file.*;
import java.io.*;

/**
 * Main class
 * 
 * @author Sylvain Rakotomalala, Ali Touzi
 */
public class Main{

    /**
     * This method is the Main function. 
     * It parses the file on argument and uses it to crate the {@link #Grille}, then solves it and display it on screen.
     * 
     * @param args Takes a file in instances as an argument.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Erreur: <fichier.txt>");
        }
        
        String str = new String(Files.readAllBytes(Paths.get(args[0])));

        Grille grille = new Grille(str);
        //System.out.println(grille);

        Algorithme test = new Algorithme(grille);

        Grille grilleColo = test.coloration();

        //Grille grilleColo = grille.enumeration();

        System.out.println(grilleColo);

        API_Grille f = new API_Grille(grilleColo);
        f.run();

    }
  
}