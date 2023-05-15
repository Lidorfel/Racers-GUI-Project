package game.racers;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */


/**
 * Wheeled class
 */
public class Wheeled{
    private int numOfWheels;

    /**
     * Normal cons
     * @param numOfWheels
     */
    public Wheeled(int numOfWheels){
        if(!setNumOfWheels(numOfWheels)){
            this.numOfWheels=4;
        }
    }

    /**
     * Default cons
     */
    public Wheeled(){
        this.numOfWheels=4;
    }

    /**
     * @return String
     */
    public String describeSpecific() {
        return ", Number of Wheels: " + getNumOfWheels();
    }

    /**
     * @return int
     */
    public int getNumOfWheels() {
        return numOfWheels;
    }

    /**
     * @param numOfWheels
     * @return boolean
     */
    public boolean setNumOfWheels(int numOfWheels) {
        boolean res=false;
        if (numOfWheels>0){
            this.numOfWheels=numOfWheels;
            res=true;
        }
        return res;
    }
}
