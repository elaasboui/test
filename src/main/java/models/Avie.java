package models;

import javafx.beans.value.ObservableValue;

public class Avie {
    private int id;

    private String description;
    private float etoile;
 public  Avie(){};
    public Avie(int id,  String description, float etoile) {
        this.id = id;

        this.description = description;
        this.etoile = etoile;
    }
    public Avie( String description, float etoile) {


        this.description = description;
        this.etoile = etoile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getEtoile() {
        return etoile;
    }

    public void setEtoile(float etoile) {
        this.etoile = etoile;
    }


}
