package services;
import models.Avie;
import models.Reclamation;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamationServices implements IService<Reclamation> {
    private Connection connection = MyDataBase.getInstance().getConnection();

    public ReclamationServices() {}

    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {
        String req = "INSERT INTO reclamation (description, image, nombre_reclation) VALUES (?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, reclamation.getDescription());
            pst.setString(2, reclamation.getImage());
            pst.setInt(3, reclamation.getNombre_reclation());
            pst.executeUpdate();
        }
    }

    @Override
    public void modifier(Reclamation reclamation) throws SQLException {
        String req = "UPDATE reclamation SET description = ?, image = ?, nombre_reclation = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, reclamation.getDescription());
            pst.setString(2, reclamation.getImage());
            pst.setInt(3, reclamation.getNombre_reclation());
            pst.setInt(4, reclamation.getId());
            pst.executeUpdate();
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `reclamation` WHERE id=?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(req);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

        }
    public void supprimer(Reclamation reclamation) throws SQLException {
        // Connexion à la base de données choubikloubiki
        Connection conn = MyDataBase.getConnection(); // Remplacez Database.getConnectionChoubikloubiki() par la méthode appropriée pour obtenir une connexion à votre base de données choubikloubiki
        PreparedStatement stmt = null;

        try {
            // Requête SQL pour supprimer l'avis
            String sql = "DELETE FROM reclamation WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reclamation.getId()); // Remplacez getId() par la méthode appropriée pour obtenir l'ID de l'avis

            // Exécuter la requête
            stmt.executeUpdate();
        } finally {
            // Fermeture des ressources
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    @Override
    public List<Reclamation> afficher() throws SQLException { String req = "SELECT * FROM reclamation";
        List<Reclamation> reclamations = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setDescription(rs.getString("description"));
                r.setImage(rs.getString("image"));
                r.setNombre_reclation(rs.getInt("nombre_reclation"));
                reclamations.add(r);
            }
        }
        return reclamations;
    }

    public Reclamation getReclamationById(int id) throws SQLException {
        Reclamation reclamation = null;
        String query = "SELECT * FROM reclamation WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    int nombre_reclation = rs.getInt("nombre_reclation");
                    reclamation = new Reclamation(id, description,nombre_reclation, image);
                }
            }
        }
        return reclamation;
    }


}


