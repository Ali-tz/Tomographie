/**
 * Case is the class representing a black, white or none colored case.
 * Start class for this poject.
 * 
 * @author Sylvain Rakotomalala, Ali Touzi
 */
public class Case{



    //  ************************************************************************

    //  Fields

    //  ************************************************************************

    /**
     * Private field, represents the x-axis.
     * 
     * @see Case#Case(int, int, int)
     * @see Case#Case(int, int)
     * @see Case#getX()
     */
    private int x;

    /**
     * Private field, represents the y-axis.
     * 
     * @see Case#Case(int, int, int)
     * @see Case#Case(int, int)
     * @see Case#getY()
     */
    private int y;


    /**
     * Private field, represents the color of this {@link #Case}; 0 means no color, 1 means white and 2 means black.
     * 
     * @see Case#Case(int, int, int)
     * @see Case#Case(int, int)
     * @see Case#getCouleur()
     * @see Case#changeCouleur(int)
     */
    private int couleur; 

    /**
     * Private field, tells if this {@link #Case} was recently visited.
     * 
     * @see Case#getRecent()
     * @see Case#setRecent(boolean)
     */
    private boolean recent;




    //  *************************************************************************

    //  Constructors

    //  *************************************************************************

    /**
     * Parameterized constructor.
     * 
     * The {@link #recent} value is always set to false.
     * 
     * @param x The coordinate value  that will be set on the x-axis to this {@link #Case}.
     * @param y The coordinate value that will be set on the y-axis to this {@link #Case}. 
     * @param couleur The color value that will be set on couleur to this {@link #Case}.
     * 
     * @see Case#x
     * @see Case#y
     * @see Case#couleur
     * 
     */
    public Case(int x, int y, int couleur){
        this.x = x;
        this.y = y;
        this.couleur = couleur;
        this.recent = false;
    }

    /**
     * Default constructor.
     * 
     * The {@link #couleur} value will be 0 and the recent value is always set to false..
     * 
     * @param x The coordinate value  that will be set on the x-axis to this {@link #Case}.
     * @param y he coordinate value that will be set on the y-axis to this {@link #Case}. 
     * 
     * @see Case#x
     * @see Case#y
     */
    public Case(int x, int y){
        this(x, y, 0);
    }

    
    
    //  *************************************************************************

    //  Public methods

    //  *************************************************************************
    
    /**
     * This method changes the color of the case
     * 
     * @param cl Value of the new color
     * 
     * @see Case#couleur
     * 
     */
    public void changeCouleur(int cl){
        couleur = cl;
    }
    

    /**
     * This method sets the field {@link #recent} to bool value in this {@link #Case}
     * 
     * @param bool new value for {@link #recent} in this {@link #Case}
     * 
     * @see Case#recent
     */
    public void setRecent(boolean bool){
        recent = bool;
    }


    /**
     * This method is used to reach the value of {@link #recent} in this {@link #Case}
     * 
     * @return The value of {@link #recent} in this {@link #Case}
     * 
     * @see Case#recent
     */
    public boolean getRecent(){
        return recent;
    }

    /**
     * This method returns the value of {@link #x} of this {@link #Case}
     * 
     * @return The value of {@link #x} of this {@link #Case}
     * 
     * @see Case#x
     */
    public int getX(){
        return x;
    }

    /**
     * This method returns the value of {@link #y} of this {@link #Case}
     * 
     * @return The value of {@link #x} of this {@link #Case}
     *
     * @see Case#y
     */
     public int getY(){
        return y;
    }

    /**
     * This method returns the value of {@link #couleur} of this {@link #Case}
     * 
     * @return The value of {@link #couleur} of this {@link #Case}
     * 
     * @see Case#couleur
     */
    public int getCouleur(){
        return couleur;
    }
}