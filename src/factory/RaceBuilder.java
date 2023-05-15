package factory;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import utilities.EnumContainer.*;
import game.racers.Racer;
import game.arenas.Arena;

import java.awt.*;
import java.lang.reflect.*;

/**
 * RaceBuilder Class,Singleton
 */
public class RaceBuilder {
    private static RaceBuilder Instance=null;
    private ClassLoader classLoader;
    private Class<?> classObject;
    private Constructor<?> constructor;

    /**
     * private default constructor
     */
    private RaceBuilder(){}
    /**
     * @param arenaType
     * @param length
     * @param maxRacers
     * @return Arena
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public Arena buildArena(String arenaType,double length,int maxRacers) throws ClassNotFoundException,NoSuchMethodException,SecurityException,InstantiationException,IllegalAccessException,IllegalArgumentException,InvocationTargetException{
        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(arenaType);
        constructor = classObject.getConstructor(double.class, int.class);
        Arena temp = (Arena) constructor.newInstance(length, maxRacers);
        return temp;

    }

    /**
     * @param racerType
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @return Racer
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration, COLOR color) throws ClassNotFoundException,NoSuchMethodException,SecurityException,InstantiationException,IllegalAccessException,IllegalArgumentException,InvocationTargetException{
        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(racerType);
        constructor = classObject.getConstructor(String.class, double.class, double.class, COLOR.class);
        Racer temp = (Racer) constructor.newInstance(name, maxSpeed, acceleration, color);
        return temp;
    }

    /**
     * @param racerType
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @param numOfWheels
     * @return Racer
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, COLOR color,int numOfWheels) throws ClassNotFoundException,NoSuchMethodException,SecurityException,InstantiationException,IllegalAccessException,IllegalArgumentException,InvocationTargetException{
        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(racerType);
        constructor = classObject.getConstructor(String.class, double.class, double.class, COLOR.class,int.class);
        Racer temp = (Racer) constructor.newInstance(name, maxSpeed, acceleration, color,numOfWheels);
        return temp;
    }

    /**
     *
     * @return RaceBuilder
     */
    public static RaceBuilder getInstance() {
        if (Instance == null){
            Instance = new RaceBuilder();
        }
        return Instance;
    }
}
