package fr.epsi.b3.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epsi.b3.bo.Article;
import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.ArticleDAO;

public class ArticleJDBCDAO implements ArticleDAO {
    private static final String SELECT_QUERY = "SELECT * FROM article";
    private static final String INSERT_QUERY = "INSERT INTO article (nom, prix, fournisseur_id) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE article SET nom = ?, prix = ?, fournisseur = ? WHERE nom = ?";
    private static final String DELETE_QUERY = "DELETE FROM article WHERE id = ?";
    private static final String DELETE_BY_NAME_QUERY = "DELETE FROM article WHERE nom LIKE ?";
    private static final String AVG_PRICE_QUERY = "SELECT AVG(prix) FROM article";
    private static final String UPDATE_PRICE_QUERY = "UPDATE article SET prix = prix * ? WHERE type = ?";

    @Override
    public List<Article> select() throws SQLException {
        List<Article> list = new ArrayList<>();
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(SELECT_QUERY)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String nom = rs.getString("NOM");
                    double prix = rs.getDouble("PRIX");
                    int fournisseurId = rs.getInt("FOURNISSEUR_ID");
                    Article article = new Article(nom, prix, new Fournisseur(fournisseurId));
                    list.add(article);
                }
            }
        }
        return list;
    }

    @Override
    public void insert(Article article) throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(INSERT_QUERY)) {
            pst.setString(1, article.getNom());
            pst.setDouble(2, article.getPrix());
            pst.setInt(3, article.getFournisseur().getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'article", e);
        }
    }

    @Override
    public void update(Article article, String ancienNom) throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(UPDATE_QUERY)) {
            pst.setString(1, article.getNom());
            pst.setDouble(2, article.getPrix());
            pst.setString(3, article.getFournisseur().getNom());
            pst.setString(4, ancienNom);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification de l'article", e);
        }
    }

    @Override
    public void delete(Article article) throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(DELETE_QUERY)) {
            pst.setInt(1, article.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'article", e);
        }
    }

    @Override
    public void deleteByName(String name) throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(DELETE_BY_NAME_QUERY)) {
            pst.setString(1, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'article, le nom ne correspond pas", e);
        }
    }

    @Override
    public double averagePrice() throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(AVG_PRICE_QUERY);
                ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors du calcul du prix moyen des articles", e);
        }
    }

    @Override
    public void updatePrice(String type, double proportion) throws SQLException {
        try (Connection cnx = DBConnection.getSingle().getConnection();
                PreparedStatement pst = cnx.prepareStatement(UPDATE_PRICE_QUERY)) {
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'article, le nom ne correspond pas", e);
        }
    }
}
