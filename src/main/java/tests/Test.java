package tests;

import models.Avie;

import services.AvieServices;
import utils.MyDataBase;

import java.sql.Date;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        MyDataBase bd = MyDataBase.getInstance();
        AvieServices cl = new AvieServices();

        try {
            // Add a new Admin
            Avie newAvie = new Avie( "ellaaaa",2.333F);
            cl.ajouter(newAvie);
            System.out.println("Admin added successfully.");

            // Update an Admin
            int adminIdToUpdate = 5;
            Avie adminToUpdate = new Avie( "f", 2.55f);
            cl.modifier(adminToUpdate);
            System.out.println("Avie updated successfully.");

            // Delete an Admin

            int avieIdToDelete =8;
            cl.supprimer(avieIdToDelete);
            System.out.println("Avie deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
