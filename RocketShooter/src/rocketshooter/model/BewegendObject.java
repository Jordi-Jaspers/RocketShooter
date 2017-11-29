
package rocketshooter.model;

/**
 *
 * @author jordi
 */
public interface BewegendObject {
    
    /**
     * laat het object bewegen.
     */
    public void start();
    
    /**
     * laat het object stoppen met bewegen.
     */
    public void stop();
    
    /**
     * is het object aan het bewegen?
     * 
     * @return true als de kogel beweegt
     */
    public boolean beweegt();
}
