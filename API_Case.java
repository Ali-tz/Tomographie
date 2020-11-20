import  javax.swing.*;
import java.awt.*;
import Grille.Case;;

public class API_Case {
    private Case c;
    private JPanel pan;

    public API_Case(Case c) {
        this.c = c;

        pan = new JPanel();
        if (c.getCouleur() == 2) {
            pan.setBackground(Color.black);
        }
        else{
            pan.setBackground(Color.white);
        }
    }

    public Case getCase(){
        return c;
    }
}