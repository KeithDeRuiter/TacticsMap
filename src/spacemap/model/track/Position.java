/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacemap.model.track;

/**
 * Class for recording a 2D position represented with float precision.
 * @author Keith
 */
public class Position {
    /** The X position. */
    private final float x;
    
    /** The Y position. */
    private final float y;

    /**
     * Creates a new instance of {@code Position}.
     * @param x The X value to use.
     * @param y The Y value to use.
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X value of this position.
     * @return The X value of this position.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the Y value of this position.
     * @return The Y value of this position.
     */
    public float getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Float.floatToIntBits(this.x);
        hash = 59 * hash + Float.floatToIntBits(this.y);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return '(' + x + ", " + y + ')';
    }
    
}
