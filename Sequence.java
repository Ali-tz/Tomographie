public class Sequence{

    private int[] sequence; /* Sequences de chiffres du jeu*/
    private int taille; /* nombre de sequences de chiffres*/

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
   
    public int getTaille(){
        return taille;
    }

    public int[] getSequence(){
        if(sequence == null){
            System.out.println("Erreur: Sequence non initialisée");
        }
        return sequence;
    }

    public int getSequence_i(int i){
        return sequence[i];
    }

    public String toString(){
        String s = "";
        for ( int i : this.sequence){
            s+= i + " ";
        }
        return s;
    }

}