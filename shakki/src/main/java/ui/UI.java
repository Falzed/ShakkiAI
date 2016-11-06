package ui;

import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import logic.Game;
import logic.parser.ParserReturn;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.GroupLayout.*;
import logic.parser.Parser;
import java.awt.event.KeyEvent;
import variants.*;
import fairy.*;
import java.io.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Luokka toteuttaa ohjelman graafisen käyttöliittymän. Siirrot syötetään laudan
 * alapuolella olevaan tekstikenttään, joko muodossa (algebrallinen
 * koordinaatti)-(algebrallinen koordinaatti) tai liikutettava nappula
 * algebrallisessa notaatiossa ja pelkkä loppupiste (esim Qd6 siirtää
 * kuningattaren d6:een). Sotilaalla syödessä lisätään x tällöin x komennon
 * eteen.
 *
 * @author Oskari Kulmala
 */
public class UI extends javax.swing.JFrame {

    private JPanel[][] panels;
    private JLabel[][] labels;

    public UI(Game peli) {
        initComponents(peli);
    }

    //Netbeansin generoimaa, refaktoroin toivottavasti myöhemmin luettavampaan muotoon
    @SuppressWarnings("unchecked")
    private void initComponents(Game peli) {
        this.peli = peli;
        this.variant = new Standard();

        historiaKentta = new javax.swing.JTextArea(20, 20);
        historiaButton = new javax.swing.JButton();
        historiaButton.setText("Submit");

        scrollpane = new javax.swing.JScrollPane();
        Dimension dimension = new Dimension(100, 100);

        this.panels = new JPanel[peli.getLauta().getLeveys()][peli.getLauta().getPituus()];
        this.labels = new JLabel[peli.getLauta().getLeveys()][peli.getLauta().getPituus()];
        JLabel[][] coordinateLabels = new JLabel[peli.getLauta().getLeveys()][peli.getLauta().getPituus()];

        for (int i = 0; i < peli.getLauta().getLeveys(); i++) {
            for (int j = 0; j < peli.getLauta().getPituus(); j++) {
                panels[i][j] = new JPanel();
//                panels[i][j].setMinimumSize(dimension);
                panels[i][j].setPreferredSize(dimension);
                labels[i][j] = new JLabel("");
                char merkki = (char) (97 + i);
                String label = Character.toString(merkki);
                label += Integer.toString(j + 1);
                coordinateLabels[i][j] = new JLabel(label);
                labels[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                labels[i][j].setVerticalAlignment(javax.swing.SwingConstants.CENTER);
                labels[i][j].setFont(new Font("Unifont", 1, 50));
                if ((i + j) % 2 == 0) {
                    panels[i][j].setBackground(new java.awt.Color(210, 140, 70));
                } else {
                    panels[i][j].setBackground(new java.awt.Color(255, 255, 255));
                }
            }
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        String merkki = Character.toString('\u265A');
        javax.swing.GroupLayout[][] groupLayouts = new javax.swing.GroupLayout[peli.getLauta().getLeveys()][peli.getLauta().getPituus()];
        for (int i = 0; i < peli.getLauta().getLeveys(); i++) {
            for (int j = 0; j < peli.getLauta().getPituus(); j++) {
                groupLayouts[i][j] = new javax.swing.GroupLayout(panels[i][j]);
                panels[i][j].setLayout(groupLayouts[i][j]);
                groupLayouts[i][j].setHorizontalGroup(
                        groupLayouts[i][j].createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayouts[i][j].createSequentialGroup()
                                .addGap(2)
                                .addComponent(coordinateLabels[i][j])
                                .addGap(0, 9, 100)
                                .addComponent(labels[i][j])
                                .addContainerGap(24, Short.MAX_VALUE)
                        )
                //                        .addComponent(coordinateLabels[i][j])
                //                        .addComponent(labels[i][j])
                );
                groupLayouts[i][j].setVerticalGroup(
                        groupLayouts[i][j].createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayouts[i][j].createSequentialGroup()
                                .addGap(3)
                                .addComponent(coordinateLabels[i][j])
                                .addGap(3)
                                .addComponent(labels[i][j])
                                .addContainerGap(25, Short.MAX_VALUE)
                        )
                );
            }
        }

        komentoKentta = new javax.swing.JTextField();
        komentoKentta.setText("");
        komentoKentta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                suoritaKomento(komentoKentta.getText());
            }
        });

        historiaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!peli.getTurnHistory().toString().equals(historiaKentta.getText())) {
                    String historiaString = historiaKentta.getText();
                    String vanhaHistoriaString = peli.getTurnHistory().toString();
                    Game testPeli = new Game(variant);
                    ParserReturn parserReturn = testPeli.applyHistory(historiaString);
                    if(!parserReturn.getError().isEmpty()) {
                        popUpErrorMessage(parserReturn.getError());
                        peli.clearHistory();
                        peli.applyHistory(vanhaHistoriaString);
                    } else {
                        peli.clearHistory();
                        peli.applyHistory(historiaString);
                    }
                    updateUI();
                }
            }
        });
        HiiriKuuntelija hiiriKuuntelija = new HiiriKuuntelija(panels[0].length, panels.length, panels, this);
        for (JPanel[] panelRivi : panels) {
            for (JPanel panel : panelRivi) {
                panel.addMouseListener(hiiriKuuntelija);
            }
        }
        JPanel lautaPanel = new JPanel();
        javax.swing.GroupLayout lautaLayout = new javax.swing.GroupLayout(lautaPanel);
        lautaPanel.setLayout(lautaLayout);
