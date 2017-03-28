package Strumienie_02_GIT;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

/**
 * Created by Michal on 2017-02-18.
 */
public class Main {

    //PROGRAM WYKONANY W RAMACH SZKOLENIA KM-PROGRAMS
    //http://km-programs.pl/

    private static Random rnd = new Random();

    public static Car generateCar() {


        String[] brandName = new String[]{"BMW", "Citroen", "Fiat", "Audi"};
        String name = brandName[rnd.nextInt(brandName.length)];
        int basicPrice = 5000 + rnd.nextInt(10000);
        String[] engineT = new String[]{"petrol", "diesel"};
        EngineType engineType = EngineType.valueOf(engineT[rnd.nextInt(engineT.length)]);
        double engineCapacity = 1 + rnd.nextInt(63);
        engineCapacity /= 10;
        double fuelUsage = 5 + rnd.nextInt(50);
        fuelUsage /= 10;

        String[] colorArr = new String[]{"black", "silver", "white", "red", "blue", "green"};
        Color color = Color.valueOf(colorArr[rnd.nextInt(colorArr.length)]);
        String[] bodyTytpeArr = new String[]{"sedan", "hatchback", "combi"};
        BodyType bodyType = BodyType.valueOf(bodyTytpeArr[rnd.nextInt(bodyTytpeArr.length)]);
        //losowanie ile elementow ma byc w liscie
        List<String> accessories = new ArrayList<>(Arrays.asList("LUSTERKA_ELEKTRYCZNE", "SZYBY_ELEKTRYCZNE", "DRZWI_TYLNE", "ALARM", "SWIATLA_PRZECIWMGIELNE", "KLIMATYZACJA", "AUDIO", "SKORA"));
        int elements = 1 + rnd.nextInt(accessories.size());
        List<Accessories> accessoriesList = new ArrayList<>();

        Accessories acc;

        for (int i = 0; i < elements; i++) {


            acc = Accessories.valueOf(accessories.get(rnd.nextInt(accessories.size())));
            if (!accessoriesList.contains(acc)) {
                accessoriesList.add(acc);
            }

        }


        String[] manufacturerArr = new String[]{"Debica", "Noname"};
        String manufacturer = manufacturerArr[rnd.nextInt(manufacturerArr.length)];
        int size = 13 + rnd.nextInt(5);
        String[] tireTypeArr = new String[]{"winter", "summer"};
        TiresType tiresType = TiresType.valueOf(tireTypeArr[rnd.nextInt(tireTypeArr.length)]);

        return new Car(name, basicPrice, new Engine(engineType, engineCapacity, fuelUsage), new Body(color, bodyType, accessoriesList), new Wheel(manufacturer, size, tiresType));//Arrays.asList(new String[]{rndName, String.valueOf(rndBasicPrice)});
    }


    public static void printFile(String fileName) {

        try {
            FileWriter fw = new FileWriter(fileName.toString(), false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(generateCar());


            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<String> generateFile(int n) {

        int idx = 1;

        List<String> list = new ArrayList<>();

        StringBuilder fileName;

        for (int i = 0; i < n; i++) {

            fileName = new StringBuilder("car");


            fileName.append(idx);

            printFile(fileName.toString() + ".txt");
            list.add(fileName.toString());

            idx++;

        }
        return list;
    }

    public static void main(String[] args) {

        List<CarShowroom> csList = new ArrayList<>();
        CarShowroom cs1 = null;
        CarShowroom cs2 = null;

        try {
            cs1 = new CarShowroom(generateFile(6));

            cs2 = new CarShowroom(generateFile(6));


            csList.add(cs1);
            csList.add(cs2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //CarShowroom.maxAveragePrice(csList,"CARS.txt");


     /*  System.out.println("sort by price: " + cs1.sortByPrice());
        System.out.println("sort by color: " + cs1.sortByColor());
        System.out.println("sort by body type: " + cs1.sortByBodyType());
        System.out.println("sort by brand: " + cs1.sortByBrand());
*/


        // System.out.println("filter by price and body type: " + cs1.filterByPriceAndBodyType(BodyType.hatchback, 12,1000000));

        //System.out.println("fuel type: " + cs1.fuelType(EngineType.petrol));


        // cs1.userCarsConfig();

        //cs1.showStatistics("price");

        /*cs1.mapProperties2().entrySet().stream().forEach(k->{

            System.out.println(k.getKey() + " => " + k.getValue());
        });*/

        /*cs1.tireTypeMap().entrySet().stream().forEach(e->{
            System.out.println(e.getKey() + " " + e.getValue());
        });*/

        CarShowroom.maxAveragePriceFromList(csList, "CARS.txt");


    }
}