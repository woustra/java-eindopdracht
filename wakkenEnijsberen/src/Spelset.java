import java.util.ArrayList;

/**
 * Spelset klasse - Beheert een set dobbelstenen en berekent totalen
 * 
 * @author [Jouw naam]
 * @version 1.0
 */
public class Spelset {
    private ArrayList<Dobbelsteen> dobbelstenen;

    /**
     * Constructor - creëert een spelset met een bepaald aantal dobbelstenen
     * @param aantal Het aantal dobbelstenen (3-8)
     */
    public Spelset(int aantal) {
        if (aantal < 3 || aantal > 8) {
            throw new IllegalArgumentException("Aantal dobbelstenen moet tussen 3 en 8 zijn");
        }
        this.dobbelstenen = new ArrayList<>();
        for (int i = 0; i < aantal; i++) {
            dobbelstenen.add(new Dobbelsteen());
        }
    }

    /**
     * Gooit alle dobbelstenen opnieuw
     */
    public void gooienAlle() {
        for (Dobbelsteen d : dobbelstenen) {
            d.gooien();
        }
    }

    /**
     * Berekent het totale aantal wakken in alle dobbelstenen
     * @return Het totale aantal wakken
     */
    public int berekenTotaalWakken() {
        int totaal = 0;
        for (Dobbelsteen d : dobbelstenen) {
            totaal += d.getAantalWakken();
        }
        return totaal;
    }

    /**
     * Berekent het totale aantal ijsberen in alle dobbelstenen
     * @return Het totale aantal ijsberen
     */
    public int berekenTotaalIjsberen() {
        int totaal = 0;
        for (Dobbelsteen d : dobbelstenen) {
            totaal += d.getAantalIjsberen();
        }
        return totaal;
    }

    /**
     * Berekent het totale aantal pinguïns in alle dobbelstenen
     * @return Het totale aantal pinguïns
     */
    public int berekenTotaalPinguins() {
        int totaal = 0;
        for (Dobbelsteen d : dobbelstenen) {
            totaal += d.getAantalPinguins();
        }
        return totaal;
    }

    // ======== GETTERS ========

    /**
     * @return De ArrayList van dobbelstenen
     */
    public ArrayList<Dobbelsteen> getDobbelstenen() {
        return dobbelstenen;
    }

    /**
     * @return Het aantal dobbelstenen in deze set
     */
    public int getAantal() {
        return dobbelstenen.size();
    }

    /**
     * Geeft een specifieke dobbelsteen
     * @param index De index van de dobbelsteen
     * @return De Dobbelsteen op deze index
     */
    public Dobbelsteen getDobbelsteen(int index) {
        return dobbelstenen.get(index);
    }

    /**
     * @return String representatie van de hele spelset
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Spelset: \n");
        for (int i = 0; i < dobbelstenen.size(); i++) {
            sb.append(i + 1).append(". ").append(dobbelstenen.get(i)).append("\n");
        }
        sb.append("Totaal: Wakken=").append(berekenTotaalWakken())
          .append(", Ijsberen=").append(berekenTotaalIjsberen())
          .append(", Pinguïns=").append(berekenTotaalPinguins());
        return sb.toString();
    }
}
