import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by trsmith on 10/20/2017.
 */
public class Main {

    public static int landingSpotNum;

    public static void main(String args[]) {
        System.out.println("Select a building to land at: ");
        Scanner userInput = new Scanner(System.in);
        landingSpotNum = userInput.nextInt();
        userInput.close();

        File file = new File("D:\\Code\\pubglootpath\\myltaTest.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HashMap<Integer, Building> buildings = new HashMap<>();
        while(scanner.hasNextLine())
        {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            int buildingNum = lineScanner.nextInt();
            String buildingType = lineScanner.next();
            HashMap<Integer, Double> neighbors = new HashMap<>();
            while (lineScanner.hasNextInt())
            {
                int num = -1;
                try {
                    num = lineScanner.nextInt();
                    neighbors.put(num, new Double(lineScanner.nextInt()));
                }
                catch (NoSuchElementException e)
                {
                    System.out.println("error: " + num + " building: " + buildingNum);
                }
            }
            buildings.put(buildingNum, new Building(buildingNum, buildingType, neighbors));
            lineScanner.close();
        }
        scanner.close();

//        for (Integer num : buildings.keySet())
//        {
//            System.out.println(buildings.get(num));
//        }

        City mylta = new City("Mylta", buildings);

        Building landingSpot = mylta.getBuildingFromNumber(landingSpotNum);
        ArrayList<Integer> rootPathList = new ArrayList<>();
        rootPathList.add(landingSpot.getNumber());
        //Algorithms.breadthFirstVisit(mylta.getBuildingFromNumber(landingSpotNum), new Path(rootPathList, landingSpot.getTimeToLoot(), landingSpot.getLootSpawns()), mylta);
        //Algorithms.bestFirstVisit(mylta.getBuildingFromNumber(landingSpotNum), new Path(rootPathList, landingSpot.getTimeToLoot(), landingSpot.getLootSpawns()), mylta);
        Algorithms.bestFirstTest(mylta.getBuildingFromNumber(landingSpotNum), new Path(rootPathList, landingSpot.getTimeToLoot(), landingSpot.getLootSpawns()), mylta);
        Path bestPath = Algorithms.getBestPath();
        System.out.println("Solution:");
        for(int num : bestPath.getPath())
        {
            System.out.println(num);
        }
        //Algorithms.bestFirstVisit(mylta.getBuildingFromNumber(4), new Path(rootPathList, landingSpot.getTimeToLoot(), landingSpot.getLootSpawns()), mylta);

//        PriorityQueue<Path> pathEfficiencyQueue = new PriorityQueue<>(Collections.reverseOrder());
//        ArrayList<Integer> MEList = new ArrayList<>();
//        Path pathME = new Path(MEList, 1, 5);
//        Path pathLE = new Path(MEList, 5, 1);
//        pathEfficiencyQueue.add(pathME);
//        pathEfficiencyQueue.add(pathLE);
//        System.out.println("Blarg: " + pathEfficiencyQueue.poll().getTotalLootSpawns());
    }

}
