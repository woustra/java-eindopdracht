import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HoofdVenster klasse - Het hoofdvenster van het spel "Wakken, IJsberen en Penquïns"
 * 
 * @author [Jouw naam]
 * @version 1.0
 */
public class HoofdVenster extends JFrame {
    private Spel spel;
    private DobbelsteenPanel dobbelsteenPanel;
    private JTextField tfWakken, tfIjsberen, tfPinguins;
    private JLabel lblScore, lblBericht;
    private JButton btnGooien, btnControleren, btnOplossing, btnHint, btnNieuw;
    private JSpinner spinnerAantal;
    private JLabel lblHints;

    /**
     * Constructor - creëert het hoofdvenster
     */
    public HoofdVenster() {
        setTitle("Wakken, IJsberen en Penquïns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Maak het spel
        spel = new Spel(3, 1);
        
        // Maak het panel voor dobbelstenen
        dobbelsteenPanel = new DobbelsteenPanel(spel.getSpelset().getDobbelstenen());

        // Zet alles in elkaar
        setupGUI();
    }

    /**
     * Setup alle GUI componenten
     */
    private void setupGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ===== TITEL =====
        JLabel lblTitel = new JLabel("WAKKEN, IJSBEREN EN PENQUINS");
        lblTitel.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitel);
        mainPanel.add(Box.createVerticalStrut(10));

