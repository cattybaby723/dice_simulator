package angela_hu.dicesimulator.model;

/**
 * @author anrou_hu
 */

public class Dice {

    private int maxPoint = 0;
    private int currentPoint = 0;

    public Dice(int currentPoint, int maxPoint){
        this.currentPoint = currentPoint;
        this.maxPoint = maxPoint;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }


}
