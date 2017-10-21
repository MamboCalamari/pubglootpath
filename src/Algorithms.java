import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.Double.NaN;

/**
 * Created by trsmith on 10/20/2017.
 */
public class Algorithms {

    private static Path bestPath = new Path();
    private static final int maxSeconds = 60 * 5;
    private static PriorityQueue<Path> pathEfficiencyQueue = new PriorityQueue<>(Collections.reverseOrder());

    public static void breadthFirstVisit(Building building, Path path, City city) {
        ArrayList<Building> unvisitedNeighbors = new ArrayList<>();
        for (Integer nextBuildingNum : building.getNeighbors().keySet()) {
            if (!path.isAlreadyInPath(nextBuildingNum)) {
                unvisitedNeighbors.add(city.getBuildingFromNumber(nextBuildingNum));
            }
        }
        if (unvisitedNeighbors.size() == 0) {
            checkIfBestPathBreadthFirst(path);
        } else {
            for (Building nextBuilding : unvisitedNeighbors) {
                double newTimeSpent = path.getTotalTimeSpent() + building.getNeighbors().get(nextBuilding.getNumber()) + building.getTimeToLoot();
                if (newTimeSpent > maxSeconds) {
                    checkIfBestPathBreadthFirst(path);
                } else {
                    ArrayList<Integer> newPathList = path.pathDeepCopy();
                    newPathList.add(nextBuilding.getNumber());
                    Path newPath = new Path(newPathList, newTimeSpent, path.getTotalLootSpawns() + nextBuilding.getLootSpawns());
                    breadthFirstVisit(nextBuilding, newPath, city);
                }
            }
        }
    }

    private static void checkIfBestPathBreadthFirst(Path path) {
        if (path.getTotalLootSpawns() > bestPath.getTotalLootSpawns()) {
            bestPath = path;
        }
    }

    public static void bestFirstVisit(Building building, Path path, City city) {
        ArrayList<Building> unvisitedNeighbors = new ArrayList<>();
        for (Integer nextBuildingNum : building.getNeighbors().keySet()) {
            if (!path.isAlreadyInPath(nextBuildingNum)) {
                unvisitedNeighbors.add(city.getBuildingFromNumber(nextBuildingNum));
            }
        }

        ArrayList<Path> terminalSolutions = new ArrayList<>();
        for (Building nextBuilding : unvisitedNeighbors) {
            double newTimeSpent = path.getTotalTimeSpent() + building.getNeighbors().get(nextBuilding.getNumber()) + building.getTimeToLoot();
            //best solution overall, stop here
            if (newTimeSpent > maxSeconds && isTerminalSolution(path)) {
                terminalSolutions.add(path);
            }
            //best solution so far, but their are higher efficiencies in the queue
            else if (newTimeSpent > maxSeconds && checkIfBestPathBestFirst(path)) {
                bestPath = path;
            } else {
                ArrayList<Integer> newPathList = path.pathDeepCopy();
                newPathList.add(nextBuilding.getNumber());
                Path newPath = new Path(newPathList, newTimeSpent, path.getTotalLootSpawns() + nextBuilding.getLootSpawns());
                pathEfficiencyQueue.add(newPath);
            }
        }
        //check if at least one terminal solution is still valid
        if (terminalSolutions.size() != 0) {
            for (Path potentialSolution : terminalSolutions) {
                if (!isTerminalSolution(path)) {
                    terminalSolutions.remove(potentialSolution);
                }
            }
        }
        //and return the best
        Path bestTerminalSolution = new Path();
        if (terminalSolutions.size() != 0) {
            for (Path potentialSolution : terminalSolutions) {
                if (potentialSolution.getTotalLootSpawns() > bestTerminalSolution.getTotalLootSpawns()) {
                    bestTerminalSolution = potentialSolution;
                }
            }
            bestPath = bestTerminalSolution;
            return;
        }
        //if not try again
        Path pathToVisit = pathEfficiencyQueue.poll();
        //if queue is empty we cannot loot for the length specified
        if (pathToVisit == null) {
            return;
        }
        bestFirstVisit(city.getBuildingFromNumber(pathToVisit.getLastBuildingNumInPath()), pathToVisit, city);
    }

    private static boolean isTerminalSolution(Path path) {
        if (!checkIfBestPathBestFirst(path)) {
            return false;
        }
        double pathEfficiency = path.getTotalLootSpawns() / path.getTotalTimeSpent();
        double bestPartialPathEfficiency = pathEfficiencyQueue.peek().getTotalLootSpawns() / pathEfficiencyQueue.peek().getTotalTimeSpent();
        if (pathEfficiency > bestPartialPathEfficiency) {
            return true;
        }
        return false;
    }


    private static boolean checkIfBestPathBestFirst(Path path) {
        double pathEfficiency = path.getTotalLootSpawns() / path.getTotalTimeSpent();
        double bestPathEfficiency = bestPath.getTotalLootSpawns() / bestPath.getTotalTimeSpent();
        if (bestPathEfficiency == NaN) {
            bestPathEfficiency = 0.000000000001;
        }
        if (pathEfficiency > bestPathEfficiency) {
            return true;
        }
        return false;
    }
}
