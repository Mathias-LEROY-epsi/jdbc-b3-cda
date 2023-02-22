package fr.epsi.b3.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.epsi.b3.bo.Utilisateur;
import fr.epsi.b3.dal.UtilisateurDAO;

public class UtilisateurJDBCDAO implements UtilisateurDAO {
    private static final String SECURED_LOGIN_QUERY = "SELECT * FROM utilisateur WHERE identifiant = ? AND mdp = ?";

    @Override
    public Utilisateur authentificationSecurisee(String login, String mdp) throws Exception {
        Utilisateur user = null;
        Connection sqlConnection = DBConnection.getSingle().getConnection();
        try (PreparedStatement pst = sqlConnection.prepareStatement(SECURED_LOGIN_QUERY)) {
            pst.setString(1, login);
            pst.setString(2, mdp);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nom");
                    String identifiant = rs.getString("identifiant");
                    String motDePasse = rs.getString("mdp");
                    user = new Utilisateur(id, nom, identifiant, motDePasse);
                } else {
                    throw new Exception("L'utilisateur n'existe pas");
                }
            }
        }
        return user;
    }
}
