package game.racers.air;
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
 * Helicopter class extends Racer, AirRacer interface
 */
public class Helicopter extends Racer implements AirRacer{
    private static final String CLASS_NAME="Helicopter";
    private static final double DEFAULT_MAX_SPEED=400;
    private static final double DEFAULT_ACCELERATION=50;
    private static final COLOR DEFAULT_color= COLOR.BLUE;

    /**
     * Default cons
     */
    public Helicopter(){
        super(CLASS_NAME,DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        super.set_Name(CLASS_NAME+ " #" + super.getSerialNumber());
    }

    /**
     * Normal cons
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
    public Helicopter(String name, double maxSpeed, double acceleration, COLOR color){
        super(name, maxSpeed, acceleration, color);

    }
    /**
     * @return String
     */
    public String describeSpecific(){
        return "";
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
}
