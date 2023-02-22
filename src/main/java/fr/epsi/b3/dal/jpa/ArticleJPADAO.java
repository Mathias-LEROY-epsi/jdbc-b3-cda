package fr.epsi.b3.dal.jpa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epsi.b3.bo.Article;
import fr.epsi.b3.dal.ArticleDAO;

public class ArticleJPADAO implements ArticleDAO {
    @Override
    public List<Article> select() throws SQLException {
        //TODO à coder
        System.out.println("XML extraire");
        return new ArrayList<>();
    }

    @Override
    public void insert(Article article) throws SQLException {
        //TODO à coder
        System.out.println("XML insert");
    }

    @Override
    public void update(Article article, String ancienNom) throws SQLException {
        //TODO à coder
        System.out.println("XML update");
    }

    @Override
    public void delete(Article article) throws SQLException {
        //TODO à coder
        System.out.println("XML delete");
    }

    @Override
    public void deleteByName(String nom) throws SQLException {
        //TODO à coder
        System.out.println("XML deleteByName");
    }

    @Override
    public double averagePrice() throws SQLException {
        //TODO à coder
        System.out.println("XML averagePrice");
        return 0;
    }

    @Override
    public void updatePrice(String type, double proportion) throws SQLException {
        //TODO à coder
        System.out.println("XML updatePrice");
    }

}
