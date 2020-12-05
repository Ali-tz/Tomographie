/**
 * Sequence is the class representing a table of numbers representing the blocks to put in our {@link #Grille}.
 * Corner stone class of this poject.
 * 
 * @author Sylvain Rakotomalala, Ali Touzi
 */
public class Sequence{


    //  ************************************************************************

    //  Fields

    //  ************************************************************************

    /**
     * Private field, table containing the length of the blocks to put in the {@link #Grille}.
     * 
     * @see Sequence#Sequence(String)
     * @see Sequence#getSequence()
     * @see Sequence#getSequence_i(int)
     * @see Sequence#toString()
     * 
     */
    private int[] sequence;

    /**
     * Private field, length of this {@link #Sequence}, indicates the number of blocks.
     * 
     * @see Sequence#getTaille()
     */
    private int taille; 
    



    //  *************************************************************************

    //  Constructors

    //  *************************************************************************

    /**
     * Parametrized constructor.
     * 
     * This constructor turns a String into a table of int.
     * 
     * @param s Contains the value of the blocks for this {@link #Sequence}.
     * 
     * @see Sequence#sequence
     */
    public Sequence(String s){
        if(s.length() == 0){
            this.taille = 0;
            this.sequence = new int[0];
        }else{
            String tab[] = s.split(" ");
            this.taille = tab.length;
            this.sequence = new int[taille];
            for(int i=0; i<taille; i++){
                sequence[i] = Integer.parseInt(tab[i]);
            }
        }
    }




    
    //  *************************************************************************

    //  Public methods

    //  *************************************************************************

    /**
     * This method returns the value of the field {@link #taille} of this {@link #Sequence}.
     *
     * @return The value of the field {@link #taille} of this {@link #Sequence}.
     * 
     * @see Sequence#taille
     */
    public int getTaille(){
        return taille;
    }


    /**
     * This method returns the value of the field {@link #sequence} of this {@link #Sequence}.
     * @return The value ofthe field {@link #sequence} of this {@link #Sequence}.
     *
     * @see Sequence#sequence
     */
    public int[] getSequence(){
        if(sequence == null){
            System.out.println("Erreur: Sequence non initialisée");
        }
        return sequence;
    }

    /**
     * This method return the value contained in {@link #sequence}[i] of this {@link #Sequence}.
     * 
     * @return The value contained in {@link #sequence}[i] of this {@link #Sequence}.
     * 
     * @see Sequence#sequence
     */
    public int getSequence_i(int i){
        return sequence[i];
    }

    /**
     * This method stores in a String the value of each block from this {@link #Sequence}.
     * 
     * @return A String that contains the value of each block from this {@link #Sequence}
     * 
     * @see Sequence#sequence
     */
    @Override
    public String toString(){
        String s = "";
        for ( int i : this.sequence){
            s+= i + " ";
        }
        return s;
    }

}