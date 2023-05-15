package game.arenas;

/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import GUI.ArenaPanel;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.Point;
import GUI.resultTable;
import javax.swing.*;
import java.awt.*;
import java.util.*;


/**
 * Arena abstract class
 */
public abstract class Arena implements Observer {
    private ArrayList<Racer> activeRacers;
    private ArrayList<Racer> completedRacers;
    private final double FRICTION;
    private final int MAX_RACERS;
    private  int MIN_Y_GAP;
    private double length;
    private resultTable DataTable;
    private ArenaPanel ap;
    private boolean raceInProgress=false;


    /**
     * Normal cons
     * @param length
     * @param maxRacers
     * @param friction
     */
    public Arena(double length,int maxRacers,double friction){
        this.length=length;
        MAX_RACERS=maxRacers;
        FRICTION=friction;
        activeRacers =new ArrayList<>();
        completedRacers=new ArrayList<>();
    }
    /**
     * abstract method
     * @param newRacer
     * @throws RacerLimitException
     * @throws RacerTypeException
     */
    public void addRacer(Racer newRacer) throws RacerLimitException,RacerTypeException{
        if(!hasSpace()){
            throw new RacerLimitException("");
        }
        getActiveRacers().add(newRacer);
        newRacer.addObserver(this);
    }


    /**
     * initRace
     */
    public void initRace(){
        for(int i=0,gap=MIN_Y_GAP;i<activeRacers.size();i++,gap+=MIN_Y_GAP){
            Point start=new Point(0,gap);
            Point end=new Point(length,gap);
            Racer temp=activeRacers.get(i);
            activeRacers.get(i).initRace(this,start,end);
        }

    }

    /**
     * function that start the race by starting all the racer threads
     */
    public void startRace() {
        raceInProgress=true;
        System.out.println("Introduction: ");
        for (Racer racer : getActiveRacers())
            racer.introduce();
        System.out.println("Start Race!");
        for(Racer r:activeRacers){
            (new Thread(r)).start();
        }
    }
    public boolean isRaceInProgress(){
        return raceInProgress;
    }
    /**
     * @return boolean
     */
    public boolean hasActiveRacers(){
        return activeRacers.size()>0;
    }

    /**
     * playTurn
     */
    public void playTurn(){
        for (int i=0;i<activeRacers.size();){
            Point p = activeRacers.get(i).move(FRICTION);
            Point temp=activeRacers.get(i).getCurrentLocation();
            if (temp.getX()>=length){
                crossFinishLine(activeRacers.get(i));
            }
            else{
                activeRacers.get(i).getRacerPanel().setBounds(new Rectangle((int)p.getX(),(int)p.getY(),40,40));
                activeRacers.get(i).getRacerPanel().repaint();
                i++;
            }

        }
    }

    /**
     * @param racer
     */
    public synchronized void crossFinishLine(Racer racer){
        racer.getRacerPanel().setBounds(new Rectangle((int)racer.getFinish().getX(),(int)racer.getFinish().getY(),40,40));
        racer.getRacerPanel().revalidate();
        racer.getRacerPanel().repaint();
        completedRacers.add(racer);
        activeRacers.remove(racer);
    }

    /**
     * showResult, using polymorphism
     */
    public void showResults(){
        for (int i=0;i<completedRacers.size();i++){
            System.out.println("#"+ i + " -> " + ((Racer)completedRacers.get(i)).describeRacer());
        }
    }

    /**
     * @return boolean
     */
    public boolean hasSpace(){
        return activeRacers.size()<MAX_RACERS;
    }

    /**
     * @return int
     */
    public int getNumPlayers(){
        return activeRacers.size();
    }

    /**
     * @return ArrayList<Racer>
     */
    public ArrayList<Racer> getActiveRacers() {
        return activeRacers;
    }

    /**
     * @return ArrayList<Racer>
     */
    public ArrayList<Racer> getCompletedRacers() {
        return completedRacers;
    }

    /**
     * @return friction
     */
    public double getFRICTION(){
        return this.FRICTION;
    }

    /**
     * @param gap for adjusting gaps between racers according to max racer in arena
     */
    public boolean setMinYGap(int gap){
        this.MIN_Y_GAP=gap;
        return true;
    }

    /**
     * @return length
     */
    public double getLength(){
        return this.length;
    }

    /**
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     *            method.
     */
    public void update(Observable o, Object arg) {
        if (activeRacers.isEmpty()){
            JOptionPane.showMessageDialog( null, "Race Completed");
        }
        else {
            try {
                synchronized (DataTable) {
                    DataTable.resetTable();
                    ArrayList<Racer> temp = new ArrayList<>(completedRacers);
                    temp.addAll(activeRacers);
                    for (Racer r : temp) {
                        DataTable.addTask(r);
                    }
                    ap.revalidate();
                    ap.repaint();
                    try {
                        Thread.sleep(30);
                    }
                    catch (InterruptedException e){

                    }
                }
            }
            catch (Exception e){
                //ignore
            }
        }

    }

    /**
     * @param dt for the showinfo
     */
    public boolean setTable(resultTable dt){
        this.DataTable=dt;
        return true;
    }

    /**
     * @param ap for the arena panel
     */
    public boolean setArenaPanel(ArenaPanel ap){
        this.ap=ap;
        return true;
    }
}
