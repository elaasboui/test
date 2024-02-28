package models;

public class Reponsee {

    private int id;

    @Override
    public String toString() {
        return "Reponsee{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", rec_id=" + rec_id +
                '}';
    }
    public int rec_id; // Add this property

    private String description;


    // Constructeur sans paramètres
    public Reponsee() {
        // Initialisation par défaut
    }
    public Reponsee(String description) {
        this.description = description;
    }


    // Constructeur avec des paramètres


    // Constructeur
    public Reponsee(int id, String description) {
        this.id = id;
        this.description = description;

    }

    // Getters et setters pour id et description
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

    public void setRec_id(int recId) {
        this.rec_id=recId;
    }

    public int getRec_id() {
        return this.rec_id;
    }

    // Getter et setter pour reclamation

}
