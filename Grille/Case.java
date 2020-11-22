package Grille;
public class Case{

    private int x,y;
    private int couleur; /* 0 non initialisé --- 1 = BLANC ----- 2 = BLACKNOIR*/
    private boolean recent;

    public Case(int x, int y, int couleur){
        this.x = x;
        this.y = y;
        this.couleur = couleur;
        this.recent = false;
    }

    public Case(int x, int y){
        this(x, y, 0);
    }

    /*public Case(int y){
        this.y = y;
        this.couleur = true;
    }*/

    public void changeCouleur(int cl){
        couleur = cl;
    }
    public void setRecent(boolean bool){
        recent = bool;
    }

    public boolean getRecent(){
        return recent;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getCouleur(){
        return couleur;
    }
}