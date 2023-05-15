package game.arenas.naval;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer.*;
import game.arenas.Arena;

import java.util.Observable;
import java.util.Observer;

/**
 * NavalArena class extends Arena
 */
public class NavalArena extends Arena implements Observer {
    private final static double DEFAULT_FRICTION=0.7;
    private final static int DEFAULT_MAX_RACERS=5;
    private final static int DEFAULT_LENGTH=1000;
    private Water water;
    private WaterSurface surface;
    private Body body;

    /**
     * Default cons
     */
    public NavalArena(){
        super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS,DEFAULT_FRICTION);
        setWater(Water.SWEET);
        setSurface(WaterSurface.FLAT);
        setBody(Body.LAKE);
    }

    /**
     * Normal cons
     * @param length
     * @param maxRacers
     */
    public NavalArena(double length,int maxRacers){
        super(length,maxRacers,DEFAULT_FRICTION);
        setWater(Water.SWEET);
        setSurface(WaterSurface.FLAT);
        setBody(Body.LAKE);
    }

    /**
     * @param newRacer
     * @throws RacerLimitException
     * @throws RacerTypeException
     */
    public void addRacer(Racer newRacer) throws RacerLimitException,RacerTypeException {
        if(!(newRacer instanceof NavalRacer)){
            throw new RacerTypeException("");
        }
        super.addRacer(newRacer);
    }

    /**
     * @return Water
     */
    public Water getWater() {
        return water;
    }

    /**
     * @param water
     * @return boolean
     */
    public boolean setWater(Water water) {
        this.water = water;
        return true;
    }

    /**
     * @return WaterSurface
     */
    public WaterSurface getSurface() {
        return surface;
    }

    /**
     * @param surface
     * @return boolean
     */
    public boolean setSurface(WaterSurface surface) {
        this.surface = surface;
        return true;
    }

    /**
     * @return Body
     */
    public Body getBody() {
        return body;
    }

    /**
     * @param body
     * @return boolean
     */
    public boolean setBody(Body body) {
        this.body = body;
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {
        super.update(o,arg);
    }
}
