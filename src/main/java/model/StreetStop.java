package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class StreetStop implements Comparable<StreetStop>{
    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("name")
    @Expose
    private String name;

    private transient StreetStop next; //Transient indica que no se serializa
    private transient StreetStop prev; //Transient indica que no se serializa

    public StreetStop(String name, String location) {
        this.location = location;
        this.name = name;
        this.next = null;
        this.prev = null;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StreetStop getNext() {
        return next;
    }

    public void setNext(StreetStop next) {
        this.next = next;
    }

    public StreetStop getPrev() {
        return prev;
    }

    public void setPrev(StreetStop prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        String streetStopInfo = "";
        streetStopInfo += "Street stop name: " + this.name +
                "\n" + "Street stop Location: " + this.location + "\n" + "-------------------------\n";
        return streetStopInfo;
    }

    @Override
    public int compareTo(StreetStop otherStrStop) {
        return this.name.compareToIgnoreCase(otherStrStop.getName());
    }
}