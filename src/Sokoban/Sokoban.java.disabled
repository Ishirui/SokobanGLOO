package Sokoban;

import javax.swing.SwingUtilities;

import Interface.MaFenetre;
import Controle.Controleur;
import Modele.Image;

public class Sokoban implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Sokoban());
    }

    @Override
    public void run() {
        Controleur controleur = new Controleur();
        MaFenetre fenetre = new MaFenetre(controleur);
    }

}
