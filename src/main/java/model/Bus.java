package model;
import persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Bus {
    private Station firstStation;
    private StreetStop firstStreetStop;
    private int totalStations;
    private int totalStreetStops;

    public Bus() {
        firstStation = null;
        firstStreetStop = null;
        totalStations = 0;
        totalStreetStops = 0;
        Persistence.loadData(this);

    }


    public boolean addNewStation(String name, int code) {
        Station newStation = new Station(name, code);
        boolean added = false;

        if (firstStation == null) {
            firstStation = newStation;
            firstStation.setNext(newStation);
            firstStation.setPrev(newStation);
        } else {
            Station lastStation = firstStation.getPrev();
            lastStation.setNext(newStation);
            newStation.setPrev(lastStation);
            newStation.setNext(firstStation);
            firstStation.setPrev(newStation);
        }

        totalStations++;
        Persistence.saveData(getStationList(), getStreetStopList()); // Guardar cambios
        return true;
    }

    public boolean addStreetStop(String name, String location) {
        StreetStop newStop = new StreetStop(name, location);

        if (firstStreetStop == null) {
            firstStreetStop = newStop;
            firstStreetStop.setNext(newStop);
            firstStreetStop.setPrev(newStop);
        } else {
            StreetStop lastStop = firstStreetStop.getPrev();
            lastStop.setNext(newStop);
            newStop.setPrev(lastStop);
            newStop.setNext(firstStreetStop);
            firstStreetStop.setPrev(newStop);
        }

        totalStreetStops++;
        Persistence.saveData(getStationList(), getStreetStopList()); // Guardar cambios
        return true;
    }

    public boolean removeStation(String name) {
        Station stationToRemove = searchStation(name);
        if (stationToRemove == null) {
            return false;
        }

        if (firstStation == stationToRemove && firstStation.getNext() == firstStation) {
            firstStation = null;
            totalStations = 0;
        } else {
            if (firstStation == stationToRemove) {
                firstStation = firstStation.getNext();
            }
            stationToRemove.getPrev().setNext(stationToRemove.getNext());
            stationToRemove.getNext().setPrev(stationToRemove.getPrev());
            totalStations--;
        }

        Persistence.saveData(getStationList(), getStreetStopList());
        return true;
    }

    public boolean removeStreetStop(String name) {
        StreetStop stopToRemove = searchStreetStop(name);
        if (stopToRemove == null) {
            return false;
        }

        if (firstStreetStop == stopToRemove && firstStreetStop.getNext() == firstStreetStop) {
            firstStreetStop = null;
            totalStreetStops = 0;
        } else {
            if (firstStreetStop == stopToRemove) {
                firstStreetStop = firstStreetStop.getNext();
            }
            stopToRemove.getPrev().setNext(stopToRemove.getNext());
            stopToRemove.getNext().setPrev(stopToRemove.getPrev());
            totalStreetStops--;
        }

        Persistence.saveData(getStationList(), getStreetStopList());
        return true;
    }

    public Station searchStation(String name) {
        if (firstStation == null) return null;
        Station current = firstStation;

        do {
            if (current.getName().equals(name)) return current;
            current = current.getNext();
        } while (current != firstStation);

        return null;
    }

    public StreetStop searchStreetStop(String name) {
        if (firstStreetStop == null) return null;
        StreetStop current = firstStreetStop;

        do {
            if (current.getName().equals(name)) return current;
            current = current.getNext();
        } while (current != firstStreetStop);

        return null;
    }

    public Station getFirstStation() {
        return firstStation;
    }

    public StreetStop getFirstStreetStop() {
        return firstStreetStop;
    }

    public int getTotalStation() {
        return totalStations;
    }

    public int getTotalStreetStops() {
        return totalStreetStops;
    }


    //Solo cambiendo el contenido de los atributos
    public void sortByName(){

        //Caso base cuando esta vacia o solo hay un elemento
        if(firstStation == null || firstStation.getNext() == firstStation){
            return;
        }

        boolean swapped;

        do {
            swapped = false;
            Station currentStation = firstStation;
            do {
                Station nextStation = currentStation.getNext();
                if (nextStation != firstStation && currentStation.getName().compareTo(nextStation.getName()) > 0) {
                    String tempName = currentStation.getName();
                    int tempCode = currentStation.getCode();

                    currentStation.setName(nextStation.getName());
                    currentStation.setCode(nextStation.getCode());
                    nextStation.setName(tempName);
                    nextStation.setCode(tempCode);

                    swapped = true;

                }
                currentStation = currentStation.getNext();
            } while (currentStation.getNext() != firstStation);


        } while (swapped);

    }


    //------------------------------------------------------------------------------------------------------------------------------------------------

    public void sortByNameExchangeRef() {
        if (firstStation == null || firstStation.getNext() == firstStation) {
            return; // Lista vacía o con un solo nodo, ya está ordenada
        }

        Station current = firstStation.getNext(); // Empezamos desde el segundo nodo
        while (current != firstStation) {
            Station next = current.getNext();
            Station temp = current;
            Station prev = current.getPrev();

            // Mover temp hacia atrás si su nombre es menor que prev
            while (prev != firstStation.getPrev() && prev.getName().compareToIgnoreCase(temp.getName()) > 0) {
                // Intercambiar punteros
                swapNodes(prev, temp);
                prev = temp.getPrev(); // Retrocedemos para seguir ordenando
            }

            current = next; // Pasamos al siguiente nodo
        }
    }

    /**
     * Intercambia dos nodos en la lista doblemente enlazada circular.
     */
    private void swapNodes(Station a, Station b) {
        if (a == b) return;

        // Desconectar a y b de sus posiciones actuales
        a.getPrev().setNext(b);
        b.getNext().setPrev(a);

        // Intercambiar referencias
        a.setNext(b.getNext());
        b.setPrev(a.getPrev());

        b.setNext(a);
        a.setPrev(b);

        // Si a era el primer nodo, actualizamos firstStation
        if (firstStation == a) {
            firstStation = b;
        }
    }


    //------------------------------------------------------------------------------------------------------------------------------------------------


    public String showAllStations(){

        String allStations = "";

        if(firstStation != null){
            Station currentStation = firstStation;
            allStations = currentStation.toString();
            while(currentStation.getNext() != firstStation){
                allStations += currentStation.getNext().toString();
                currentStation = currentStation.getNext();
            }
            return allStations;
        } else {
            return allStations += "No hay estaciones para mostrar";
        }


    }

    //---------------------------------------------------------------------------------
    //Json

    public List<Station> getStationList() {
        List<Station> stationList = new ArrayList<>();
        if (firstStation == null) {
            return stationList;
        }

        Station current = firstStation;
        do {
            stationList.add(current);
            current = current.getNext();
        } while (current != firstStation);

        return stationList;
    }

    public List<StreetStop> getStreetStopList() {
        List<StreetStop> stopList = new ArrayList<>();
        if (firstStreetStop == null) {
            return stopList;
        }

        StreetStop current = firstStreetStop;
        do {
            stopList.add(current);
            current = current.getNext();
        } while (current != firstStreetStop);

        return stopList;
    }



}