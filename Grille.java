import java.util.ArrayList;


/**
 * Grille is the class representing a matrix of Case and executes major algorithms.
 * Key class for this poject.
 * 
 * @author Sylvain Rakotomalala, Ali Touzi
 */
public class Grille{


    //  ************************************************************************

    //  Fields

    //  ************************************************************************

    /**
     * Private field, represents the number of lines of this {@link #Grille}.
     * 
     * @see Grille#getN()
     */
    private int n;

    /**
     * Private field, represents the number of columns of this {@link #Grille}.
     * 
     * @see Grille#getM()
     */
    private int m;

    /**
     * Private field, represents this {@link #Grille} fulfilled by cases of {@link #Case}.
     * 
     * @see Grille#EnumRec(int, int)
     * @see Grille#colorierEtPropager(int, int, int)
     * @see Grille#clone()
     * @see Grille#enumeration()
     * @see Grille#toString()
     * @see Grille#Grille(String)
     */
    private Case[][] grille;

    /**
     * Private field, represents the sequences of the length of the lines' blocks.
     * 
     * @see Grille#sLigne
     */
    private Sequence[] sLigne;

    /**
     * Private field, represents the sequences of the length of the columns' blocks.
     * 
     * @see Grille#afficheSeqColonne()
     */
    private Sequence[] sColonne;

    /**
     * Private field, reprensents the length of {@link #sColonne}.
     */
    private int tailleSeqColonne;

    /**
     * Private field, reprensents the length of {@link #sLigne}.
     */
    private int tailleSeqLigne;

    /**
     * Private field, used to store the parsed file used to build this {@link #Grille}.
     */
    private String s;

    /**
     * Private fiels, tells if this {@link #Grille} is fullfield. 1 means it's completed, 0 means uncompletable and -1 we don't know.
     * 
     * @see Grille#setComplet(int)
     * @see Grille#complet()
     */
    private int complet; /* 1-> complété -- -1->on ne sait pas -- 0-> incomplétable*/


    //  *************************************************************************

    //  Constructors

    //  *************************************************************************

