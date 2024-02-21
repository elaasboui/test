package models;

public class Reclamation {
    private int id;

    private String description;
    private float etoile;
    private int nombre_reclation;
    private String image;
public Reclamation(){}
    public Reclamation(int id,  String description, float etoile, int nombre_reclation, String image) {
        this.id = id;

        this.description = description;
        this.etoile = etoile;
        this.nombre_reclation = nombre_reclation;
        this.image = image;
    }
    public Reclamation(  String description, float etoile, int nombre_reclation, String image) {


        this.description = description;
        this.etoile = etoile;
        this.nombre_reclation = nombre_reclation;
        this.image = image;
    }

    public int getId() {
        return id;
    }



    public String getDescription() {
        return description;
    }

    public float getEtoile() {
        return etoile;
    }

    public int getNombre_reclation() {
        return nombre_reclation;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtoile(float etoile) {
        this.etoile = etoile;
    }

    public void setNombre_reclation(int nombre_reclation) {
        this.nombre_reclation = nombre_reclation;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
