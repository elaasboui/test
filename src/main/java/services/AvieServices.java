package services;

import models.Avie;
import utils.MyDataBase;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AvieServices implements IService<Avie> {
    private Connection connection = MyDataBase.getInstance().getConnection();
public AvieServices(){}
    @Override
    public void ajouter(Avie avie) throws SQLException {
        String req = "INSERT INTO avie (description, etoile) VALUES(?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, avie.getDescription());

            pst.setFloat(2, avie.getEtoile());
            pst.executeUpdate();
        }
    }









    @Override

        public void supprimer(int id) throws SQLException {
            String req = "DELETE FROM `avie` WHERE id=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }


    @Override

        public List<Avie> afficher() throws SQLException {
            String req = "select * from avie";
            List<Avie> avies = new ArrayList();
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                Avie a = new Avie();
                a.setId(rs.getInt("id"));
                a.setDescription(rs.getString("description"));
                a.setEtoile(rs.getFloat("etoile"));


                avies.add(a);
            }

            return avies;
        }


    public Avie getAvieById(int avieId) throws SQLException {
        Avie avie = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MyDataBase.getConnection();
            String query = "SELECT * FROM avie WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, avieId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String description = resultSet.getString("description");
                float etoile = resultSet.getFloat("etoile");
                avie = new Avie(avieId, description, etoile);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return avie;
    }

    public void modifier(Avie avie) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = MyDataBase.getConnection();
            String query = "UPDATE avie SET description = ?, etoile = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, avie.getDescription());
            statement.setFloat(2, avie.getEtoile());
            statement.setInt(3, avie.getId());
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

