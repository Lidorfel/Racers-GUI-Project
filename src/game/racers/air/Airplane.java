package game.racers.air;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.*;

import java.awt.*;

/**
 * Airplane class extends Racer, AirRacer interface
 */
public class Airplane extends Racer implements AirRacer{
    private static final String CLASS_NAME="Airplane";
    private static final double DEFAULT_MAX_SPEED=885;
    private static final int DEFAULT_WHEELS=3;
    private static final double DEFAULT_ACCELERATION=100;
    private static final COLOR DEFAULT_color= COLOR.BLACK;
    private Wheeled wheeled;

    /**
     * Default cons
     */
    public Airplane(){
        super(CLASS_NAME,DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        super.set_Name(CLASS_NAME+ " #" + super.getSerialNumber());
        wheeled=new Wheeled(DEFAULT_WHEELS);

    }

    /**
     * Normal cons
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @param numOfWheels
     */
    public Airplane(String name, double maxSpeed, double acceleration, COLOR color, int numOfWheels){
        super(name,maxSpeed,acceleration,color);
        wheeled=new Wheeled(numOfWheels);
    }

    /**
     * @return String
     */
    public String describeSpecific(){
        return wheeled.describeSpecific();
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
