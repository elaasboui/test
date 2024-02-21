package services;

import models.Avie;
import models.Reclamation;
import utils.MyDataBase;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ReclamationServices implements IService<Reclamation> {
    private Connection connection = MyDataBase.getInstance().getConnection();
    public ReclamationServices(){};
    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {
        String req = " INSERT INTO `reclamation`(`description`, `image`, `nombre_reclation`, `etoile`) VALUES (?,?,?,?)";

        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, reclamation.getDescription());
            pst.setFloat(2, reclamation.getEtoile());
            pst.setString(3, reclamation.getImage());
            pst.setInt(4, reclamation.getNombre_reclation());

            pst.executeUpdate();
        }
    }
    @Override
    public void modifier(Reclamation reclamation) throws SQLException {
        String req = " UPDATE reclamation SET description=?,image=?,nombre_reclation=?,etoile=? WHERE id = ?";

        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, reclamation.getDescription());
            pst.setFloat(2, reclamation.getEtoile());
            pst.setInt(3, reclamation.getNombre_reclation());
            pst.setString(4, reclamation.getImage());

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
    @Override

    public List<Reclamation> afficher() throws SQLException {
        String req = "select * from reclamation";
        List<Reclamation> reclamations = new ArrayList();
        Statement st = this.connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while(rs.next()) {
            Reclamation r = new Reclamation();
            r.setId(rs.getInt("id"));
            r.setDescription(rs.getString("description"));
            r.setEtoile(rs.getFloat("etoile"));
            r.setImage(rs.getString("image"));
            r.setNombre_reclation(rs.getInt("nombre reclamation"));

            reclamations.add(r);
        }

        return reclamations;
    }
}
