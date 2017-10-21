import java.util.ArrayList;

/**
 * Created by trsmith on 10/20/2017.
 */
public class Path implements Comparable<Path> {

    private ArrayList<Integer> path;
    private double totalTimeSpent;
    private int totalLootSpawns;

    public Path() {
        path = new ArrayList<>();
        totalTimeSpent = 0.0;
        totalLootSpawns = 0;
    }

    public Path(ArrayList<Integer> path, double totalTimeSpent, int totalLootSpawns) {
        this.path = path;
        this.totalTimeSpent = totalTimeSpent;
        this.totalLootSpawns = totalLootSpawns;
    }

    public ArrayList<Integer> pathDeepCopy() {
        ArrayList<Integer> copy = new ArrayList<>();
        for (Integer buildingNumber : path) {
            copy.add(buildingNumber);
        }
        return copy;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public double getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public int getTotalLootSpawns() {
        return totalLootSpawns;
    }

    public boolean isAlreadyInPath(int number) {
        if (path.indexOf(number) != -1) {
            return true;
        } else {
            return false;
        }
    }

//    public void addBuildingToPath(int buildingNum)
//    {
//        path.add(buildingNum);
//    }

    public int getLastBuildingNumInPath()
    {
        return path.get(path.size() - 1);
    }

    @Override
    public int compareTo(Path o) {
        double efficiencyCompare = (totalLootSpawns / totalTimeSpent) - (o.totalLootSpawns / o.totalTimeSpent);
        if (efficiencyCompare > 0.0) {
            return 1;
        } else if (efficiencyCompare < 0.0) {
            return -1;
        } else {
            return 0;
        }
    }
}
