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
    private Connection connection = MyDataBase.getInstance().getconn();
public AvieServices(){}
    @Override
    public void ajouter(Avie avie) throws SQLException {
        String req = "INSERT INTO avie (description, id_client, id_resto, id_plat, etoile) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, avie.getDescription());
            pst.setInt(2, avie.getId_client());
            pst.setInt(3, avie.getId_resto());
            pst.setInt(4, avie.getId_plat());
            pst.setFloat(5, avie.getEtoile());
            pst.executeUpdate();
        }
    }



    @Override
    public void modifier(Avie avie) throws SQLException {
        String req = "UPDATE avie SET id_resto = ?, id_client = ?, id_plat = ?, description = ?, etoile = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setInt(1, avie.getId_resto());
            pst.setInt(2, avie.getId_client());
            pst.setInt(3, avie.getId_plat());
            pst.setString(4, avie.getDescription());
            pst.setFloat(5, avie.getEtoile());
            pst.setInt(6, avie.getId());
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
                a.setId_client(rs.getInt("client"));
                a.setId_resto(rs.getInt("resto"));
                a.setId_plat(rs.getInt("plat"));

                avies.add(a);
            }

            return avies;
        }
    }

