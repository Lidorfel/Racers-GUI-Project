package game.arenas.air;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.air.AirRacer;
import utilities.EnumContainer.*;
import game.arenas.Arena;
import game.racers.Racer;

import java.util.Observable;
import java.util.Observer;

/**
 * AerialArena Class,extends from Arena
 */
public class AerialArena extends Arena implements Observer {
    private final static double DEFAULT_FRICTION=0.4;
    private final static int DEFAULT_MAX_RACERS=6;
    private final static int DEFAULT_LENGTH=1500;
    private Vision vision;
    private Weather weather;
    private Height height;
    private Wind wind;

    /**
     * Default Cons
     */
    public AerialArena(){
        super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS,DEFAULT_FRICTION);
        setVision(Vision.SUNNY);
        setWeather(Weather.DRY);
        setHeight(Height.HIGH);
        setWind(Wind.HIGH);
    }

    /**
     * Normal Cons
     * @param length
     * @param maxRacers
     */
    public AerialArena(double length,int maxRacers){
        super(length,maxRacers,DEFAULT_FRICTION);
        setVision(Vision.SUNNY);
        setWeather(Weather.DRY);
        setHeight(Height.HIGH);
        setWind(Wind.HIGH);
    }

    /**
     * @param newRacer
     * @throws RacerLimitException
     * @throws RacerTypeException
     */
    public void addRacer(Racer newRacer) throws RacerLimitException,RacerTypeException {
        if(!(newRacer instanceof AirRacer)){
            throw new RacerTypeException("Invalid Racer of type \"" + newRacer.className() + "\" for Aerial arena.");
        }
        super.addRacer(newRacer);
    }

    /**
     * @return Vision
     */
    public Vision getVision() {
        return vision;
    }

    /**
     * @param vision
     * @return boolean
     */
    public boolean setVision(Vision vision) {
        this.vision = vision;
        return true;
    }

    /**
     * @return Weather
     */
    public Weather getWeather() {
        return weather;
    }

    /**
     * @param weather
     * @return boolean
     */
    public boolean setWeather(Weather weather) {
        this.weather = weather;
        return true;
    }

    /**
     * @return Height
     */
    public Height getHeight() {
        return height;
    }

    /**
     * @param height
     * @return boolean
     */
    public boolean setHeight(Height height) {
        this.height = height;
        return true;
    }

    /**
     * @return Wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * @param wind
     * @return boolean
     */
    public boolean setWind(Wind wind) {
        this.wind = wind;
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {
        super.update(o,arg);
    }
}
