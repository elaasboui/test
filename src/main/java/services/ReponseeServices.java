package services;

import models.Reponsee;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReponseeServices {

    private Connection connection = MyDataBase.getInstance().getConnection();

    public ReponseeServices() {}

    public void ajouter(Reponsee reponsee) throws SQLException {
        String req = "INSERT INTO reponsee (description, id_reclamation) VALUES (?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, reponsee.getDescription());
            pst.setInt(2, reponsee.getRec_id());
            pst.executeUpdate();
        }
    }

    public void modifier(Reponsee reponsee) throws SQLException {
        String req = "UPDATE reponsee SET description = ?, id_reclamation = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, reponsee.getDescription());
            pst.setInt(2, reponsee.getRec_id());
            pst.setInt(3, reponsee.getId());
            pst.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM reponsee WHERE id=?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(req);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public List<Reponsee> getAllReponsees() throws SQLException {
        String req = "SELECT * FROM reponsee";
        List<Reponsee> reponsees = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Reponsee reponsee = new Reponsee();
                reponsee.setId(rs.getInt("id"));
                reponsee.setDescription(rs.getString("description"));
                reponsee.setRec_id(rs.getInt("id_reclamation"));
                reponsees.add(reponsee);
            }
        }
        return reponsees;
    }
}