//        getContentPane().setLayout(lautaLayout);
        Group parallel = lautaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        for (int j = 0; j < peli.getLauta().getPituus(); j++) {
            Group sequential = lautaLayout.createSequentialGroup();

            for (int i = 0; i < peli.getLauta().getLeveys(); i++) {
                sequential.addComponent(panels[i][j], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
            }
            parallel.addGroup(sequential);
        }
        parallel.addComponent(komentoKentta);
        lautaLayout.setHorizontalGroup(parallel);

        SequentialGroup sequential = lautaLayout.createSequentialGroup();
        for (int j = peli.getLauta().getPituus() - 1; j >= 0; j--) {
            Group parallelLoop = lautaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
            for (int i = 0; i < peli.getLauta().getLeveys(); i++) {
                parallelLoop.addComponent(panels[i][j], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                if (i != peli.getLauta().getLeveys() - 1) {
                    parallelLoop.addGap(100);
                }
            }
            sequential.addGroup(parallelLoop);
        }
        sequential.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        sequential.addComponent(komentoKentta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        lautaLayout.setVerticalGroup(
                lautaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sequential)
        );

//        historiaKentta.setColumns(20);
//        historiaKentta.setRows(5);
//        historiaKentta.setText("");
        scrollpane.setViewportView(historiaKentta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lautaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(historiaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                ));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lautaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(historiaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )));

        pack();

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        JMenuItem standard = new JMenuItem("standard", icon);
        standard.setMnemonic(KeyEvent.VK_0);
        standard.setToolTipText("Standardi shakki");
        standard.addActionListener((ActionEvent event) -> {
            vaihdaVarianttia(new Standard());
        });
        JMenuItem charge = new JMenuItem("charge", icon);
        charge.setMnemonic(KeyEvent.VK_1);
        charge.setToolTipText("Charge of the Light Brigade");
        charge.addActionListener((ActionEvent event) -> {
            vaihdaVarianttia(new FairyVariant(new File("src/main/resources/chargeOfTheLightBrigade.xml")));
        });
        JMenuItem dunsanys = new JMenuItem("Dunsany's", icon);
        dunsanys.setMnemonic(KeyEvent.VK_2);
        dunsanys.setToolTipText("Dunsany's Chess");
        dunsanys.addActionListener((ActionEvent event) -> {
            vaihdaVarianttia(new FairyVariant(new File("src/main/resources/dunsanysChess.xml")));
        });
        JMenuItem open = new JMenuItem("Open", icon);
        open.setMnemonic(KeyEvent.VK_3);
        open.setToolTipText("Open");
        open.addActionListener((ActionEvent event) -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(this.historiaKentta);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                vaihdaVarianttia(new FairyVariant(file));
            }
        });

        fileMenu.add(eMenuItem);
        fileMenu.add(standard);
        fileMenu.add(charge);
        fileMenu.add(dunsanys);
        fileMenu.add(open);

        menubar.add(fileMenu);

        setJMenuBar(menubar);
    }// </editor-fold>                        

    /**
     * Metodi päivittää UI:n.
     */
    public void updateUI() {
        historiaKentta.setText(peli.getTurnHistory().toString());
        for (int i = 0; i < peli.getLauta().getLeveys(); i++) {
            for (int j = 0; j < peli.getLauta().getPituus(); j++) {
                int[] koordinaatit = {i, j};
                labels[i][j].setText(Character.toString(peli.getLauta().getNappula(koordinaatit).getMerkki()));
            }
        }
    }

    private void vaihdaVarianttia(Variant variant) {
        this.variant = variant;
        peli.vaihdaVariantti(variant);
        updateUI();
    }

    public String popupKorotus() {
        JFrame frame = new JFrame("Korotus");

        Object[] possibilities = {"Kuningatar", "Ratsu", "Torni", "Lähetti"};
        String s = (String) JOptionPane.showInputDialog(
                frame,
                "",
                "Sotilaan korotus",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Kuningatar");
        return s;
    }

    public void popUpErrorMessage(String string) {
        JFrame frame = new JFrame("Korotus");
        JOptionPane.showMessageDialog(frame, string);
    }

    public String getKomento() {
        return komentoKentta.getText();
    }

    public JLabel[][] getLabels() {
        return this.labels;
    }

    public void setHistoriaKentta(String string) {
        this.historiaKentta.setText(string);
    }

    public void clearKomentoKentta() {
        this.komentoKentta.setText("");
    }

    public String getHistoriaKentta() {
        return historiaKentta.getText();
    }

    public void suoritaKomento(String komento) {
        ParserReturn komentoReturn = peli.suoritaKomento(komento);
        int[][] startEndPoints = komentoReturn.getCoordinates();
//                System.out.println(komentoReturn);
        if (!komentoReturn.getError().isEmpty()) {
            popUpErrorMessage(komentoReturn.getError());
        }
        if (startEndPoints != null) {
            if (peli.tarkistaKorotus(startEndPoints[1])) {
                peli.korota(startEndPoints[1], popupKorotus());
            }
            historiaKentta.setText(peli.getTurnHistory().toString());
        }

        komentoKentta.setText("");

        updateUI();

        if (peli.tarkistaMatti()) {
            popUpErrorMessage(peli.getVuoro() + " on matissa");
        }
        if (peli.tarkistaPatti()) {
            popUpErrorMessage(peli.getVuoro() + " on patissa, tasapeli");
        }
    }

    public void hiiriKomento(int[][] startEndPoints) {
//        System.out.println(Parser.parseToAlgebraicCommand(startEndPoints));
        suoritaKomento(Parser.parseToAlgebraicCommand(startEndPoints));
        unselectAll();
    }

    public void toggleValittu(int[] koordinaatit) {
        if (panels[koordinaatit[0]][koordinaatit[1]].getBackground().equals(new java.awt.Color(255, 255, 255))) {
            panels[koordinaatit[0]][koordinaatit[1]].setBackground(new java.awt.Color(200, 200, 200));
        } else if (panels[koordinaatit[0]][koordinaatit[1]].getBackground().equals(new java.awt.Color(210, 140, 70))) {
            panels[koordinaatit[0]][koordinaatit[1]].setBackground(new java.awt.Color(160, 90, 20));
        } else if (panels[koordinaatit[0]][koordinaatit[1]].getBackground().equals(new java.awt.Color(200, 200, 200))) {
            panels[koordinaatit[0]][koordinaatit[1]].setBackground(new java.awt.Color(255, 255, 255));
        } else if (panels[koordinaatit[0]][koordinaatit[1]].getBackground().equals(new java.awt.Color(160, 90, 20))) {
            panels[koordinaatit[0]][koordinaatit[1]].setBackground(new java.awt.Color(210, 140, 70));
        }
    }

    public void unselectAll() {
        for (int i = 0; i < panels[0].length; i++) {
            for (int j = 0; j < panels.length; j++) {
                if (panels[i][j].getBackground().equals(new java.awt.Color(200, 200, 200))) {
                    panels[i][j].setBackground(new java.awt.Color(255, 255, 255));
                } else if (panels[i][j].getBackground().equals(new java.awt.Color(160, 90, 20))) {
                    panels[i][j].setBackground(new java.awt.Color(210, 140, 70));
                }
            }
        }
    }

    private javax.swing.JTextField komentoKentta;

    private javax.swing.JTextArea historiaKentta;
    private javax.swing.JScrollPane scrollpane;

    private JButton historiaButton;

    private Game peli;
    private Variant variant;
}
