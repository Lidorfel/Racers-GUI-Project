package utilities;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */


/**
 * Mishap class
 */
public class Mishap {
    private boolean fixable;
    private double reductionFactor;
    private int turnsToFix;

    /**
     * Normal cons
     * @param fixable
     * @param turnsToFix
     * @param reductionFactor
     */
    public Mishap(boolean fixable,int turnsToFix,double reductionFactor){
        setFixable(fixable);
        setReductionFactor(reductionFactor);
        setTurnsToFix(turnsToFix);
    }

    /**
     * nextTurn
     */
    public void nextTurn(){
        if (fixable && turnsToFix>0){
            turnsToFix--;
        }
    }

    /**
     * @return boolean
     */
    public boolean isFixable() {//getFixable
        return fixable;
    }

    /**
     * @param fixable
     */
    public boolean setFixable(boolean fixable) {
        this.fixable = fixable;
        return true;
    }

    /**
     * @return double
     */
    public double getReductionFactor() {
        return reductionFactor;
    }

    /**
     * @param reductionFactor
     */
    public boolean setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
        return true;
    }

    /**
     * @return int
     */
    public int getTurnsToFix() {
        return turnsToFix;
    }

    /**
     * @param turnsToFix
     */
    public boolean setTurnsToFix(int turnsToFix) {
        this.turnsToFix = turnsToFix;
        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return String.format("(%b, %d ,%.2f)",fixable,turnsToFix,reductionFactor);
    }
}
