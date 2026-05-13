import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * DobbelsteenPanel klasse - Visualiseert dobbelstenen op het scherm
 * 
 * @author [Jouw naam]
 * @version 1.0
 */
public class DobbelsteenPanel extends JPanel {
    private ArrayList<Dobbelsteen> dobbelstenen;
    private static final int STEEN_GROOTTE = 80;
    private static final int PADDING = 10;

    /**
     * Constructor - creëert een panel om dobbelstenen in te tonen
     * @param dobbelstenen De lijst van dobbelstenen om te tekenen
     */
    public DobbelsteenPanel(ArrayList<Dobbelsteen> dobbelstenen) {
        this.dobbelstenen = dobbelstenen;
        setPreferredSize(new Dimension(600, 150));
        setBackground(new Color(200, 220, 240));
    }

    /**
     * Update de dobbelstenen
     * @param dobbelstenen De nieuwe dobbelstenen om te tekenen
     */
    public void updateDobbelstenen(ArrayList<Dobbelsteen> dobbelstenen) {
        this.dobbelstenen = dobbelstenen;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = PADDING;
        int y = (getHeight() - STEEN_GROOTTE) / 2;

        for (Dobbelsteen dobbelsteen : dobbelstenen) {
            tekenDobbelsteen(g2d, x, y, dobbelsteen);
            x += STEEN_GROOTTE + PADDING;
        }
    }

    /**
     * Tekent één dobbelsteen met zijn puntjes
     * @param g Graphics object
     * @param x X positie
     * @param y Y positie
     * @param dobbelsteen De dobbelsteen om te tekenen
     */
    private void tekenDobbelsteen(Graphics2D g, int x, int y, Dobbelsteen dobbelsteen) {
        // Teken de vierkante vorm
        g.setColor(Color.WHITE);
        g.fillRect(x, y, STEEN_GROOTTE, STEEN_GROOTTE);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawRect(x, y, STEEN_GROOTTE, STEEN_GROOTTE);

        // Teken de puntjes
        int waarde = dobbelsteen.getWaarde();
        tekenPuntjes(g, x, y, waarde);

        // Teken de waarde eronder
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g.getFontMetrics();
        String text = String.valueOf(waarde);
        int textX = x + (STEEN_GROOTTE - fm.stringWidth(text)) / 2;
        int textY = y + STEEN_GROOTTE + fm.getAscent() + 5;
        g.drawString(text, textX, textY);
    }

    /**
     * Tekent de puntjes op een dobbelsteen gebaseerd op de waarde
     * @param g Graphics object
     * @param x X positie van dobbelsteen
     * @param y Y positie van dobbelsteen
     * @param waarde De waarde van de dobbelsteen
     */
    private void tekenPuntjes(Graphics2D g, int x, int y, int waarde) {
        g.setColor(new Color(50, 50, 50));
        int dotSize = 8;
        int dotX, dotY;

        switch (waarde) {
            case 1:
                // Middelpunt
                dotX = x + STEEN_GROOTTE / 2 - dotSize / 2;
                dotY = y + STEEN_GROOTTE / 2 - dotSize / 2;
                g.fillOval(dotX, dotY, dotSize, dotSize);
                break;

            case 2:
                // Diagonaal
                g.fillOval(x + 10, y + 10, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + STEEN_GROOTTE - 20, dotSize, dotSize);
                break;

            case 3:
                // Diagonaal lijn
                g.fillOval(x + 10, y + 10, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE / 2 - dotSize / 2, y + STEEN_GROOTTE / 2 - dotSize / 2, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + STEEN_GROOTTE - 20, dotSize, dotSize);
                break;

            case 4:
                // Vier hoeken
                g.fillOval(x + 10, y + 10, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + 10, dotSize, dotSize);
                g.fillOval(x + 10, y + STEEN_GROOTTE - 20, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + STEEN_GROOTTE - 20, dotSize, dotSize);
                break;

            case 5:
                // Vier hoeken + midden
                g.fillOval(x + 10, y + 10, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + 10, dotSize, dotSize);
                g.fillOval(x + 10, y + STEEN_GROOTTE - 20, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + STEEN_GROOTTE - 20, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE / 2 - dotSize / 2, y + STEEN_GROOTTE / 2 - dotSize / 2, dotSize, dotSize);
                break;

            case 6:
                // Twee kolommen van drie
                g.fillOval(x + 10, y + 10, dotSize, dotSize);
                g.fillOval(x + 10, y + STEEN_GROOTTE / 2 - dotSize / 2, dotSize, dotSize);
                g.fillOval(x + 10, y + STEEN_GROOTTE - 20, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + 10, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + STEEN_GROOTTE / 2 - dotSize / 2, dotSize, dotSize);
                g.fillOval(x + STEEN_GROOTTE - 20, y + STEEN_GROOTTE - 20, dotSize, dotSize);
                break;
        }
    }
}
