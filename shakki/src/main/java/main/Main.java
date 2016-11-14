package main;

import components.Nappula;
import logic.Game;
import ui.*;
import variants.Standard;

public class Main {

    /**
     * Pääohjelma.
     *
     * @param args eivät tee mitään
     */
    public static void main(String[] args) {
        Game peli = new Game(new Standard());
        UI ui = new UI(peli);
//        AIvsAIUI ui = new AIvsAIUI(peli);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ui.setVisible(true);
                ui.updateUI();
            }
        });

    }
}
