package Strumienie_02_GIT;

/**
 * Created by Michal on 2017-02-17.
 */
public class Engine {

    private EngineType engineType;
    private double engineCapacity;
    private double fuelUsage;


    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(double fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    public Engine(EngineType engineType, double engineCapacity, double fuelUsage) {
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
        this.fuelUsage = fuelUsage;
    }

    public Engine() {

    }

    @Override
    public String toString() {
        return engineType + " " + engineCapacity + " " + fuelUsage;
    }
}
