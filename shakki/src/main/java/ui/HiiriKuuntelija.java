package ui;

import java.awt.event.*;
import javax.swing.JPanel;
import logic.parser.Parser;

/**
 * MouseListener ruudun valinnalle.
 * @author Oskari Kulmala
 */
public class HiiriKuuntelija implements MouseListener {

    private boolean ruutuValittu;
    private int[] valittuRuutu;
    private int[][] laudanRuudut;
    private JPanel[][] ruudut;
    private UI ui;
 
    /**
     * Konstruktori.
     * @param leveys laudan leveys
     * @param pituus laudan pituus
     * @param ruudut laudan ruudut (JPanelit)
     * @param ui käytössä oleva ui
     */
    public HiiriKuuntelija(int leveys, int pituus, JPanel[][] ruudut, UI ui) {
        this.laudanRuudut = new int[leveys][pituus];
        this.valittuRuutu = new int[2];
        this.ruudut = ruudut;
        this.ui = ui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!ruutuValittu) {
            for (int i = 0; i < laudanRuudut[0].length; i++) {
                if (ruutuValittu) {
                    break;
                }
                for (int j = 0; j < laudanRuudut.length; j++) {
                    if (ruudut[i][j] == e.getSource()) {
                        valittuRuutu[0] = i;
                        valittuRuutu[1] = j;
                        ruutuValittu = true;
                        break;
                    }
                }
            }
            ui.toggleValittu(valittuRuutu);
        } else {
            int[][] startEndPoints = new int[2][2];
//            System.out.println(Parser.parseToAlgebraic(valittuRuutu));
            startEndPoints[0][0] = this.valittuRuutu[0];
            startEndPoints[0][1] = this.valittuRuutu[1];
            ruutuValittu = false;
            for (int i = 0; i < laudanRuudut[0].length; i++) {
                if (ruutuValittu) {
                    break;
                }
                for (int j = 0; j < laudanRuudut.length; j++) {
                    if (ruudut[i][j] == e.getSource()) {
                        valittuRuutu[0] = i;
                        valittuRuutu[1] = j;
                        ruutuValittu = true;
                        break;
                    }
                }
            }
            startEndPoints[1][0] = this.valittuRuutu[0];
            startEndPoints[1][1] = this.valittuRuutu[1];
            this.valittuRuutu = new int[2];
            this.ruutuValittu = false;
            ui.hiiriKomento(startEndPoints);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
