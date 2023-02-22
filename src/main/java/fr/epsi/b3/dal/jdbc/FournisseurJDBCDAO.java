package fr.epsi.b3.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.FournisseurDAO;

public class FournisseurJDBCDAO implements FournisseurDAO {
    private static final String SELECT_QUERY = "SELECT * FROM fournisseur";
    private static final String INSERT_QUERY = "INSERT INTO fournisseur VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE fournisseur SET NOM = ? WHERE NOM = ?";
    private static final String DELETE_QUERY = "DELETE FROM fournisseur WHERE id = ?";

    @Override
    public List<Fournisseur> select() throws SQLException {
        List<Fournisseur> list = new ArrayList<>();
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(SELECT_QUERY)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String nom = rs.getString("NOM");
                    Fournisseur fournisseur = new Fournisseur(id, nom);
                    list.add(fournisseur);
                }
            }
        }
        return list;
    }

    @Override
    public void insert(Fournisseur fournisseur) throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(INSERT_QUERY)) {
            pst.setString(1, fournisseur.getNom());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du fournisseur", e);
        }
    }

    @Override
    public void update(String nouveauNom, String ancienNom) throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(UPDATE_QUERY)) {
            pst.setString(1, nouveauNom);
            pst.setString(2, ancienNom);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification du fournisseur", e);
        }
    }

    @Override
    public void delete(Fournisseur fournisseur) throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(DELETE_QUERY)) {
            pst.setInt(1, fournisseur.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du fournisseur", e);
        }
    }
}
