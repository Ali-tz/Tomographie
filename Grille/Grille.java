package Grille;
public class Grille{
    private int n, m;
    private Case[][] grille;
    private Sequence[] sLigne;
    private Sequence[] sColonne;
    private int tailleSeqColonne;
    private int tailleSeqLigne;
    private String s;

    public Grille(String s){

        this.s = s;

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

    public Grille clone(Grille G){
        return new Grille(G.getSequences());
    }

    public void setColor(int i,int j,int cl){
        this.getCase(i,j).changeCouleur(cl);
    }

    public int getN(){
        return n;
    }

    public int getM(){
        return m;
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