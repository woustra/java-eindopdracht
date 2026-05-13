/**
 * Dobbelsteen klasse - Representeert één dobbelsteen met waarde en berekent wakken, ijsberen en pinguïns
 * 
 * @author [Jouw naam]
 * @version 1.0
 */
public class Dobbelsteen {
    private int waarde;
    private int aantalWakken;
    private int aantalIjsberen;
    private int aantalPinguins;

    /**
     * Constructor - creëert een dobbelsteen met willekeurige waarde (1-6)
     */
    public Dobbelsteen() {
        this.waarde = (int) (Math.random() * 6) + 1;
        berekenWakkenEnIjsberen();
    }

    /**
     * Constructor - creëert een dobbelsteen met specifieke waarde (voor testen)
     * @param waarde De waarde van de dobbelsteen (1-6)
     */
    public Dobbelsteen(int waarde) {
        if (waarde < 1 || waarde > 6) {
            throw new IllegalArgumentException("Dobbelsteenwaarde moet tussen 1 en 6 zijn");
        }
        this.waarde = waarde;
        berekenWakkenEnIjsberen();
    }

    /**
     * Bepaalt het aantal wakken en ijsberen op basis van dobbelsteenwaarde
     * Wakken: alleen op oneven nummers (1, 3, 5)
     * Ijsberen: alleen als er een wak is
     *   - 1: 0 ijsberen
     *   - 3: 2 ijsberen
     *   - 5: 4 ijsberen
     */
    private void berekenWakkenEnIjsberen() {
        if (waarde == 1) {
            this.aantalWakken = 1;
            this.aantalIjsberen = 0;
        } else if (waarde == 3) {
            this.aantalWakken = 1;
            this.aantalIjsberen = 2;
        } else if (waarde == 5) {
            this.aantalWakken = 1;
            this.aantalIjsberen = 4;
        } else {
            // Even nummers (2, 4, 6) hebben geen wakken
            this.aantalWakken = 0;
            this.aantalIjsberen = 0;
        }

        // Pinguïns berekenen: tegenovergestelde zijde van dobbelsteen
        int tegenovergesteld = 7 - waarde;
        this.aantalPinguins = tegenovergesteld;
    }

    /**
     * Gooit de dobbelsteen opnieuw
     */
    public void gooien() {
        this.waarde = (int) (Math.random() * 6) + 1;
        berekenWakkenEnIjsberen();
    }

    // ======== GETTERS ========

    /**
     * @return De waarde op de dobbelsteen (1-6)
     */
    public int getWaarde() {
        return waarde;
    }

    /**
     * @return Het aantal wakken op deze dobbelsteen
     */
    public int getAantalWakken() {
        return aantalWakken;
    }

    /**
     * @return Het aantal ijsberen op deze dobbelsteen
     */
    public int getAantalIjsberen() {
        return aantalIjsberen;
    }

    /**
     * @return Het aantal pinguïns op de tegenovergestelde zijde
     */
    public int getAantalPinguins() {
        return aantalPinguins;
    }

    /**
     * @return String representatie van de dobbelsteen
     */
    @Override
    public String toString() {
        return String.format("Dobbelsteen: %d (Wakken: %d, Ijsberen: %d, Pinguïns: %d)", 
            waarde, aantalWakken, aantalIjsberen, aantalPinguins);
    }
}
