package utilities;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */

/**
 * Point class
 */
public class Point {
    private static final int MAX_X=1000000;
    private static final int MIN_X=0;
    private static final int MAX_Y=800;
    private static final int MIN_Y=0;
    private double x,y;

    /**
     * Default cons
     */
    public Point(){
        setX(0);
        setY(0);
    }

    /**
     * Normal cons
     * @param xCord
     * @param yCord
     */
    public Point(double xCord,double yCord){
        this.x=xCord;
        this.y=yCord;
    }

    /**
     * Copy cons
     * @param other
     */
    public Point(Point other){
        this(other.x,other.y);
    }

    /**
     * @return double
     */
    public double getX() {
        return x;
    }

    /**
     * @return double
     */
    public double getY() {
        return y;
    }

    /**
     * @param x
     * @return boolean
     */
    public boolean setX(double x) {
        boolean res=false;
        if (x>=0&& x<=MAX_X) {
            this.x = x;
            res=true;
        }
        else this.x=0;
        return res;
    }

    /**
     * @param y
     * @return boolean
     */
    public boolean setY(double y) {
        boolean res=false;
        if (y>=0&& y<=MAX_Y) {
            this.y = y;
            res=true;
        }
        else this.y=0;
        return res;
    }

    /**
     * @return String
     */
    public String toString() {
        return String.format("(%d,%d)",x,y);
    }
}
