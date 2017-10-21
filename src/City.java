import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by trsmith on 10/20/2017.
 */
public class City {

    private String name;
    private HashMap<Integer, Building> buildings;

    public City(String name, HashMap<Integer, Building> buildings)
    {
        this.name = name;
        this.buildings = buildings;
    }

    public Building getBuildingFromNumber(int buildingNumber)
    {
        return buildings.get(buildingNumber);
    }
}
