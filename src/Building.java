import java.util.HashMap;

/**
 * Created by trsmith on 10/20/2017.
 */
public class Building{

    private int number;
    private int lootSpawns;
    private double timeToLoot;
    private HashMap<Integer, Double> neighbors;

    private final static HashMap<String, Integer> lootSpawnsForBuildingType;
    static
    {
        lootSpawnsForBuildingType = new HashMap<String, Integer>();
        lootSpawnsForBuildingType.put("BD", 8);
        lootSpawnsForBuildingType.put("H37", 20);
        lootSpawnsForBuildingType.put("H36", 5);
        lootSpawnsForBuildingType.put("H35", 12);
        lootSpawnsForBuildingType.put("H34", 4);
        lootSpawnsForBuildingType.put("H33", 37);
        lootSpawnsForBuildingType.put("H32", 3);
        lootSpawnsForBuildingType.put("H31", 13);
        lootSpawnsForBuildingType.put("H30", 36);
        lootSpawnsForBuildingType.put("H29", 3);
        lootSpawnsForBuildingType.put("H28", 13);
        lootSpawnsForBuildingType.put("H27", 16);
        lootSpawnsForBuildingType.put("H26", 8);
        lootSpawnsForBuildingType.put("M3", 5);
        lootSpawnsForBuildingType.put("M2", 17);
        lootSpawnsForBuildingType.put("M1", 26);
        lootSpawnsForBuildingType.put("W2", 9);
        lootSpawnsForBuildingType.put("W", 7);
        lootSpawnsForBuildingType.put("H24", 3);
        lootSpawnsForBuildingType.put("H23", 2);
        lootSpawnsForBuildingType.put("H22", 3);
        lootSpawnsForBuildingType.put("H21", 6);
        lootSpawnsForBuildingType.put("H19", 7);
        lootSpawnsForBuildingType.put("H18", 9);
        lootSpawnsForBuildingType.put("H17", 3);
        lootSpawnsForBuildingType.put("H16", 12);
        lootSpawnsForBuildingType.put("H15", 3);
        lootSpawnsForBuildingType.put("H14", 20);
        //lootSpawnsForBuildingType.put("H13", 6);
        lootSpawnsForBuildingType.put("H12", 15);
        lootSpawnsForBuildingType.put("H20", 8);
        lootSpawnsForBuildingType.put("H11", 6);
        lootSpawnsForBuildingType.put("H10", 3);
        lootSpawnsForBuildingType.put("H9", 1);
        lootSpawnsForBuildingType.put("H8", 5);
        lootSpawnsForBuildingType.put("H7", 11);
        lootSpawnsForBuildingType.put("H6", 10);
        lootSpawnsForBuildingType.put("H5", 14);
        lootSpawnsForBuildingType.put("H4", 16);
        lootSpawnsForBuildingType.put("H3", 10);
        lootSpawnsForBuildingType.put("H2", 11);
        lootSpawnsForBuildingType.put("H1", 9);
        lootSpawnsForBuildingType.put("TT1", 38);
        lootSpawnsForBuildingType.put("TD", 17);
        lootSpawnsForBuildingType.put("AB", 39);
    }
    private final static HashMap<String, Double> timeToLootForBuildingType;
    static
    {
        timeToLootForBuildingType = new HashMap<String, Double>();
        timeToLootForBuildingType.put("BD", 11.22);
        timeToLootForBuildingType.put("H37", 51.23);
        timeToLootForBuildingType.put("H36", 9.4);
        timeToLootForBuildingType.put("H35", 24.49);
        timeToLootForBuildingType.put("H34", 4.89);
        timeToLootForBuildingType.put("H33", 80.0);
        timeToLootForBuildingType.put("H32", 3.3);
        timeToLootForBuildingType.put("H31", 30.53);
        timeToLootForBuildingType.put("H30", 69.0);
        timeToLootForBuildingType.put("H29", 3.9);
        timeToLootForBuildingType.put("H28", 21.55);
        timeToLootForBuildingType.put("H27", 25.58);
        timeToLootForBuildingType.put("H26", 8.1);
        timeToLootForBuildingType.put("M3", 6.87);
        timeToLootForBuildingType.put("M2", 32.33);
        timeToLootForBuildingType.put("M1", 42.14);
        timeToLootForBuildingType.put("W2", 11.53);
        timeToLootForBuildingType.put("W", 9.75);
        timeToLootForBuildingType.put("H24", 4.99);
        timeToLootForBuildingType.put("H23", 6.68);
        timeToLootForBuildingType.put("H22", 6.68);
        timeToLootForBuildingType.put("H21", 18.5);
        timeToLootForBuildingType.put("H19", 16.13);
        timeToLootForBuildingType.put("H18", 17.8);
        timeToLootForBuildingType.put("H17", 4.3);
        timeToLootForBuildingType.put("H16", 31.87);
        timeToLootForBuildingType.put("H15", 11.5);
        timeToLootForBuildingType.put("H14", 25.24);
        //timeToLootForBuildingType.put("H13", 10.82);
        timeToLootForBuildingType.put("H12", 42.75);
        timeToLootForBuildingType.put("H20", 16.88);
        timeToLootForBuildingType.put("H11", 15.19);
        timeToLootForBuildingType.put("H10", 6.63);
        timeToLootForBuildingType.put("H9", 1.0);
        timeToLootForBuildingType.put("H8", 11.98);
        timeToLootForBuildingType.put("H7", 23.25);
        timeToLootForBuildingType.put("H6", 23.72);
        timeToLootForBuildingType.put("H5", 27.0);
        timeToLootForBuildingType.put("H4", 24.53);
        timeToLootForBuildingType.put("H3", 15.39);
        timeToLootForBuildingType.put("H2", 27.66);
        timeToLootForBuildingType.put("H1", 20.6);
        timeToLootForBuildingType.put("TT1", 86.63);
        timeToLootForBuildingType.put("TD", 23.49);
        timeToLootForBuildingType.put("AB", 70.77);
    }

    public Building(int number, String type, HashMap<Integer, Double> neighbors)
    {
        this.number = number;
        lootSpawns = lootSpawnsForBuildingType.get(type);
        timeToLoot = timeToLootForBuildingType.get(type);
        this.neighbors = neighbors;
    }

    public int getLootSpawns() {
        return lootSpawns;
    }

    public double getTimeToLoot() {
        return timeToLoot;
    }

    public HashMap<Integer, Double> getNeighbors() {
        return neighbors;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        String result = number + " " + lootSpawns + " " + timeToLoot + "\n";
        for(Integer number : neighbors.keySet())
        {
            result += number + " " + neighbors.get(number) + "\n";
        }
        return result;
    }
}
