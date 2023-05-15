package game.racers.land;
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
 * Bicycle class extends Racer, LandRacer interface
 */
public class Bicycle extends Racer implements LandRacer{
    private static final String CLASS_NAME="Bicycle";
    private static final double DEFAULT_MAX_SPEED=270;
    private static final int DEFAULT_WHEELS=2;
    private static final double DEFAULT_ACCELERATION=10;
    private static final COLOR DEFAULT_color= COLOR.GREEN;
    private Wheeled wheeled;
    private BicycleType type;

    /**
     * Default cons
     */
    public Bicycle(){
        super(CLASS_NAME, DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        super.set_Name(CLASS_NAME+ " #" + super.getSerialNumber());
        setType(BicycleType.MOUNTAIN);
        wheeled=new Wheeled(DEFAULT_WHEELS);
    }

    /**
     * Normal cons
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @param numOFWheels
     */
    public Bicycle(String name, double maxSpeed, double acceleration, COLOR color, int numOFWheels){
        super(name,maxSpeed,acceleration,color);
        wheeled= new Wheeled(numOFWheels);
        setType(BicycleType.MOUNTAIN);
    }

    /**
     * @return String
     */
    public String describeSpecific(){
        String temp =String.format(", Bicycle Type: %s",getType());
        temp= wheeled.describeSpecific()+temp;
        return temp;
    }

    /**
     * introduce
     */
    @Override
    public void introduce() {
        System.out.println("["+className()+"] "+ describeRacer());
    }

    /**
     * @return BicycleType
     */
    public BicycleType getType() {
        return type;
    }

    /**
     * @param type
     * @return boolean
     */
    public boolean setType(BicycleType type) {
        this.type = type;
        return true;
    }

    /**
     * @return String
     */
    public String className() {
        return CLASS_NAME;
    }

}
