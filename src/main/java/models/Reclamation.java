package models;

public class Reclamation {
    private int id;
    private int id_plat;
    private int id_resto;
    private int id_client;
    private String description;
    private float etoile;
    private int nombre_reclation;
    private String image;
public Reclamation(){}
    public Reclamation(int id, int id_plat, int id_resto, int id_client, String description, float etoile, int nombre_reclation, String image) {
        this.id = id;
        this.id_plat = id_plat;
        this.id_resto = id_resto;
        this.id_client = id_client;
        this.description = description;
        this.etoile = etoile;
        this.nombre_reclation = nombre_reclation;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getId_plat() {
        return id_plat;
    }

    public int getId_resto() {
        return id_resto;
    }

    public int getId_client() {
        return id_client;
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

    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }

    public void setId_resto(int id_resto) {
        this.id_resto = id_resto;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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
