package fr.epsi.b3.dal;

import java.sql.SQLException;
import java.util.List;

import fr.epsi.b3.bo.Article;

public interface ArticleDAO {
    List<Article> select() throws SQLException;

    void insert(Article article) throws SQLException;

    void update(Article article, String ancienNom) throws SQLException;

    void delete(Article article) throws SQLException;

    void deleteByName(String nom) throws SQLException;

    double averagePrice() throws SQLException;

    void updatePrice(String type, double proportion) throws SQLException;
}
