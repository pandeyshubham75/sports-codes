package SportsCodes;

import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    private Map<Integer, CityTime> checkins;
    private Map<String, Map<String, Integer[]>> distances;

    public UndergroundSystem() {
        checkins = new HashMap();
        distances = new HashMap();
    }

    public void checkIn(int id, String stationName, int t) {
        checkins.put(id, new CityTime(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CityTime ct = checkins.remove(id);
        // if (distances.containsKey(stationName)){
        //     HashMap<String, Integer[]> thisDistance = (HashMap<String, Integer[]>) distances.get(stationName);
        //     if (thisDistance.containsKey(ct.city)){
        //         Integer[] arr = thisDistance.get(ct.city);
        //         arr[0] = arr[0]+t-ct.time;
        //         arr[1]++;
        //     }
        // } else
        if (distances.containsKey(ct.city)){
            HashMap<String, Integer[]> thisDistance = (HashMap<String, Integer[]>) distances.get(ct.city);
            if (thisDistance.containsKey(stationName)){
                // if (stationName.equals("CN3K6CYM")){
                //     System.out.println("1");
                // }
                Integer[] arr = thisDistance.get(stationName);
                arr[0] = arr[0]+t-ct.time;
                arr[1]++;
            } else {
                // if (stationName.equals("CN3K6CYM")){
                //     System.out.println("2");
                // }
                thisDistance.put(stationName, new Integer[]{t-ct.time, 1});
            }
        } else {
            // if (stationName.equals("CN3K6CYM")){
            //         System.out.println("3");
            //     }
            HashMap<String, Integer[]> thisDistance = new HashMap();
            thisDistance.put(stationName, new Integer[]{t-ct.time, 1});
            distances.put(ct.city, thisDistance);
            // if (stationName.equals("CN3K6CYM")){
            //         System.out.println("Source: "+ct.city);
            //         System.out.println("Destination: "+stationName);
            //         System.out.println("*****: "+distances.get(stationName));
            //     }
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        // if (true)return 0;
        double sol = 0.0;
        int total = 0;
        if (distances.containsKey(startStation) && distances.get(startStation).containsKey(endStation)){
            Integer[] arr = distances.get(startStation).get(endStation);
            // if (startStation.equals("Y1A2ROGU")){
            //     System.out.println(arr[0]);
            //     System.out.println(arr[1]);
            // }
            sol += arr[0];
            total += arr[1];
        }
        // if (distances.containsKey(endStation) && distances.get(endStation).containsKey(startStation)){
        //     Integer[] arr = distances.get(endStation).get(startStation);
        //     if (startStation.equals("Y1A2ROGU")){
        //         System.out.println(arr[0]);
        //         System.out.println(arr[1]);
        //     }
        //     sol += arr[0];
        //     total += arr[1];
        // }
        // if (total == 0){
        //     System.out.println(startStation);
        //     System.out.println(endStation);
        //     System.out.println(sol);
        // }
        return sol/total;
    }
}

class CityTime {
    String city;
    int time;
    public CityTime(String city, int time){
        this.city = city;
        this.time = time;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */