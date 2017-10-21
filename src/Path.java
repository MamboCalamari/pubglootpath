import java.util.ArrayList;

/**
 * Created by trsmith on 10/20/2017.
 */
public class Path {

    private ArrayList<Integer> path;
    private double totalTimeSpent;

    public Path()
    {
        path = new ArrayList<>();
        totalTimeSpent = 0.0;
    }

    public Path(ArrayList<Integer> path, double totalTimeSpent)
    {
        this.path = path;
        this.totalTimeSpent = totalTimeSpent;
    }

    public ArrayList<Integer> pathDeepCopy()
    {
        ArrayList<Integer> copy = new ArrayList<>();
        for(Integer buildingNumber : path)
        {
            copy.add(buildingNumber);
        }
        return copy;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public boolean isAlreadyInPath(int number)
    {
        if(path.indexOf(number) != -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
