package fr.epsi.b3;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.bo.Utilisateur;
import fr.epsi.b3.dal.DAOFactory;
import fr.epsi.b3.dal.FournisseurDAO;
import fr.epsi.b3.dal.UtilisateurDAO;
import fr.epsi.b3.dal.jdbc.DBConnection;
import fr.epsi.b3.error.StoreModeNotFoundException;

public class Main {

    public static void main(String[] args) throws SQLException {
        //authentificationSecurisee();

        try {
            FournisseurDAO dao = DAOFactory.getFournisseurDAO();

            //Ajout d'un nouveau fournisseur
            dao.insert(new Fournisseur("L'espace création"));

            //Extraction
            List<Fournisseur> fournisseurs = dao.select();
            for (Fournisseur item : fournisseurs) {
                System.out.println(item);
            }

            // Update du fournisseur
            dao.update("L'espace création", "L'espace création 2");

            // Delete le fournisseur
            dao.delete(new Fournisseur("L'espace création 2"));

        } catch (Exception e) {
            DBConnection.getSingle().closeConnection();
            System.out.println(e.getMessage());
        }
    }

    private static void authentificationSecurisee() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("************************************************");
        System.out.println("******* Bienvenue dans mon App Sécurisée *******");
        System.out.println("************************************************");
        try {
            Utilisateur user = null;
            UtilisateurDAO dao = DAOFactory.getUtilisateurDAO();

            do {
                System.out.println("* Merci de vous identifier ...");
                System.out.print("* Identifiant : ");
                String login = scanner.nextLine();
                System.out.print("* Mot de passe : ");
                String mdp = scanner.nextLine();
                try {
                    user = dao.authentificationSecurisee(login, mdp);
                    System.out.printf("* Bienvenue à toi %s%n", user.getNom());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("* Merci de recommencer...");
                }
            } while (user == null);

        } catch (StoreModeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}