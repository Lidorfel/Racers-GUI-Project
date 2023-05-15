package game.racers;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import game.arenas.Arena;
import utilities.*;
import utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;


/**
 * Racer abstract class
 */
public abstract class Racer extends Observable implements Runnable {
    private static int index =1;
    private int serialNumber;
    private String name;
    private Point currentLocation;
    private Point finish;
    private Arena arena;
    private double maxSpeed;
    private double acceleration;
    private double currentSpeed;
    private double failureProbability;
    private EnumContainer.COLOR color;
    private Mishap mishap;
    private JPanel racerPanel;

    /**
     * Normal cons
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
    public Racer(String name, double maxSpeed, double acceleration, EnumContainer.COLOR color){
        setSerialNumber(index++);
        set_Name(name);
        setMaxSpeed(maxSpeed);
        setAcceleration(acceleration);
        setColor(color);


    }


    /**
     * @param cp connecting JPanel to the racer
     */
    public boolean setRacerPanel(JPanel cp){
        this.racerPanel=cp;
        return true;
    }

    /**
     * @return JPane
     */
    public JPanel getRacerPanel(){return this.racerPanel;}
    /**
     * @param arena
     * @param start
     * @param finish
     */
    public void initRace(Arena arena,Point start ,Point finish){
        setArena(arena);
        setCurrentLocation(start);
        setFinish(finish);
        racerPanel.setBounds(new Rectangle((int)getCurrentLocation().getX(),(int)getCurrentLocation().getY(),40,40));
    }

    /**
     * @param friction
     * @return Point
     */
    public Point move(double friction){
        if(hasMishap()&&mishap.isFixable()&&mishap.getTurnsToFix()==0)
            setMishap(null);
        if(!hasMishap()){
            if(Fate.breakDown()){
                mishap=Fate.generateMishap();
                System.out.println(name +" has a new mishap!  " + mishap.toString());
            }
        }
        if(hasMishap()){
            currentSpeed+=acceleration*friction*mishap.getReductionFactor();
            mishap.nextTurn();

        }else
            currentSpeed+=acceleration*friction;
        currentSpeed=Math.min(currentSpeed,maxSpeed);
        currentLocation.setX(Math.min(currentLocation.getX()+currentSpeed,arena.getLength()));
        return currentLocation;
    }

    /**
     * abstract method
     * @return String
     */
    public abstract String describeSpecific();

    /**
     * @return String
     */
    public String describeRacer(){
        String temp = String.format("name: %s, SerialNumber: %d, maxSpeed: %.1f, acceleration: %.1f, color: %s",getName(),getSerialNumber(),getMaxSpeed(),getAcceleration(),getColor());
        return temp + describeSpecific();
    }

    /**
     * abstract method
     * introduceq
     */
    public abstract void introduce();

    /**
     * @return String
     */
    public String className(){
        return "Racer";
    }

    /**
     * @return boolean
     */
    public boolean hasMishap(){
        return mishap!=null;
    }

    //GETTER AND SETTERS


    /**
     * @return int
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber
     * @return boolean
     */
    public boolean setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
        return true;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     * @return boolean
     */
    public boolean set_Name(String name) {
        this.name = name;
        return true;
    }

    /**
     * @return Point
     */
    public Point getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation
     * @return boolean
     */
    public boolean setCurrentLocation(Point currentLocation) {
        this.currentLocation = new Point(currentLocation);
        return true;
    }

    /**
     * @return Point
     */
    public Point getFinish() {
        return finish;
    }

    /**
     * @param finish
     * @return boolean
     */
    public boolean setFinish(Point finish) {
        this.finish = new Point(finish);
        return true;
    }

    /**
     * @return Arena
     */
    public Arena getArena() {
        return arena;
    }

    /**
     * @param arena
     * @return boolean
     */
    public boolean setArena(Arena arena) {
        this.arena = arena;
        return true;
    }

    /**
     * @return double
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed
     * @return boolean
     */
    public boolean setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
        return true;
    }

    /**
     * @return double
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration
     * @return boolean
     */
    public boolean setAcceleration(double acceleration) {
        this.acceleration = acceleration;
        return true;
    }

    /**
     * @return double
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * @param currentSpeed
     * @return boolean
     */
    public boolean setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
        return true;
    }

    /**
     * @return double
     */
    public double getFailureProbability() {
        return failureProbability;
    }

    /**
     * @param failureProbability
     * @return boolean
     */
    public boolean setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
        return true;
    }

    /**
     * @return Color
     */
    public EnumContainer.COLOR getColor() {
        return color;
    }

    /**
     * @param color
     * @return boolean
     */
    public boolean setColor(EnumContainer.COLOR color) {
        this.color = color;
        return true;
    }

    /**
     * @return Mishap
     */
    public Mishap getMishap() {
        return mishap;
    }

    /**
     * @param mishap
     * @return boolean
     */
    public boolean setMishap(Mishap mishap) {
        this.mishap = mishap;
        return true;
    }

    /**
     * Run method of a Runnable racer for thread
     */
    public void run(){
        while (currentLocation.getX() < arena.getLength()) {
            try {
                Thread.sleep(100);
                Point temp = move(arena.getFRICTION());
                getRacerPanel().setBounds(new Rectangle((int) temp.getX(), (int) temp.getY(), 40, 40));
                getRacerPanel().revalidate();
                getRacerPanel().repaint();
            } catch (InterruptedException e) {
                System.out.println("Problem in sleep " + this.getName());
            }
            setChanged();
            notifyObservers();
        }
        synchronized (arena) {
            arena.crossFinishLine(this);
            setChanged();
            notifyObservers();
        }
    }

    /**
     * @return if the racer finished the race, for the show info
     */
    public boolean isFinished(){
        return currentLocation.getX() >= arena.getLength();
    }

}
