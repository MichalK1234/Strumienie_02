package Strumienie_02_GIT;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Michal on 2017-02-17.
 */
public class Car {

    private String brand;
    private double basicPrice;
    private Engine engine;
    private Body body;
    private Wheel wheel;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(double basicPrice) {
        this.basicPrice = basicPrice;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Car(String fileName) {

        try {
            FileReader file = new FileReader(fileName + ".txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {


            }
        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public Car(String brand, double basicPrice, Engine engine, Body body, Wheel wheel) {
        this.brand = brand;
        this.basicPrice = basicPrice;
        this.engine = engine;
        this.body = body;
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return brand + " " + basicPrice + " " + engine + " " + body + " " + wheel;
    }

    public static Map<Integer, List<String>> readFile(String fileName) {

        Map<Integer, List<String>> map = new HashMap<>();
        int idx = -1;
        try {

            Scanner sc = new Scanner(new File(fileName));
            String[] tab = new String[]{};
            while (sc.hasNext()) {

                tab = sc.nextLine().split(" ");

                map.put(++idx, Arrays.asList(tab));
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return map;
    }
}
