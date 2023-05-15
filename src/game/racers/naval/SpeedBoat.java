package game.racers.naval;
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
 * SpeedBoat class extends Racer, NavalRacer interface
 */
public class SpeedBoat extends Racer implements NavalRacer{
    private static final String CLASS_NAME="SpeedBoat";
    private static final double DEFAULT_MAX_SPEED=170;
    private static final double DEFAULT_ACCELERATION=5;
    private static final COLOR DEFAULT_color=COLOR.RED;
    private BoatType type;
    private Team team;

    /**
     * Default cons
     */
    public SpeedBoat(){
        super(CLASS_NAME, DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION, DEFAULT_color);
        setTeam(Team.DOUBLE);
        setType(BoatType.SKULLING);
        super.set_Name(CLASS_NAME+ " #" + super.getSerialNumber());
    }

    /**
     * Normal cons
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
    public SpeedBoat(String name, double maxSpeed, double acceleration, COLOR color){
        super(name,maxSpeed,acceleration,color);
        setTeam(Team.DOUBLE);
        setType(BoatType.SKULLING);
    }

    /**
     * @return BoatType
     */
    public BoatType getType() {
        return type;
    }

    /**
     * @param type
     * @return boolean
     */
    public boolean setType(BoatType type) {
        this.type = type;
        return true;
    }

    /**
     * @return Team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team
     * @return boolean
     */
    public boolean setTeam(Team team) {
        this.team = team;
        return true;
    }

    /**
     * @return String
     */
    public String describeSpecific(){
        return String.format(", Type: %s, Team: %s",getType(),getTeam());
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
