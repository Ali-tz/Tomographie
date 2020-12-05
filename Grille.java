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
     * private field, represents the number of lines of this {@link #Grille}.
     */
    private int n;

    /**
     * private field, represents the number of columns of this {@link #Grille}.
     */
    private int m;

    /**
     * private field, represents this {@link #Grille} fulfilled by cases of {@link #Case}.
     */
    private Case[][] grille;

    /**
     * private field, represents the sequences of the length of the lines' blocks.
     */
    private Sequence[] sLigne;

    /**
     * private field, represents the sequences of the length of the columns' blocks.
     */
    private Sequence[] sColonne;

    /**
     * private field, reprensents the length of {@link #sColonne}.
     */
    private int tailleSeqColonne;

    /**
     * private field, reprensents the length of {@link #sLigne}.
     */
    private int tailleSeqLigne;

    /**
     * private field, used to store the parsed file used to build this {@link #Grille}.
     */
    private String s;

    /**
     * private fiels, tells if this {@link #Grille} is fullfield. 1 means it's completed, 0 means uncompletable and -1 we don't know.
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
     */
    public void setColor(int i,int j,int cl){
        this.getCase(i,j).changeCouleur(cl);
    }
    

    /**
     * This method returns this {@link #Grille} colored and if possible completed.
     * 
     * @return This {@link #Grille} completed if possible.
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
     * This method returns a clone of {@link #Grille} colored and if possible completed, else it's uncompletable
     * 
     * @param k Index of a {@link #Case} in this {@link #Grille}.
     * @param c Color of the {@link #Case}
     * 
     * @return A clone of {@link #Grille} colored and if possible completed, else it's uncompletable
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
     * This method allows us to reach the field {@link #n}
     * @return The number of lines of this {@link #Grille}
     */
    public int getN(){
        return n;
    }

    /**
     * 
     * @return
     */
    public int getM(){
        return m;
    }

    public int complet(){
        return complet;
    }
    public void setComplet(int i){
        complet = i;
    }

    public Case getCase(int i, int j){
        return grille[i][j];
    }

    public int getCouleur(int i, int j){
        return this.getCase(i,j).getCouleur();
    }

    public Sequence[] getSequencesLigne(){
        return sLigne;
    }

    public Sequence[] getSequencesColonne(){
        return sColonne;
    }

    public String getSequences(){
        return s;
    }

    public String afficheSeqligne(){
        String s = "";
        for (Sequence seq : sLigne){
            s += seq + "\n";
        }
        return s + "\n";
    }

    public String afficheSeqColonne(){
        String s = "";
        for (Sequence seq : sColonne){
            s += seq + "\n";
        }
        return s + "\n";
    }


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