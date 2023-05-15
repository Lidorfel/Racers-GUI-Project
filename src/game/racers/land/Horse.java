package game.racers.land;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import game.racers.Racer;
import utilities.EnumContainer.*;

import java.awt.*;

/**
 * Horse class extends Racer, LandRacer interface
 */
public class Horse extends Racer implements LandRacer{
    private static final String CLASS_NAME="Horse";
    private static final double DEFAULT_MAX_SPEED=50;
    private static final double DEFAULT_ACCELERATION=3;
    private static final COLOR DEFAULT_color= COLOR.BLACK;
    private Breed breed;

    /**
     * Default cons
     */
    public Horse(){
        super(CLASS_NAME, DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        super.set_Name(CLASS_NAME+ " #" + super.getSerialNumber());
        setBreed(Breed.THOROUGHBRED);
    }

    /**
     * Normal cons
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
    public Horse(String name, double maxSpeed, double acceleration, COLOR color){
        super(name, maxSpeed,acceleration,color);
        setBreed(Breed.THOROUGHBRED);
    }

    /**
     * @return String
     */
    public String describeSpecific(){
        return String.format(", Breed: %s",getBreed());
    }

    /**
     * introduce
     */
    @Override
    public void introduce() {
        System.out.println("["+className()+"] "+ describeRacer());
    }

    /**
     * @return String
     */
    public String className() {
        return CLASS_NAME;
    }

    /**
     * @return Breed
     */
    public Breed getBreed() {
        return breed;
    }

    /**
     * @param breed
     * @return boolean
     */
    public boolean setBreed(Breed breed) {
        this.breed = breed;
        return true;
    }

}
