package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Station implements Comparable<Station>{
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("name")
    @Expose
    private String name;

    private transient Station next; //Transient indica que no se serializa
    private transient Station prev; //Transient indica que no se serializa

    public Station(String name, int code) {
        this.code = code;
        this.name = name;
        this.next = null;
        this.prev = null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station getNext() {
        return next;
    }

    public void setNext(Station next) {
        this.next = next;
    }

    public Station getPrev() {
        return prev;
    }

    public void setPrev(Station prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        String stationInfo = "";
        stationInfo += "Station name: " + this.name +
                "\n" + "Station code: " + this.code + "\n" + "-------------------------\n";
        return stationInfo;
    }

    @Override
    public int compareTo(Station otherStation) {
        return this.name.compareToIgnoreCase(otherStation.getName());
    }
}



