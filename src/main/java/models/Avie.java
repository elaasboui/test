package models;

public class Avie {
    private int id;
    private int id_plat;
    private int id_resto;
    private int id_client;
    private String description;
    private float etoile;
    public Avie(){}

    public Avie(int id, int id_plat, int id_resto, int id_client, String description, float etoile) {
        this.id = id;
        this.id_plat = id_plat;
        this.id_resto = id_resto;
        this.id_client = id_client;
        this.description = description;
        this.etoile = etoile;
    }
    public Avie( int id_plat, int id_resto, int id_client, String description, float etoile) {

        this.id_plat = id_plat;
        this.id_resto = id_resto;
        this.id_client = id_client;
        this.description = description;
        this.etoile = etoile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_plat() {
        return id_plat;
    }

    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }

    public int getId_resto() {
        return id_resto;
    }

    public void setId_resto(int id_resto) {
        this.id_resto = id_resto;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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
