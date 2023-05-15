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
 * Car class extends Racer, LandRacer interface
 */
public class Car extends Racer implements LandRacer{
    private static final String CLASS_NAME="Car";
    private static final int DEFAULT_WHEELS=4;
    private static final double DEFAULT_MAX_SPEED=400;
    private static final double DEFAULT_ACCELERATION=20;
    private static final COLOR DEFAULT_color= COLOR.RED;
    private Wheeled wheeled;
    private Engine engine;

    /**
     * Default cons
     */
    public Car(){
        super(CLASS_NAME, DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
        super.set_Name(CLASS_NAME+ " #" + super.getSerialNumber());
        setEngine(Engine.FOURSTROKE);
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
    public Car(String name, double maxSpeed, double acceleration, COLOR color, int numOFWheels){
        super(name,maxSpeed,acceleration,color);
        wheeled=new Wheeled(numOFWheels);
        setEngine(Engine.FOURSTROKE);
    }

    /**
     * @return String
     */
    public String describeSpecific(){
        String temp =String.format(", Engine Type: %s",getEngine());
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
     * @return Engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * @param engine
     * @return boolean
     */
    public boolean setEngine(Engine engine) {
        this.engine = engine;
        return true;
    }

    /**
     * @return String
     */
    public String className() {
        return CLASS_NAME;
    }

}
