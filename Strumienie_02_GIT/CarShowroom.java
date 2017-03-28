package Strumienie_02_GIT;

import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Michal on 17.03.2017.
 */
public class CarShowroom {

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    private Set<Car> cars = new HashSet<>();

    public CarShowroom() {
    }

    public CarShowroom(List<String> filesList) throws IOException {


        for (String s : filesList) {

            File file = new File(s + ".txt");


            Scanner sc = new Scanner(file);

            String[] arr = sc.nextLine().split("[\\[\\]\\,\\s]");


            List<Accessories> listOfAccesories = new ArrayList<>();
            for (int i = 8; i < arr.length - 3; i++) {

                if (!arr[i].isEmpty() && !Accessories.values().equals(arr[i])) {


                    listOfAccesories.add(Accessories.valueOf(arr[i]));


                }

            }

            cars.add(new Car(arr[0], Double.parseDouble(arr[1]), new Engine(EngineType.valueOf(arr[2]), Double.parseDouble(arr[3]), Double.parseDouble(arr[4])),
                    new Body(Color.valueOf(arr[5]), BodyType.valueOf(arr[6]), listOfAccesories),
                    new Wheel(arr[arr.length - 3], Integer.parseInt(arr[arr.length - 2]), TiresType.valueOf(arr[arr.length - 1]))));


        }

        cars = new LinkedHashSet<>(cars.stream().sorted((c1, c2) -> c1.getBrand().compareTo(c2.getBrand())).collect(Collectors.toList()));

    }


    public List<Car> sortByPrice() {

        return cars.stream().sorted((c1, c2) -> Double.compare(c1.getBasicPrice(), c2.getBasicPrice())).collect(Collectors.toList());


    }

    public List<Car> sortByBrand() {

        return cars.stream().sorted((c1, c2) -> c1.getBrand().compareTo(c2.getBrand())).collect(Collectors.toList());


    }

    public List<Car> sortByColor() {

        return cars.stream().sorted((c1, c2) -> String.valueOf(c1.getBody().getColor()).compareTo(String.valueOf(c2.getBody().getColor()))).collect(Collectors.toList());


    }

    public List<Car> sortByBodyType() {

        return cars.stream().sorted((c1, c2) -> String.valueOf(c1.getBody().getBodyType()).compareTo(String.valueOf(c2.getBody().getBodyType()))).collect(Collectors.toList());


    }

    public Set<Car> filterByPriceAndBodyType(BodyType type, double a, double b) {
        return cars.stream().filter(c -> type.equals(c.getBody())/* String.valueOf(c.getBody().getBodyType()).equalsIgnoreCase(String.valueOf(type))*/
                && (c.getBasicPrice() >= a && c.getBasicPrice() <= b)).collect(Collectors.toSet());

    }

    public Set<Car> fuelType(EngineType type) {


        return cars.stream().filter(c -> type.equals(c.getEngine().getEngineType())/*String.valueOf(c.getEngine().getEngineType()).equalsIgnoreCase(String.valueOf(type))*/).collect(Collectors.toSet());
    }

    public void userCarsConfig() {

        Scanner sc = new Scanner(System.in);


        List<Car> carsList = new ArrayList<>();


        cars.forEach(System.out::println);
        double priceMin, priceMax, capacityMin, capacityMax;

        String engineType = " ";
        List<Accessories> acc;
        String[] arr;
        String w;

        System.out.print("Insert min price: ");
        priceMin = Double.parseDouble(sc.nextLine());
        do {
            System.out.print("Insert max price: ");
            priceMax = Double.parseDouble(sc.nextLine());
        } while (priceMax <= priceMin);

        final double maxPrice = priceMax;


        List<EngineType> engineTypes = new ArrayList<>(Arrays.asList(EngineType.values()));
        do {
            System.out.print("Insert fuel type: " + Arrays.toString(EngineType.values()));
            engineType = sc.nextLine();
        } while (!engineTypes.contains(EngineType.valueOf(engineType)));
        EngineType type = EngineType.valueOf(engineType);

        System.out.print("Insert min capacity: ");
        capacityMin = Double.parseDouble(sc.nextLine());
        do {
            System.out.print("Insert max capacity: ");
            capacityMax = Double.parseDouble(sc.nextLine());
        } while (capacityMax <= capacityMin);

        final double maxCapacity = capacityMax;

        List<EngineType> engineTypeList = new ArrayList<>(Arrays.asList(EngineType.values()));

        do {
            System.out.print("Insert equipment elements: ");
            w = sc.nextLine();
            arr = w.split(",\\s");
        } while (!checkIfAllElementsEnum(arr));


        cars.stream().filter(c ->
                c.getBasicPrice() >= priceMin &&
                        c.getBasicPrice() <= maxPrice &&
                        String.valueOf(c.getEngine().getEngineType()).equalsIgnoreCase(String.valueOf(type)) &&
                        c.getEngine().getEngineCapacity() >= capacityMin &&
                        c.getEngine().getEngineCapacity() <= maxCapacity &&
                        CollectionUtils.isEqualCollection(c.getBody().getAccessoriesList(), engineTypeList)).forEach(System.out::println);


    }