        // ===== DOBBELSTEEN PANEL =====
        dobbelsteenPanel.setBorder(BorderFactory.createTitledBorder("Worpen"));
        mainPanel.add(dobbelsteenPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // ===== AANTAL DOBBELSTENEN =====
        JPanel panelAantal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAantal.add(new JLabel("Aantal dobbelstenen:"));
        spinnerAantal = new JSpinner(new SpinnerNumberModel(3, 3, 8, 1));
        spinnerAantal.setPreferredSize(new Dimension(50, 25));
        panelAantal.add(spinnerAantal);
        JButton btnWijzig = new JButton("Wijzig");
        btnWijzig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aantal = (int) spinnerAantal.getValue();
                spel = new Spel(aantal, 1);
                dobbelsteenPanel.updateDobbelstenen(spel.getSpelset().getDobbelstenen());
                resetUI();
            }
        });
        panelAantal.add(btnWijzig);
        mainPanel.add(panelAantal);
        mainPanel.add(Box.createVerticalStrut(10));

        // ===== KNOPPEN =====
        JPanel panelKnoppen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnGooien = new JButton("WERF DOBBELSTENEN");
        btnGooien.setFont(new Font("Arial", Font.BOLD, 12));
        btnGooien.setBackground(new Color(100, 200, 100));
        btnGooien.setForeground(Color.WHITE);
        btnGooien.setPreferredSize(new Dimension(180, 35));
        btnGooien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spel.gooienDobbelstenen();
                dobbelsteenPanel.updateDobbelstenen(spel.getSpelset().getDobbelstenen());
                updateScoreLabel();
                lblBericht.setText("Gis het aantal wakken, ijsberen en pinguïns!");
            }
        });
        panelKnoppen.add(btnGooien);
        mainPanel.add(panelKnoppen);
        mainPanel.add(Box.createVerticalStrut(10));

        // ===== INPUTVELDEN =====
        JPanel panelInput = new JPanel();
        panelInput.setBorder(BorderFactory.createTitledBorder("Jouw gissing"));
        panelInput.setLayout(new GridLayout(1, 3, 10, 0));

        panelInput.add(maakInputVeld("Wakken:", tfWakken = new JTextField(), 0));
        panelInput.add(maakInputVeld("Ijsberen:", tfIjsberen = new JTextField(), 1));
        panelInput.add(maakInputVeld("Pinguïns:", tfPinguins = new JTextField(), 2));

        mainPanel.add(panelInput);
        mainPanel.add(Box.createVerticalStrut(10));

        // ===== CONTROLE KNOPPEN =====
        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnControleren = new JButton("CONTROLEER ANTWOORD");
        btnControleren.setFont(new Font("Arial", Font.BOLD, 11));
        btnControleren.setBackground(new Color(100, 150, 200));
        btnControleren.setForeground(Color.WHITE);
        btnControleren.setPreferredSize(new Dimension(170, 30));
        btnControleren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleerAntwoord();
            }
        });
        panelControl.add(btnControleren);

        btnOplossing = new JButton("TOON OPLOSSING");
        btnOplossing.setFont(new Font("Arial", Font.BOLD, 11));
        btnOplossing.setBackground(new Color(200, 100, 100));
        btnOplossing.setForeground(Color.WHITE);
        btnOplossing.setPreferredSize(new Dimension(150, 30));
        btnOplossing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblBericht.setText(spel.geefJuisteAntwoorden());
                lblBericht.setForeground(new Color(200, 100, 100));
            }
        });
        panelControl.add(btnOplossing);

        btnHint = new JButton("HINT");
        btnHint.setFont(new Font("Arial", Font.BOLD, 11));
        btnHint.setBackground(new Color(200, 150, 50));
        btnHint.setForeground(Color.WHITE);
        btnHint.setPreferredSize(new Dimension(100, 30));
        btnHint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblBericht.setText(spel.geefHint());
                lblBericht.setForeground(new Color(200, 150, 50));
                lblHints.setText("Hints: " + spel.getScore().getAantalHints());
            }
        });
        panelControl.add(btnHint);

        mainPanel.add(panelControl);
        mainPanel.add(Box.createVerticalStrut(10));

        // ===== BERICHT =====
        lblBericht = new JLabel("");
        lblBericht.setFont(new Font("Arial", Font.ITALIC, 12));
        lblBericht.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblBericht);
        mainPanel.add(Box.createVerticalStrut(10));

        // ===== SCORE =====
        JPanel panelScore = new JPanel();
        panelScore.setBorder(BorderFactory.createTitledBorder("Score"));
        panelScore.setLayout(new BorderLayout());
        
        lblScore = new JLabel();
        lblScore.setFont(new Font("Arial", Font.PLAIN, 11));
        panelScore.add(lblScore, BorderLayout.WEST);

        lblHints = new JLabel("Hints: 0");
        lblHints.setFont(new Font("Arial", Font.PLAIN, 11));
        panelScore.add(lblHints, BorderLayout.EAST);

        mainPanel.add(panelScore);
        mainPanel.add(Box.createVerticalStrut(10));

        // ===== EXTRA BUTTONS =====
        JPanel panelExtra = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnNieuw = new JButton("NIEUW SPEL");
        btnNieuw.setFont(new Font("Arial", Font.BOLD, 12));
        btnNieuw.setBackground(new Color(150, 150, 150));
        btnNieuw.setForeground(Color.WHITE);
        btnNieuw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aantal = (int) spinnerAantal.getValue();
                spel = new Spel(aantal, 1);
                dobbelsteenPanel.updateDobbelstenen(spel.getSpelset().getDobbelstenen());
                resetUI();
            }
        });
        panelExtra.add(btnNieuw);
        mainPanel.add(panelExtra);

        // Scroll pane voor alle content
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        updateScoreLabel();
        lblBericht.setText("Klik op 'WERF DOBBELSTENEN' om te beginnen!");
    }

    /**
     * Maakt een input veld met label
     */
    private JPanel maakInputVeld(String label, JTextField tf, int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 11));
        tf.setPreferredSize(new Dimension(80, 25));
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lbl);
        panel.add(tf);
        return panel;
    }

    /**
     * Controleert het ingevoerde antwoord
     */
    private void controleerAntwoord() {
        try {
            int wakken = Integer.parseInt(tfWakken.getText());
            int ijsberen = Integer.parseInt(tfIjsberen.getText());
            int pinguins = Integer.parseInt(tfPinguins.getText());

            boolean correct = spel.controleerAntwoord(wakken, ijsberen, pinguins);

            if (correct) {
                lblBericht.setText("✓ CORRECT! Goed gedaan!");
                lblBericht.setForeground(new Color(0, 150, 0));
            } else {
                lblBericht.setText("✗ Onjuist. Probeer opnieuw!");
                lblBericht.setForeground(new Color(200, 0, 0));
            }

            updateScoreLabel();
        } catch (NumberFormatException e) {
            lblBericht.setText("Voer alstublieft geldige getallen in!");
            lblBericht.setForeground(Color.RED);
        }
    }

    /**
     * Update de score label
     */
    private void updateScoreLabel() {
        lblScore.setText(String.format("Worpen: %d | Correct: %d | Onjuist: %d",
            spel.getScore().getAantalWorpen(),
            spel.getScore().getAantalCorrect(),
            spel.getScore().getAantalOnjuist()));
    }

    /**
     * Reset de UI voor een nieuw spel
     */
    private void resetUI() {
        tfWakken.setText("");
        tfIjsberen.setText("");
        tfPinguins.setText("");
        lblBericht.setText("Klik op 'WERF DOBBELSTENEN' om te beginnen!");
        lblHints.setText("Hints: 0");
        updateScoreLabel();
    }

    /**
     * Main methode om het programma te starten
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HoofdVenster frame = new HoofdVenster();
                frame.setVisible(true);
            }
        });
    }
}
