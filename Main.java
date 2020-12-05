import java.nio.file.*;
import java.time.*;
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

        //System.out.println(grille);
        
        Grille grille = new Grille(str);

        long timeElapsed = 0;
    
        
        for(int i=0;i<10;i++){
            long start = System.nanoTime();
  
            Algorithme test = new Algorithme(grille);
            Grille grilleColo = test.coloration();
            long finish = System.nanoTime();
            System.out.println(finish - start);
            timeElapsed += finish - start;
        
        } 
        
      
        System.out.println(timeElapsed/11);

        //Grille grilleColo = grille.enumeration();

        //System.out.println(grilleColo);

        //API_Grille f = new API_Grille(grilleColo);
        //f.run();

    }
  
}