    public void showStatistics(String value) {

        if (value.equalsIgnoreCase("capacity")) {

            DoubleSummaryStatistics engineCapacityStatistics =
                    cars.stream().collect(Collectors.summarizingDouble(c -> c.getEngine().getEngineCapacity()));
            System.out.println("Min capacity value: " + engineCapacityStatistics.getMin());
            System.out.println("Average capacity value: " + engineCapacityStatistics.getAverage());
            System.out.println("Max capacity value: " + engineCapacityStatistics.getMax());

        } else if (value.equalsIgnoreCase("price")) {


            DoubleSummaryStatistics priceSummaryStatistics =
                    cars.stream().collect(Collectors.summarizingDouble(c -> c.getBasicPrice()));
            System.out.println("Min price value: " + priceSummaryStatistics.getMin());
            System.out.println("Average price value: " + priceSummaryStatistics.getAverage());
            System.out.println("Max price value: " + priceSummaryStatistics.getMax());

        } else if (value.equalsIgnoreCase("wheel size")) {

            DoubleSummaryStatistics wheelSizeSummaryStatistics =
                    cars.stream().collect(Collectors.summarizingDouble(c -> c.getWheel().getSize()));

            System.out.println("Min wheel size value: " + wheelSizeSummaryStatistics.getMin());
            System.out.println("Average wheel size value: " + wheelSizeSummaryStatistics.getAverage());
            System.out.println("Max wheel size value: " + wheelSizeSummaryStatistics.getMax());

        } else {

            System.out.println("Data error !");
        }


    }

    private Map<Car, Double> mapProperties() {

        Map<Car, Double> map = new TreeMap<>((c1, c2) -> c1.getBrand().compareTo(c2.getBrand()));

        Properties prop = new Properties();
        String propFile = "cars.properties";

        try {
            InputStream inpStr = new FileInputStream(propFile);
            prop.load(inpStr);

        } catch (Exception e) {

            e.printStackTrace();
        }

        cars.stream().forEach(c -> {

            map.put(c, Double.parseDouble(prop.getProperty(c.getBrand())));
        });


        return map;
    }

    public Map<Car, Double> mapProperties2() {

        return mapProperties().entrySet().stream().sorted((c1, c2) -> Double.compare(c2.getValue(), c1.getValue())).collect(Collectors.toMap(c -> c.getKey(), c -> c.getValue()));

    }

    public Map<TiresType, List<Car>> tireTypeMap() {

        Map<TiresType, List<Car>> map = new HashMap<>();

        cars.stream().forEach(e -> {

            if (e.getWheel().getTiresType().equals(TiresType.summer)) {


                if (map.containsKey(TiresType.summer)) {
                    map.get(TiresType.summer).add(e);
                } else {

                    map.put(TiresType.summer, new ArrayList<>(Arrays.asList(e)));

                }

            } else if (e.getWheel().getTiresType().equals(TiresType.winter)) {


                if (map.containsKey(TiresType.winter)) {
                    map.get(TiresType.winter).add(e);
                } else {

                    map.put(TiresType.winter, new ArrayList<>(Arrays.asList(e)));

                }

            }

        });


        return map;
    }


    public double averagePrice() {
        DoubleSummaryStatistics summary = cars.stream().collect(Collectors.summarizingDouble(c -> c.getBasicPrice()));

        return summary.getAverage();
    }

    public static void maxAveragePriceFromList(List<CarShowroom> list, String filename) {
        Optional<CarShowroom> o = list.stream().max((c1, c2) -> Double.compare(c2.averagePrice(), c1.averagePrice()));
        if (o.isPresent()) {
            try {
                FileWriter fw = new FileWriter(filename, false);
                PrintWriter pw = new PrintWriter(fw);
                o.get().getCars().forEach(c -> {

                    pw.println(c.getBrand() + " " + c.getBasicPrice());

                });

                pw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static boolean checkIfEnum(String acc) {

        try {
            return Arrays.stream(EngineType.values()).anyMatch(e -> e.equals(EngineType.valueOf(acc)));
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    public static boolean checkIfAllElementsEnum(String[] arr) {
        for (String s : arr) {
            if (!checkIfEnum(s)) {
                return false;
            }
        }
        return true;
    }

}
