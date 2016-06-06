package adventofcode_13;

import java.util.ArrayList;

public class AdventOfCode_13 
{
    public static void main(String[] args)
    {
        populateCityData(Constants.INPUT_FILE_NAME);
        tripPlanner();
    }

    // reads file input builds a list of cities with crosslinked
    // distance information to one another
    
    private static void populateCityData(String filename)
    {
        ArrayList<String> dataLine = FileParser.getStrings(filename);
        String[] data;
        int happy = 0;
        
        for (int i = 0; i < dataLine.size(); i++)
        {
             data = dataLine.get(i).split(" ");
             
             try
             { 
                 happy = data[2].equals("gain")? 1 : -1;
                 happy = happy * Integer.parseInt(data[3]);
                 CityManager.processCities(data[0], data[10], happy); 
             }
             catch(Exception e)
             { System.out.println(e); }
        }
    }
    
    // Ask PermutationCalc to find all possible routes that n cities
    // could be visited, then for each route, query each city for
    // the distance to the next. Keep a running total of the shortest and 
    // longest routes and report those.
    
    private static void tripPlanner()
    {
        ArrayList<int[]> allRoutes = PermutationCalc.findAllPermutations(CityManager.getNumCities());
        
        int longest = 0;
        int tripSize = 0;
        
        for (int i = 0; i < allRoutes.size(); i++)
        {
            tripSize = getCurrentTripDistance(allRoutes, i);

            if (tripSize > longest)
                longest = tripSize;
        }
        
        System.out.println("The most happiness is: " + longest);
    }
    
    private static int getCurrentTripDistance(ArrayList<int[]> allRoutes, int routeNum)
    {
        int[] currentRoute = allRoutes.get(routeNum);
        int tripSize = 0;
        int lastTrip = currentRoute.length-1;
        
        for (int i = 0; i < lastTrip; i++)
        {
            tripSize += CityManager.getDistance(currentRoute[i], currentRoute[i+1]);
            tripSize += CityManager.getDistance(currentRoute[i+1], currentRoute[i]);
        }

        tripSize += CityManager.getDistance(currentRoute[lastTrip], currentRoute[0]);
        tripSize += CityManager.getDistance(currentRoute[0], currentRoute[lastTrip]);
        
        return tripSize;
    }
}  