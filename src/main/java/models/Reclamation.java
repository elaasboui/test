package models;

public class Reclamation {
    private int id;

    private String description;

    private int nombre_reclation;
    private String image;
public Reclamation(){}
    public Reclamation(int id,  String description, int nombre_reclation, String image) {
        this.id = id;

        this.description = description;

        this.nombre_reclation = nombre_reclation;
        this.image = image;
    }
    public Reclamation(  String description,  int nombre_reclation, String image) {


        this.description = description;

        this.nombre_reclation = nombre_reclation;
        this.image = image;
    }

    public int getId() {
        return id;
    }



    public String getDescription() {
        return description;
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


    public void setNombre_reclation(int nombre_reclation) {
        this.nombre_reclation = nombre_reclation;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", description='" + description;
    }
}