    /**
     * Parametrized constructor.
     * 
     * The {@link #grille} will be set to empty and {@link #complet} will be set to -1.
     * 
     * @param s Supposed to contain the block's sequences for the lines and columns.
     * 
     * @see Grille#s
     * @see Grille#grille
     * @see Grille#complet
     */
    public Grille(String s){

        this.s = s;
        this.complet = -1;

        this.tailleSeqColonne = 0;
        this.tailleSeqLigne = 0;

        

        /* INITIALISATION SEQUENCES */
        String tab[] = s.split("#\n");
        String tabLigne[] = tab[0].split("\n");
        String tabColonne[] = tab[1].split("\n");
        
        this.n = tabLigne.length; /*on recupère la longueur de la ligne du tableau*/
        this.m = tabColonne.length;/*on recupère la longueur de la colonne du tableau*/

        this.sLigne = new Sequence[n];
        this.sColonne = new Sequence[m];

        for(int i = 0; i<n; i++){
            sLigne[i] = new Sequence(tabLigne[i]);
            if (tailleSeqLigne < sLigne[i].getTaille()){
                tailleSeqLigne = sLigne[i].getTaille();
            }
        }
        for(int i = 0; i<m; i++){
            sColonne[i] = new Sequence(tabColonne[i]);
             if (tailleSeqColonne < sColonne[i].getTaille()){
                tailleSeqColonne = sColonne[i].getTaille();
            }
        }
        
        /* INITIALISATION GRILLE */
        this.grille = new Case[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m ; j++){
                grille[i][j] = new Case(i,j);
            }
        }
    }
    

    //  *************************************************************************

    //  Public methods

    //  *************************************************************************

    /**
     * This method returns a copy of this {@link #Grille}
     * 
     * @return A clone of this {@link #Grille}.
     * 
     * @see Grille#grille
     */
    public Grille clone(){
        Grille G = new Grille(this.getSequences());
        for(int j = 0; j < m; j++){
            for(int i = 0; i < n; i++){
                G.setColor(i, j, grille[i][j].getCouleur());
            }
        }
        return G;
    }
    

    /**
     * This method is used to change the colors of the {@link #Case} in this {@link #Grille}.
     * 
     * 
     * @param i The x-axis coordinate of the {@Clink #Case} in this {@link #Grille}.
     * @param j The y-axis coordinate of the {@Clink #Case} in this {@link #Grille}.
     * @param cl The new color of the {@Clink #Case} in this {@link #Grille}.
     * 
     * 
     */
    public void setColor(int i,int j,int cl){
        this.getCase(i,j).changeCouleur(cl);
    }
    

    /**
     * This method returns this {@link #Grille} colored and if possible completed.
     * 
     * @return This {@link #Grille} completed if possible.
     * 
     * @see Grille#grille
     */
    public Grille enumeration(){
        Algorithme alg = new Algorithme(this);
        Grille A = alg.coloration();

        if ( A.complet() == 0){
            System.out.println("incomplétable1\n");
            return null;

        }

        if ( A.complet() == 1){
            System.out.println("Complete\n");
            return A;

        }
             
        Grille ABlanc = A.EnumRec(0,1);
        if (ABlanc == null){return A.EnumRec(0,2);}
        else return ABlanc;
    }


    /**
     * This method return a clone of this {@link #Grille} colored and if possible completed
     * 
     * @param i1 The x-axis coordinate of the {@Clink #Case} in this {@link #Grille} that you will start your coloration with.
     * @param j1 The y-axis coordinate of the {@Clink #Case} in this {@link #Grille} that you will start your coloration with.
     * @param c The color value that will be set to (i1,j1) {@link #Case} in this {@link #Grille}.
     * 
     * 
     * @return A clone of his {@link #Grille} colored and if possible completed.
     * 
     * 
     * @see Grille#grille
     */
    public Grille colorierEtPropager(int i1, int j1, int c){ /* FONCTION DE COLORATION DE LA GRILLE */

        Grille gClone = this.clone();
        Algorithme alg = new Algorithme(gClone);

        /*
        int n = gClone.getN();
        int m = gClone.getM(); */

        ArrayList<Integer> lignesAVoir = new ArrayList<Integer>();
        lignesAVoir.add(0,i1);

        ArrayList<Integer> colonnesAVoir = new ArrayList<Integer>();
        colonnesAVoir.add(0,j1);

        gClone.setColor(i1, j1, c);

        boolean ok;
        while(!lignesAVoir.isEmpty() || !colonnesAVoir.isEmpty()){
            System.out.println("On commence par les lignes");
            while ( !lignesAVoir.isEmpty() ){
                int p = lignesAVoir.get(0);
                

                ok = alg.colorLigne(p, m-1, gClone.getSequencesLigne()[p].getTaille(), gClone);    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    gClone.setComplet(0);
                    System.out.println("Grille non solvable\n");
                    return gClone;
                }
                for(int j = 0; j < m; j++){
                    if(gClone.getCase(p, j).getRecent()){
                        colonnesAVoir.add(colonnesAVoir.size(), j);
                        
                        gClone.getCase(p, j).setRecent(false);
                    }
                        
                }
                //System.out.println(p+ "  "+ lignesAVoir.indexOf(p) + '\n');
                //System.out.println(G);
                lignesAVoir.remove((Object) p); 
            }

            System.out.println("Puis les colonnes");
            while ( !colonnesAVoir.isEmpty() ){              
                int q = colonnesAVoir.get(0);
                //System.out.println(q+"  "  +colonnesAVoir.indexOf(q) +'\n');
        
                ok = alg.colorColonne(n-1, q, gClone.getSequencesColonne()[q].getTaille(), gClone);    /* ok=Faux si détection d’impossibilité, ok=Vrai sinon */
                if(ok == false){
                    gClone.setComplet(0);
                    System.out.println("Grille non solvable\n");
                    return gClone;
                }
                for(int i = 0; i < n; i++){
                    if(gClone.getCase(i, q).getRecent()){
                        lignesAVoir.add(lignesAVoir.size(),i);
                        
                        gClone.getCase(i, q).setRecent(false);
                    }    
                }
                //System.out.println(G);
                colonnesAVoir.remove((Object) q);
            }
        }
        return gClone;
    }

    
    /**
     * This method returns a clone of {@link #Grille} colored and if possible completed, else it's uncompletable.
     * 
     * @param k Index of a {@link #Case} in this {@link #Grille}.
     * @param c Color of the {@link #Case}.
     * 
     * @return A clone of {@link #Grille} colored and if possible completed, else it's uncompletable.
     * 
     * @see Grille#grille
     */
    public Grille EnumRec(int k, int c){
        
        System.out.println("k:" + k + "\n");

        if (k == n*m){
            return this;
        }
        int i = k/m;
        int j = k%m;

        Grille gClone = this.colorierEtPropager(i, j, c);

        if (gClone.complet == 0){
            return null;
        }


        int k1;
        for (k1 = k+1; k1<n*m; k1++){
            i = k1/m;
            j = k1%m;
            if (gClone.getCouleur(i, j)== 0){
                Grille ABlanc = gClone.EnumRec(k1,1);
                if (ABlanc == null){
                    return gClone.EnumRec(k1,2);
                }
                return ABlanc;
            }
        }

        return gClone;
    }

    
    /**
     * This method returns the value of the field {@link #n} in this {@link #Grille}.
     * 
     * @return The value of th field {@link #n} in this {@link #Grille}.
     * 
     * @see Grille#n
     */
    public int getN(){
        return n;
    }

    /**
     * This method returns the value of the field {@link #m} in this {@link #Grille}.
     * @return The value of the field {@link #m} in this {@link #Grille}.
     * 
     * @see Grille#m
     */
    public int getM(){
        return m;
    }

    /**
     * This method returns the value of the field {@link #complet} in this {@link #Grille}.
     * 
     * @return The value of the field {@link #complet} in this {@link #Grille}.
     * 
     * @see Grille#complet
     */
    public int complet(){
        return complet;
    }

    /**
     * This method changes the value of the field {@link #complet} in this {@link #Grille}.
     * 
     * @param i The value that will be set on the field {@link #complet}.
     * 
     * @see Grille#complet
     */
    public void setComplet(int i){
        complet = i;
    }

    /**
     * This method returns the (i,j) {@link #Case} of this {@link #Grille}.
     * 
     * @param i The x-axis coordinate of the Case we want to reach.
     * @param j The y-axis coordinate of the Case we want to reach.
     * 
     * @return The (i,j) {@link #Case} of this {@link #Grille}.
     * 
     * @see Grille#grille
     */
    public Case getCase(int i, int j){
        return grille[i][j];
    }

    /**
     * This method allows us to know the color of the (i,j) {@link #Case} in this {@link #Grille}.
     * 
     * @param i The x-axis coordinate of the Case we want to reach.
     * @param j The y-axis coordinate of the Case we want to reach.
     * 
     * @return The color of the (i,j) {@link #Case} in this {@link #Grille}.
     */
    public int getCouleur(int i, int j){
        return this.getCase(i,j).getCouleur();
    }

    /**
     * This method returns the value of the field {@link #sCLigne} of this {@link #Grille}.
     * 
     * @return The field {@link #sLigne} of this {@link #Grille}.
     * 
     * @see Grille#sLigne
     */
    public Sequence[] getSequencesLigne(){
        return sLigne;
    }

    /**
     * This method returns the value of the field {@link #sColonne} of this {@link #Grille}.
     * 
     * @return The value of the field {@link #sColonne} of this {@link #Grille}.
     * 
     * @see Grille#sColonne
     */
    public Sequence[] getSequencesColonne(){
        return sColonne;
    }

    /**
     * This returns the value of the field {@link #s} of this {@link #Grille}.
     * 
     * @return The value of the field {@link #s} of this {@link #Grille}.
     * 
     * @see Grille#s
     */
    public String getSequences(){
        return s;
    }

    /**
     * This methods store the values in {@link #sLigne} in this {@link #Grille} in a String in order to see it in the trerminal.
     * 
     * @return A String that contains this {@link #Grille}.
     * 
     * @see Grille#sLigne
     */
    public String afficheSeqligne(){
        String s = "";
        for (Sequence seq : sLigne){
            s += seq + "\n";
        }
        return s + "\n";
    }

    /**
     * This methods store the values in {@link #sColonne} in this {@link #Grille} in a String in order to see it in the trerminal.
     * 
     * @return A String that contains this {@link #Grille}.
     * 
     * @see Grille#sColonne
     */
    public String afficheSeqColonne(){
        String s = "";
        for (Sequence seq : sColonne){
            s += seq + "\n";
        }
        return s + "\n";
    }

    /**
     * This methods store this {@link #Grille} in a String in order to see it in the trerminal.
     * 
     * @return A String that contains this {@link #Grille}.
     * 
     * @see Grille#grille
     */
    @Override
    public String toString(){
       String s = "";
       
       for(int i=0; i<tailleSeqColonne; i++){
           for(int k=0; k<tailleSeqLigne; k++){
               s+="  ";
           }
           s+=" ";
           for(Sequence seq : sColonne){
               if (seq.getTaille() <= i){ s+= "    ";}
               else {s+=" " + seq.getSequence_i(i) + "  ";}
           }
           s+="\n";
       }

       for (int i=0;i<n;i++){
           s+=sLigne[i].toString();
           for(int j = 0; j<tailleSeqLigne-sLigne[i].getTaille();j++){
               s+="  ";
           }
           s+="|";
           for(int j=0;j<m;j++){
               s+= " " + grille[i][j].getCouleur() + " |";
           }
           s+="\n";
        }

        return s;
    }



}