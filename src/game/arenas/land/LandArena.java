package game.arenas.land;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.LandRacer;
import utilities.EnumContainer.*;

import java.util.Observable;
import java.util.Observer;

/**
 * LandArena Class extends Arena
 */
public class LandArena extends Arena implements Observer {
    private final static double DEFAULT_FRICTION=0.5;
    private final static int DEFAULT_MAX_RACERS=8;
    private final static int DEFAULT_LENGTH=800;
    private Coverage coverage;
    private LandSurface surface;

    /**
     * Default cons
     */
    public LandArena(){
        super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS,DEFAULT_FRICTION);
        setCoverage(Coverage.GRASS);
        setSurface(LandSurface.FLAT);
    }

    /**
     * Normal cons
     * @param length
     * @param maxRacers
     */
    public LandArena(double length,int maxRacers){
        super(length,maxRacers,DEFAULT_FRICTION);
        setCoverage(Coverage.GRASS);
        setSurface(LandSurface.FLAT);
    }

    /**
     * @param newRacer
     * @throws RacerLimitException
     * @throws RacerTypeException
     */
    public void addRacer(Racer newRacer) throws RacerLimitException,RacerTypeException {
        if(!(newRacer instanceof LandRacer)){
            throw new RacerTypeException("Invalid Racer of type \"" + newRacer.className() + "\" for Land arena.");
        }
        super.addRacer(newRacer);
    }

    /**
     * @return Coverage
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * @param coverage
     * @return boolean
     */
    public boolean setCoverage(Coverage coverage) {
        this.coverage = coverage;
        return true;
    }

    /**
     * @return LandSurface
     */
    public LandSurface getSurface() {
        return surface;
    }

    /**
     * @param surface
     * @return boolean
     */
    public boolean setSurface(LandSurface surface) {
        this.surface = surface;
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {
        super.update(o,arg);
    }
}
