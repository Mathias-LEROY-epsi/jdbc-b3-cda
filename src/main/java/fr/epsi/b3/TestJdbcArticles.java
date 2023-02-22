package fr.epsi.b3;

import fr.epsi.b3.bo.Article;
import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.ArticleDAO;
import fr.epsi.b3.dal.DAOFactory;
import fr.epsi.b3.dal.FournisseurDAO;
import fr.epsi.b3.dal.jdbc.DBConnection;

public class TestJdbcArticles {
    public static void main(String[] args) throws Exception {
        try {
            ArticleDAO articleDao = DAOFactory.getArticleDAO();
            FournisseurDAO fournisseurDAO = DAOFactory.getFournisseurDAO();

            // Ajout d'un nouveau fournisseur
            Fournisseur fournisseur = new Fournisseur("La Maison de la Peinture");
            fournisseurDAO.insert(fournisseur);

            // Ajout de 4 articles
            Article a1 = new Article("Peinture blanche 1L", 12.5, fournisseur);
            articleDao.insert(a1);
            Article a2 = new Article("Peinture rouge mate 1L", 15.5, fournisseur);
            articleDao.insert(a2);
            Article a3 = new Article("Peinture noire laquée 1L", 17.8, fournisseur);
            articleDao.insert(a3);
            Article a4 = new Article("Peinture bleue mate 1L", 15.5, fournisseur);
            articleDao.insert(a4);

            // Update du prix des peintures mate --> réduit de 25%
            articleDao.updatePrice("mate", 0.75);

            // Extraction
            articleDao.select().forEach(System.out::println);

            // Extraction du prix moyen des articles
            double avg = articleDao.averagePrice();
            System.out.println("Prix moyen des articles : " + avg);

            // Suppression des articles comportant le nom peinture
            articleDao.deleteByName("Peinture");

        } catch (Exception e) {
            DBConnection.getSingle().closeConnection();
            System.out.println(e.getMessage());
        }
    }
}
