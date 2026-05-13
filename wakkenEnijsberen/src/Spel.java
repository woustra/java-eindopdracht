/**
 * Spel klasse - Beheert de spellogica van "Wakken, IJsberen en Penquïns"
 * 
 * @author [Jouw naam]
 * @version 1.0
 */
public class Spel {
    private Spelset spelset;
    private ScoreTracker score;
    private int variant;

    /**
     * Constructor - creëert een nieuw spel met opgegeven aantal dobbelstenen
     * @param aantalDobbelstenen Het aantal dobbelstenen (3-8)
     * @param variant De variant van het spel (1=origineel)
     */
    public Spel(int aantalDobbelstenen, int variant) {
        if (aantalDobbelstenen < 3 || aantalDobbelstenen > 8) {
            throw new IllegalArgumentException("Aantal dobbelstenen moet tussen 3 en 8 zijn");
        }
        this.spelset = new Spelset(aantalDobbelstenen);
        this.score = new ScoreTracker();
        this.variant = variant;
    }

    /**
     * Gooit alle dobbelstenen en registreert dit in de score
     */
    public void gooienDobbelstenen() {
        spelset.gooienAlle();
        score.werpGedaan();
    }

    /**
     * Controleert of de gegeven antwoorden correct zijn
     * @param gegokenWakken Het aantal wakken gegist
     * @param gegokenIjsberen Het aantal ijsberen gegist
     * @param gegokenPinguins Het aantal pinguïns gegist
     * @return true als alle antwoorden correct zijn, false anders
     */
    public boolean controleerAntwoord(int gegokenWakken, int gegokenIjsberen, int gegokenPinguins) {
        int werkelijkeWakken = spelset.berekenTotaalWakken();
        int werkelijkeIjsberen = spelset.berekenTotaalIjsberen();
        int werkelijkePinguins = spelset.berekenTotaalPinguins();

        boolean correct = (gegokenWakken == werkelijkeWakken && 
                          gegokenIjsberen == werkelijkeIjsberen && 
                          gegokenPinguins == werkelijkePinguins);

        if (correct) {
            score.antwoordCorrect();
        } else {
            score.antwoordOnjuist();
        }

        return correct;
    }

    /**
     * Geeft een hint door het aantal pogingen
     * @return Een hint string
     */
    public String geefHint() {
        score.hintGebruikt();
        int pogingen = score.getAantalOnjuist();

        if (pogingen < 3) {
            return "Kijk goed naar de patronen op de dobbelstenen!";
        } else if (pogingen < 5) {
            return "Oneven nummers hebben wakken. Even nummers niet!";
        } else {
            return "Wakken: " + spelset.berekenTotaalWakken() + 
                   ", Ijsberen: " + spelset.berekenTotaalIjsberen() + 
                   ", Pinguïns: " + spelset.berekenTotaalPinguins();
        }
    }

    /**
     * Geeft de juiste antwoorden
     * @return String met de antwoorden
     */
    public String geefJuisteAntwoorden() {
        return String.format("Juist: Wakken=%d, Ijsberen=%d, Pinguïns=%d",
            spelset.berekenTotaalWakken(),
            spelset.berekenTotaalIjsberen(),
            spelset.berekenTotaalPinguins());
    }

    /**
     * Reset het spel
     */
    public void reset() {
        spelset = new Spelset(spelset.getAantal());
        score.reset();
    }

    // ======== GETTERS ========

    /**
     * @return De huidige spelset
     */
    public Spelset getSpelset() {
        return spelset;
    }

    /**
     * @return De huidige score
     */
    public ScoreTracker getScore() {
        return score;
    }

    /**
     * @return De huidige variant
     */
    public int getVariant() {
        return variant;
    }

    /**
     * @return String representatie van het spel
     */
    @Override
    public String toString() {
        return "Spel [" + spelset.getAantal() + " dobbelstenen, variant " + variant + "]\n" + 
               spelset.toString() + "\n" + score.toString();
    }
}
