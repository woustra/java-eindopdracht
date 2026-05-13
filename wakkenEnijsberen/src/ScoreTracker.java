/**
 * ScoreTracker klasse - Houdt de spelstatistieken bij
 * 
 * @author [Jouw naam]
 * @version 1.0
 */
public class ScoreTracker {
    private int aantalWorpen;
    private int aantalCorrect;
    private int aantalOnjuist;
    private int aantalHints;

    /**
     * Constructor - initialiseert een lege score tracker
     */
    public ScoreTracker() {
        this.aantalWorpen = 0;
        this.aantalCorrect = 0;
        this.aantalOnjuist = 0;
        this.aantalHints = 0;
    }

    /**
     * Voegt een worp toe aan de teller
     */
    public void werpGedaan() {
        aantalWorpen++;
    }

    /**
     * Registreert een correct antwoord
     */
    public void antwoordCorrect() {
        aantalCorrect++;
    }

    /**
     * Registreert een onjuist antwoord
     */
    public void antwoordOnjuist() {
        aantalOnjuist++;
    }

    /**
     * Telt een hint op
     */
    public void hintGebruikt() {
        aantalHints++;
    }

    /**
     * Reset alle scores
     */
    public void reset() {
        this.aantalWorpen = 0;
        this.aantalCorrect = 0;
        this.aantalOnjuist = 0;
        this.aantalHints = 0;
    }

    // ======== GETTERS ========

    /**
     * @return Het aantal worpen gedaan
     */
    public int getAantalWorpen() {
        return aantalWorpen;
    }

    /**
     * @return Het aantal correcte antwoorden
     */
    public int getAantalCorrect() {
        return aantalCorrect;
    }

    /**
     * @return Het aantal onjuiste antwoorden
     */
    public int getAantalOnjuist() {
        return aantalOnjuist;
    }

    /**
     * @return Het aantal hints gebruikt
     */
    public int getAantalHints() {
        return aantalHints;
    }

    /**
     * @return Het aantal gissingen tot nu toe (correct + onjuist)
     */
    public int getTotaalGissingen() {
        return aantalCorrect + aantalOnjuist;
    }

    /**
     * @return String representatie van de score
     */
    @Override
    public String toString() {
        return String.format("Score: Worpen=%d, Correct=%d, Onjuist=%d, Hints=%d", 
            aantalWorpen, aantalCorrect, aantalOnjuist, aantalHints);
    }
}
