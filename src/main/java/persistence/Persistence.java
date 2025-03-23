package persistence;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Station;
import model.StreetStop;
import model.Bus;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class Persistence {
    private static final String FILE_PATH = "src/main/java/resources/data.json";

    public static void saveData(List<Station> stations, List<StreetStop> streetStops) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("stations", gson.toJsonTree(stations));
            jsonObject.add("streetStops", gson.toJsonTree(streetStops));

            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(Bus bus) {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            Type stationListType = new TypeToken<List<Station>>() {}.getType();
            List<Station> stations = gson.fromJson(jsonObject.get("stations"), stationListType);
            for (Station station : stations) {
                bus.addNewStation(station.getName(), station.getCode());
            }

            Type stopListType = new TypeToken<List<StreetStop>>() {}.getType();
            List<StreetStop> stops = gson.fromJson(jsonObject.get("streetStops"), stopListType);
            for (StreetStop stop : stops) {
                bus.addStreetStop(stop.getName(), stop.getLocation());
            }
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
